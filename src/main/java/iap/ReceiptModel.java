package iap;

import lombok.Data;

@Data
public class ReceiptModel {
    public Integer adam_id;
    public Integer app_item_id;
    public Integer download_id;
    public Integer version_external_identifier;
    public String environment;
    public String receipt_type;
    public String bundle_id;
    public String application_version;
    public String receipt_creation_date;
    public String receipt_creation_date_ms;
    public String receipt_creation_date_pst;
    public String request_date;
    public String request_date_ms;
    public String request_date_pst;
    public String original_purchase_date;
    public String original_purchase_date_ms;
    public String original_purchase_date_pst;
    public String original_application_version;
    public InAppModel[] in_app;
}