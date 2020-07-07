package stream;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class Test {
    @org.junit.Test
    public void t1() {

        List<String> memberUuidList = Arrays.asList("a", "b", "c", "d");
        List<String> data = Arrays.asList("a", "b", "c");
        Map<String, String> result = Maps.newHashMapWithExpectedSize(data.size());
        for (String uuid : memberUuidList) {
            log.info("uuid:{}", uuid);
            String info = data.stream().filter(element -> element.equals(uuid)).findAny().orElse("");
            log.info("info:{}", info);
            result.put(uuid, info);
        }
    }

    @org.junit.Test
    public void t3() {
        List<String> ids = Arrays.asList("com.tvbc.new3.vip.13m.subscribe.discount","com.tvbc.new3.vip.12m.subscribe", "com.tvbc.new3.vip.1m.subscribe", "com.tvbc.vip.12m", "com.tvbc.vip.12m.daily");
        List<String> id = ids.stream()
                .filter(receipt-> receipt.indexOf("subscribe")>-1)
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());
        log.info("id:{}",id);
    }

    @org.junit.Test
    public void t2() {
        List<String> ids = Arrays.asList("a", "b", "c", "d", "runException");
        List<Integer> value = Arrays.asList(33, 22, null, null, 5);
//        List<Integer> d =
//        value.stream().map(n -> n * n).forEach(s -> System.out.print(s + " "));
        Map map = Maps.newHashMap();
        IntStream.range(0, value.size()).forEach(i -> {
            if (value.get(i) != null)
                map.put(ids.get(i), value.get(i));
        });
        map.forEach((k, v) -> {
            System.out.println("k:" + k + " v:" + v);
        });

//        new HashMap<String, Integer>().put(ids.get(i), value.get(i))
      /*  value.stream().collect((Collectors.groupingBy(Integer::intValue)));
        Map<Integer, Integer> m = value.stream().collect(Collectors.toMap(Integer::intValue, Function.identity(), (oldValue, newValue) -> newValue));
        m.forEach((k, v) -> System.out.println(k + ": " + v));*/
    }

    private String getById(String uuid) {
        if (!uuid.equals("a")) return null;
        return uuid;
    }
}
