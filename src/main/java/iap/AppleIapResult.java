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
public class AppleIapResult extends BaseEntity {

    private int status;
    private String environment;
    @JSONField(serialize=false)
    private Receipt receipt;
    private List<LatestReceiptInfo> latest_receipt_info;
    @JSONField(serialize=false)
    private String latest_receipt;
    private List<PendingRenewalInfo> pending_renewal_info;
}