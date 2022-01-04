package cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author lixiliang
 * @describe
 * @date 2021/8/3
 */
@Slf4j
public class Guava {
    @Data
    class BeanA{
        private String id;
        private String name;
    }
    LoadingCache<String, Map<String, BeanA>> cache;
    public void buildCache(){
        cache = CacheBuilder.newBuilder()
                .maximumSize(1)
                // 最近一次初始化,或者写入操作后的3s刷新,刷新缓存是异步操作,不会阻塞(会有一个请求执行查询,其他请求仍然访问旧值;抗压,更新数据可用)
                .refreshAfterWrite(5, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Map<String,BeanA>>() {
                    @Override
                    public Map<String,BeanA> load(String key) {
                        log.info("ChannelInfoCache load cache start~,key:{}",key);
                        List<BeanA> channelInfos = getBeanAList();
                        log.info("ChannelInfoCache load cache finish,cache data size = {}", channelInfos.size());
                        return channelInfos.stream().collect(Collectors.toMap(BeanA::getId, Function.identity(),(key1, key2) -> key2));
                    }
                });
    }
    public static void main(String[] args) {
        Guava g = new Guava();
        g.buildCache();
        try {
            for (int i = 0; i < 11; i++) {
               log.info("1 get {}",g.get("id"+i));
            }
            TimeUnit.SECONDS.sleep(4);
            for (int i = 0; i < 10; i++) {
                log.info("2 get {}",g.get("id"+i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public BeanA get(String id) throws ExecutionException {
        return cache.get(getClass().getSimpleName()).get(id);
    }

    public List<BeanA> getBeanAList(){
        log.info("invoked");
        List<BeanA> aList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            BeanA a = new BeanA();
            a.id= "id"+i;
            a.name = "name"+i;
            aList.add(a);
        }

        return aList;
    }
}
