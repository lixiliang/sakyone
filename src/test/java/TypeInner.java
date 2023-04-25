import entity.OutData;
import lombok.extern.slf4j.Slf4j;
import lottery.Award;

import java.util.Map;

/**
 * @author lixiliang
 * @describe
 * @date 2023/3/23
 */
@Slf4j
public class TypeInner {

    public void o1(OutData<Award> award){
      log.info("{}",award);
    }

    public void a2(Award award){
      log.info("{}",award);
    }
    public void m3(Map<String,Award> award){
      log.info("{}",award);
    }
}
