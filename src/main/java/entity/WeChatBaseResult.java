package entity;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
/**
 * 微信接口返回结果共用方法
 */
public class WeChatBaseResult extends BaseEntity {
    public static final String SUCCESS_CODE = "SUCCESS";
    public static final String ERROR_CODE = "FAIL";
    public static final String SYSTEMERROR = "接⼝返回错误";
    public static final String PARAMERROR = "参数错误";
    public static final String SIGNERROR = "签名错误";
    public static final String PAYAUTHERROR = "权限校验错误";
    public static final String ORDER_MONEY_ERROR = "订单金额异常";
    public static final String ORDER_NOT_FOUND = "订单不存在";

    private String return_code;
    private String return_msg;
    private String result_code;
    private String err_code;
    private String err_code_des;
    private String appid;
    private String mch_id;
    private String sign;
    private String nonce_str;

    public boolean success(){
        return SUCCESS_CODE.equalsIgnoreCase(result_code) && SUCCESS_CODE.equalsIgnoreCase(return_code);
    }
    public boolean failed(){
        return !success();
    }
}
