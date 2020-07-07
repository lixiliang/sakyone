
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.BaseEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class OutData extends BaseEntity {
    public OutData() {
    }

    public OutData(boolean status, int msgType, String msg, Object data, Object otherData, boolean transfer) {
        this.status = status;
        this.msgType = msgType;
        this.msg = msg;
        this.data = data;
        if (transfer) {
            if (data instanceof JSONObject || data instanceof JSONArray || data instanceof JsonObject || data instanceof JsonArray) {
                ObjectMapper om = new ObjectMapper();
                JsonNode node = null;
                try {
                    node = om.readTree(data.toString());
                } catch (JsonProcessingException e) {
                    node = null;
                } catch (IOException e) {
                    node = null;
                }
                this.data = node;
            } else {
                this.data = data;
            }
            if (otherData instanceof JSONObject || otherData instanceof JSONArray) {
                ObjectMapper om = new ObjectMapper();
                JsonNode node = null;
                try {
                    node = om.readTree(otherData.toString());
                } catch (JsonProcessingException e) {
                    node = null;
                } catch (IOException e) {
                    node = null;
                }
                this.otherData = node;
            } else {
                this.otherData = otherData;
            }
        }
        this.signType = "md5";
        this.time = System.currentTimeMillis();
    }

    public OutData(boolean status, int msgType, String msg, Object data, Object otherData) {
        this(status, msgType, msg, data, otherData, true);
    }

    public OutData(boolean status, int msgType, String msg, Object data) {
        this(status, msgType, msg, data, null);
    }

    public OutData(boolean status, int msgType, String msg) {
        this(status, msgType, msg, null);
    }

    public OutData(int msgType, String msg, Object data) {
        this(true, msgType, msg, data);
    }

    public OutData(int msgType, String msg) {
        this(true, msgType, msg, null);
    }


    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    private Integer msgType;

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    private Object otherData;

    public Object getOtherData() {
        return otherData;
    }

    public void setOtherData(Object otherData) {
        this.otherData = otherData;
    }

    private String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    private String signType;

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    private Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
