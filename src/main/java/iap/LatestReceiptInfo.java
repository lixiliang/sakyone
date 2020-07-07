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
public class LatestReceiptInfo extends BaseEntity {
    @JSONField(serialize=false)
    private String original_purchase_date_pst;
    private Integer quantity;
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
    private Long purchase_date_ms;
    @JSONField(serialize=false)
    private String expires_date_formatted_pst;
    private String is_trial_period;
    @JSONField(serialize=false)
    private String item_id;
    @JSONField(serialize=false)
    private String unique_identifier;
    private String original_transaction_id;
    private String expires_date;
    private String cancellation_date;
    private String cancellation_reason;
    @JSONField(serialize=false)
    private Long expires_date_ms;
    @JSONField(serialize=false)
    private String expires_date_pst;
    private String transaction_id;
    @JSONField(serialize=false)
    private String bvrs;
    @JSONField(serialize=false)
    private String web_order_line_item_id;
    @JSONField(serialize=false)
    private String version_external_identifier;
    private String bid;
    private String product_id;
    private String purchase_date;
    @JSONField(serialize=false)
    private String purchase_date_pst;
    @JSONField(serialize=false)
    private String original_purchase_date;

    public String getExpires_date() {
        if(StringUtils.isNotBlank(expires_date)){
            return GTMDateUtil.GTMStr2ChinaStr(expires_date);
        }
        return expires_date;
    }

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

    public String getCancellation_date() {
        if(StringUtils.isNotBlank(cancellation_date)){
            return GTMDateUtil.GTMStr2ChinaStr(cancellation_date);
        }
        return cancellation_date;
    }
}