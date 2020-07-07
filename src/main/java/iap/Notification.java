package iap;

import entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**

 */
@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class Notification extends BaseEntity {
    private String latest_receipt;
    private LatestReceiptInfo latest_receipt_info;
    private String environment;
    private String auto_renew_status;
    private String password;
    private String auto_renew_product_id;
    private String notification_type;
    private String cancellation_date;
    private String cancellation_date_pst;
    private String cancellation_date_ms;
    private String web_order_line_item_id;
    private String latest_expired_receipt;
    private LatestExpiredReceiptInfo latest_expired_receipt_info;
    private String auto_renew_status_change_date;
    private String auto_renew_status_change_date_pst;
    private String auto_renew_status_change_date_ms;
}