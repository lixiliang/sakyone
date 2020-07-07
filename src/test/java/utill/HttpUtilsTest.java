package utill;

import entity.AppleSubscribe;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;

@Slf4j
public class HttpUtilsTest {

    @Test
    public void get() {
        HttpResponse response = HttpUtils.get("http://localhost:8499/payApi/external/test?a=1",1,1000);
        log.info("response:{}",response);
    }

    @Test
    public void post() {
        AppleSubscribe subscribe = new AppleSubscribe();
        subscribe.setCreateTime(new Date());
        subscribe.setContinueTimes(11);
        HttpResponse response = HttpUtils.jsonPost("http://localhost:8499/payApi/external/test",subscribe,0,1000);
    }
}