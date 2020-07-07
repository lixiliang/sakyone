package widevine;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import utill.HttpResponse;
import utill.HttpUtils;

public class WindevineTest {
    @Test
    public void getKey(){
        String url = "http://license.uat.widevine.com/cenc/getcontentkey/widevine_test";
        JSONObject object = new JSONObject();
        String request = "ewogICJjb250ZW50X2lkIjogIlptdHFNMnhxWVZOa1ptRnNhM0l6YWc9PSIsCiAgInRyYWNrcyI6IFsKICAgIHsgInR5cGUiOiAiU0QiIH0sCiAgICB7ICJ0eXBlIjogIkhEIiB9LAogICAgeyAidHlwZSI6ICJBVURJTyIgfQogIF0sCiAgImRybV90eXBlcyI6IFsgIldJREVWSU5FIiBdLAogICJwb2xpY3kiOiAiIgp9Cg==";
        String signature = "kwVLL4xVh9mnlZlPqiEWN0E+FsvG0y+/oy451XXeIMo=";
        String signer = "widevine_test";
        object.put("request",request);
        object.put("signature",signature);
        object.put("signer",signer);
        HttpResponse response = HttpUtils.jsonPost(url,object);
        System.out.println(response.getContent());
    }
}
