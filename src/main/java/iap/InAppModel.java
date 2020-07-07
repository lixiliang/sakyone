package iap;

import lombok.Data;

@Data
public class InAppModel {
    public String quantity;
    public String product_id;
    public String transaction_id;
    public String original_transaction_id;
    public String purchase_date;
    public String purchase_date_ms;
    public String purchase_date_pst;
    public String original_purchase_date;
    public String original_purchase_date_ms;
    public String original_purchase_date_pst;
    public String expires_date;
    public Integer expiration_intent;
    //1 - App Store is still attempting to renew the subscription
    //0 - App Store has stopped attempting to renew the subscription
    public Integer is_in_billing_retry_period;
    //true-currently in the free trial period
    //false-not in free trial period
    public String is_trial_period;
    public String is_in_intro_offer_period;
    public String cancellation_date;
//1 - Customer canceled their transaction due to an actual or perceived issue within your app.
//0 - Transaction was canceled for another reason, for example, if the customer made the purchase accidentally
    public Integer cancellation_reason;
    public String app_item_id;
    public String version_external_identifier;
    public String web_order_line_item_id;
    //1 - Subscription will renew at the end of the current subscription period.
    //0 - Customer has turned off automatic renewal for their subscription.
    //The value for this key should not be interpreted as the customer’s subscription status
    public Integer auto_renew_status;
    public String auto_renew_product_id;
    //1 - Customer has agreed to the price increase. Subscription will renew at the higher price.
    //0 - Customer has not taken action regarding the increased price. Subscription expires if the customer takes no action before the renewal date.
    public Integer price_consent_status;

}