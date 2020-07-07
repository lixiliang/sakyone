package json;

import lombok.Data;

@Data
public class InApp {
    private int quantity;
    private String product_id;
    private Long transaction_id;
    private String original_transaction_id;
    private String purchase_date;
    private Long purchase_date_ms;
    private String purchase_date_pst;
    private String original_purchase_date;
    private String original_purchase_date_ms;
    private String original_purchase_date_pst;
    private String expires_date;
    private String expires_date_ms;
    private String expires_date_pst;
    private Integer expiration_intent;
    //1 - App Store is still attempting to renew the subscription
    //0 - App Store has stopped attempting to renew the subscription
    private Integer is_in_billing_retry_period;
    //true-currently in the free trial period
    //false-not in free trial period
    private Boolean is_trial_period;
    private boolean is_in_intro_offer_period;
    private String cancellation_date;
//1 - Customer canceled their transaction due to an actual or perceived issue within your app.
//0 - Transaction was canceled for another reason, for example, if the customer made the purchase accidentally
    private Integer cancellation_reason;
    private String app_item_id;
    private String version_external_identifier;
    private String web_order_line_item_id;
    //1 - Subscription will renew at the end of the current subscription period.
    //0 - Customer has turned off automatic renewal for their subscription.
    //The value for this key should not be interpreted as the customer’s subscription status
    private Integer auto_renew_status;
    private String auto_renew_product_id;
    //1 - Customer has agreed to the price increase. Subscription will renew at the higher price.
    //0 - Customer has not taken action regarding the increased price. Subscription expires if the customer takes no action before the renewal date.
    private Integer price_consent_status;
}