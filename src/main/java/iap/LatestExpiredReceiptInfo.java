/**
  * Copyright 2019 bejson.com 
  */
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
public class LatestExpiredReceiptInfo extends BaseEntity {
    @JSONField(serialize=false)
    private String original_purchase_date_pst;
    private String quantity;
    @JSONField(serialize=false)
    private String subscription_group_identifier;
    @JSONField(serialize=false)
    private String unique_vendor_identifier;
    @JSONField(serialize=false)
    private String original_purchase_date_ms;
    @JSONField(serialize=false)
    private String expires_date_formatted;
    private String is_in_intro_offer_period;
    @JSONField(serialize=false)
    private String purchase_date_ms;
    @JSONField(serialize=false)
    private String expires_date_formatted_pst;
    private String is_trial_period;
    @JSONField(serialize=false)
    private String item_id;
    @JSONField(serialize=false)
    private String unique_identifier;
    private String original_transaction_id;
    private String expires_date;
    private String transaction_id;
    @JSONField(serialize=false)
    private String bvrs;
    @JSONField(serialize=false)
    private String web_order_line_item_id;
    private String bid;
    private String product_id;
    private String purchase_date;
    @JSONField(serialize=false)
    private String purchase_date_pst;
    @JSONField(serialize=false)
    private String original_purchase_date;
}