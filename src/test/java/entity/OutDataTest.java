package entity;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;
import org.junit.Test;
import utill.JsonUtil;

public class OutDataTest {
    @Test
    public void t1() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orderStatus", 1);
        jsonObject.put("orderDesc", "test1");
        OutData o = new OutData(true, 0, "ok", jsonObject);

        System.out.println(JSON.toJSONString(o));
        System.out.println(JsonUtil.getJson(o));
    }
}