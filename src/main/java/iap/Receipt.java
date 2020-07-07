package iap;

import com.alibaba.fastjson.annotation.JSONField;
import entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class Receipt extends BaseEntity {
    @JSONField(serialize=false)
    private String receipt_type;
    @JSONField(serialize=false)
    private String adam_id;
    @JSONField(serialize=false)
    private String app_item_id;
    private String bundle_id;
    @JSONField(serialize=false)
    private String application_version;
    @JSONField(serialize=false)
    private String download_id;
    @JSONField(serialize=false)
    private String version_external_identifier;
    private String receipt_creation_date;
    private String receipt_creation_date_ms;
    @JSONField(serialize=false)
    private String receipt_creation_date_pst;
    @JSONField(serialize=false)
    private String request_date;
    @JSONField(serialize=false)
    private String request_date_ms;
    @JSONField(serialize=false)
    private String request_date_pst;
    @JSONField(serialize=false)
    private String original_purchase_date;
    @JSONField(serialize=false)
    private String original_purchase_date_ms;
    @JSONField(serialize=false)
    private String original_purchase_date_pst;
    @JSONField(serialize=false)
    private String original_application_version;
    private List<InApp> in_app;
}