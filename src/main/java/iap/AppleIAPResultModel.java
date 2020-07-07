package iap;

import lombok.Data;

@Data
public class AppleIAPResultModel {
    private Integer status;
    private ReceiptModel receipt;
    //the base-64 encoded receipt
    private String latest_receipt;
    private String latest_receipt_info;
    private ReceiptModel latest_expired_receipt_info;
    private ReceiptModel pending_renewal_info;
}