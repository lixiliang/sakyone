package iap;

import entity.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class TransactionInfo extends BaseEntity {
    private Integer quantity;
    private String productId;
    private String transactionId;
    private String originalTransactionId;
    private String purchaseDate;
    private String expiresDate;
    private Long expiresDateMs ;
    private String isInIntroOfferPeriod;
    private String isTrialPeriod;
    private PendingRenewalInfo pendingRenewalInfo;
}