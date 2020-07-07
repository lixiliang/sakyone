package iap;


import com.alibaba.fastjson.annotation.JSONField;
import entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class PendingRenewalInfo extends BaseEntity {

    private String auto_renew_product_id;
    //0代表已经关闭订阅
    //1 - Subscription will renew at the end of the current subscription period.
    //0 - Customer has turned off automatic renewal for their subscription.
    //The value for this key should not be interpreted as the customer’s subscription status
    private String auto_renew_status;
    /**
     * 1
     * The customer voluntarily canceled their subscription.
     *
     * 2
     * Billing error; for example, the customer's payment information was no longer valid.
     *
     * 3
     * The customer did not agree to a recent price increase.
     *
     * 4
     * The product was not available for purchase at the time of renewal.
     *
     * 5
     * Unknown error.
     */
    private String expiration_intent;
    @JSONField(serialize=false)
    private String grace_period_expires_date;
    @JSONField(serialize=false)
    private String grace_period_expires_date_ms;
    @JSONField(serialize=false)
    private String grace_period_expires_date_pst;
    private String is_in_billing_retry_period;
    private String original_transaction_id;
    @JSONField(serialize=false)
    private String price_consent_status;
    private String product_id;
}