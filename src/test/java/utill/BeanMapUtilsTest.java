package utill;

import com.google.common.collect.Maps;
import iap.InApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

@Slf4j
public class BeanMapUtilsTest {

    @Test
    public void beanToMap() {
        InApp inApp = new InApp();
        inApp.setAuto_renew_product_id("prod");
        inApp.setAuto_renew_status("1");
        inApp.setApp_item_id("a1");
        Map map = BeanMapUtils.beanToMap(inApp);
        log.info("map:{}",map);
    }

    @Test
    public void mapToBean() {
        InApp inApp;
        Map<String,String> map = Maps.newHashMap();
        map.put("original_transaction_id","orgTranid");
        map.put("app_item_id","a1");
        map.put("is_in_intro_offer_period","22");
        inApp = BeanMapUtils.mapToBean(map,InApp.class);
        log.info("in:{}",inApp);
    }
}