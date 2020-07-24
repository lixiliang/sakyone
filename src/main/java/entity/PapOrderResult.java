package entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Accessors(chain = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
/**
 * 代扣结果通知
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class PapOrderResult extends WeChatBaseResult {
    private String device_info;
    private String openid;
    private String is_subscribe;
    private String bank_type;
    private String total_fee;
    private String fee_type;
    private String cash_fee;
    private String cash_fee_type;
    /**
     * 交易状态
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * ACCEPT—已接收，等待扣款
     * PAY_FAIL--支付失败(其他原因，如银行返回失败)
     */
    private String trade_state;
    private String coupon_fee;
    private String coupon_count;
    //微信支付订单号
    private String transaction_id;
    private String out_trade_no;
    private String attach;
    private String time_end;
    private String contract_id;
    private String trade_state_desc;
}
