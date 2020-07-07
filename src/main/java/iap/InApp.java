package iap;

import com.alibaba.fastjson.annotation.JSONField;
import entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import utill.GTMDateUtil;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class InApp extends BaseEntity {
    private Integer quantity;
    private String product_id;
    private String transaction_id;
    private String original_transaction_id;



    private String purchase_date;
    private String purchase_date_china;
    @JSONField(serialize=false)
    private String purchase_date_ms;
    @JSONField(serialize=false)
    private String purchase_date_pst;
    private String original_purchase_date;
    @JSONField(serialize=false)
    private String original_purchase_date_ms;
    @JSONField(serialize=false)
    private String original_purchase_date_pst;
    private String expires_date;
    @JSONField(serialize=false)
    private String expires_date_ms;
    @JSONField(serialize=false)
    private String expires_date_pst;
    private String expiration_intent;
    //1 - App Store is still attempting to renew the subscription
    //0 - App Store has stopped attempting to renew the subscription
    private String is_in_billing_retry_period;
    //true-currently in the free trial period
    //false-not in free trial period
    private String is_trial_period;
    private String is_in_intro_offer_period;



    private String cancellation_date;
//1 - Customer canceled their transaction due to an actual or perceived issue within your app.
//0 - Transaction was canceled for another reason, for example, if the customer made the purchase accidentally
    private String cancellation_reason;
//    @JSONField(serialize=false)
    private String app_item_id;


    @JSONField(serialize=false)
    private String version_external_identifier;
    @JSONField(serialize=false)
    private String web_order_line_item_id;
    //1 - Subscription will renew at the end of the current subscription period.
    //0 - Customer has turned off automatic renewal for their subscription.
    //The value for this key should not be interpreted as the customer’s subscription status
    private String auto_renew_status;
    private String auto_renew_product_id;
    //1 - Customer has agreed to the price increase. Subscription will renew at the higher price.
    //0 - Customer has not taken action regarding the increased price. Subscription expires if the customer takes no action before the renewal date.
    @JSONField(serialize=false)
    private String price_consent_status;

    public String getPurchase_date() {
        if(StringUtils.isNotBlank(purchase_date)){
            return GTMDateUtil.GTMStr2ChinaStr(purchase_date);
        }
        return purchase_date;
    }

    public String getOriginal_purchase_date() {
        if(StringUtils.isNotBlank(original_purchase_date)){
            return GTMDateUtil.GTMStr2ChinaStr(original_purchase_date);
        }
        return original_purchase_date;
    }

    public String getExpires_date() {
        if(StringUtils.isNotBlank(expires_date)){
            return GTMDateUtil.GTMStr2ChinaStr(expires_date);
        }
        return expires_date;
    }

    public String getCancellation_date() {
        if(StringUtils.isNotBlank(cancellation_date)){
            return GTMDateUtil.GTMStr2ChinaStr(cancellation_date);
        }
        return cancellation_date;
    }
}