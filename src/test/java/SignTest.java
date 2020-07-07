import code.MD5;
import com.google.common.collect.Lists;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import utill.HttpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SignTest {
    JSONObject data = new JSONObject();
    @Before
    public void buildData(){
        JSONArray list = new JSONArray();
        list.put("Uuu");
        list.put("I’d");
        data.put("voteChooseAnswerList",list);
        data.put("hasAnswer",1);
    }

    @Test
    public void serverSign() {
        String dataStr = "";
        if(data instanceof JSONObject) {
            @SuppressWarnings("unchecked")
            Iterator<String> dataKeys = data.keys();
            List<String> dataKeyList = new ArrayList<String>();
            while(dataKeys.hasNext()){
                dataKeyList.add(dataKeys.next());
            }
            Collections.sort(dataKeyList);
            for (String dataKey : dataKeyList) {
                dataStr+=dataKey+"="+ data.get(dataKey)+"&";
            }
        }else {
            dataStr = data.toString();
        }
        System.out.println(dataStr);
        String sign = MD5.MD5Encode(dataStr);
        System.out.println(dataStr+"sign:"+sign);
    }

    @Test
    public void androidSign(){
        Mybean bean = new Mybean();
        List<String> alist = Lists.newArrayList();
        alist.add("Uuu");
        alist.add("I’d");
        bean.setVoteChooseAnswerList(alist);
        bean.setHasAnswer(1);

        String dataStr = "";
        String beanToJson = JsonUtil.beanToJson(bean);
        JSONObject jsonObject = new JSONObject(beanToJson);
        Iterator<String> dataKeys = jsonObject.keys();
        List<String> dataKeyList = new ArrayList<>();
        while(dataKeys.hasNext()){
            dataKeyList.add(dataKeys.next());
        }
        Collections.sort(dataKeyList);
        for (String dataKey : dataKeyList) {
            dataStr += dataKey + "=" + jsonObject.get(dataKey) + "&";
        }
        String sign = MD5.MD5Encode(dataStr);
        System.out.println(dataStr+"sign:"+sign);
        bean.setSign(sign);


        HttpUtils.jsonPost("http://localhost:8899/test/t1",bean);

    }
}

class Mybean{
    List<String> voteChooseAnswerList;
    Integer hasAnswer;
    String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<String> getVoteChooseAnswerList() {
        return voteChooseAnswerList;
    }

    public void setVoteChooseAnswerList(List<String> voteChooseAnswerList) {
        this.voteChooseAnswerList = voteChooseAnswerList;
    }

    public Integer getHasAnswer() {
        return hasAnswer;
    }

    public void setHasAnswer(Integer hasAnswer) {
        this.hasAnswer = hasAnswer;
    }
}
