package iap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class IapResultUtil {

    /**
     * 获取自动订阅的信息
     *
     * @param originalTransactionId
     * @param iapResult
     * @return
     */
    public static PendingRenewalInfo getPendingRenewalInfo(String originalTransactionId, AppleIapResult iapResult) {
        PendingRenewalInfo result = null;
        if (iapResult != null) {
            List<PendingRenewalInfo> infos = iapResult.getPending_renewal_info();
            if (CollectionUtils.isNotEmpty(infos)) {
                Optional<PendingRenewalInfo> pendingRenewal = infos.stream().filter(item -> item.getOriginal_transaction_id().equals(originalTransactionId)).findAny();
                result = pendingRenewal.orElse(null);
            }
        }
        return result;
    }

    public static TransactionInfo buildTransactionInfo(LatestReceiptInfo latestReceiptInfo) {
        TransactionInfo result = new TransactionInfo();
        result.setProductId(latestReceiptInfo.getProduct_id());
        result.setQuantity(latestReceiptInfo.getQuantity());
        result.setOriginalTransactionId(latestReceiptInfo.getOriginal_transaction_id());
        result.setPurchaseDate(latestReceiptInfo.getPurchase_date());
        result.setTransactionId(latestReceiptInfo.getTransaction_id());
        result.setExpiresDate(latestReceiptInfo.getExpires_date());
        result.setIsTrialPeriod(latestReceiptInfo.getIs_trial_period());
        result.setIsInIntroOfferPeriod(latestReceiptInfo.getIs_in_intro_offer_period());
        return result;
    }

    public static TransactionInfo buildTransactionInfo(InApp inApp) {
        TransactionInfo result = new TransactionInfo();
        result.setProductId(inApp.getProduct_id());
        result.setQuantity(inApp.getQuantity());
        result.setOriginalTransactionId(inApp.getOriginal_transaction_id());
        result.setPurchaseDate(inApp.getPurchase_date());
        result.setTransactionId(inApp.getTransaction_id());
        result.setExpiresDate(inApp.getExpires_date());
        result.setIsTrialPeriod(inApp.getIs_trial_period());
        result.setIsInIntroOfferPeriod(inApp.getIs_in_intro_offer_period());
        return result;
    }


    /**
     * 获取本次交易中的原始交易数据
     *
     * @param transactionId
     * @param iapResult
     * @return
     */
    public static InApp getTransactionInApp(String transactionId, AppleIapResult iapResult) {
        InApp result = null;
        if (iapResult != null) {
            List<InApp> inApps = iapResult.getReceipt().getIn_app();
            Optional<InApp> transactionInApp = inApps.stream().filter(inApp -> inApp.getTransaction_id().equals(transactionId)).findAny();
            result = transactionInApp.orElse(null);
        }
        return result;
    }



    public static LatestReceiptInfo getLatestReceiptInfo(AppleIapResult iapResult) {
        List<LatestReceiptInfo> latestReceiptInfos = iapResult.getLatest_receipt_info();
        if (CollectionUtils.isEmpty(latestReceiptInfos)) {
            return null;
        } else {
            Optional<LatestReceiptInfo> latestReceiptInfo = latestReceiptInfos.stream()
                    .filter(receipt-> receipt.getProduct_id().indexOf("subscribe")>-1
                    && StringUtils.isBlank(receipt.getCancellation_date()))
                    .max(Comparator.comparingLong(LatestReceiptInfo::getPurchase_date_ms));
            return latestReceiptInfo.orElse(null);
        }
    }

    /**
     * 获取除了最新记录之外的最新n条记录
     *
     * @param iapResult
     * @param limit
     * @return
     */
    public static List<LatestReceiptInfo> getLatestNTransaction(AppleIapResult iapResult, int limit) {
        List<LatestReceiptInfo> latestReceiptInfos = iapResult.getLatest_receipt_info();
        if (CollectionUtils.isEmpty(latestReceiptInfos)) {
            return null;
        } else {
            List<LatestReceiptInfo> latestList = latestReceiptInfos.stream()
                    .filter(receipt-> receipt.getProduct_id().indexOf("subscribe")>-1)
                    .sorted(Comparator.comparingLong(LatestReceiptInfo::getPurchase_date_ms).reversed())
                    .skip(1)
                    .limit(limit)
                    .collect(Collectors.toList());
             Collections.reverse(latestList);
            return latestList;
        }
    }

    /**
     * 获取本次交易号中的最新receipt info
     *
     * @param transactionId
     * @param iapResult
     * @return
     */
    public static LatestReceiptInfo getTransactionLatestReceiptInfo(String transactionId, AppleIapResult iapResult) {
        List<LatestReceiptInfo> latestReceiptInfos = iapResult.getLatest_receipt_info();
        if (CollectionUtils.isEmpty(latestReceiptInfos)) {
            return null;
        } else {
            Optional<LatestReceiptInfo> transactionInLatestReceiptInfo = latestReceiptInfos.stream().filter(item -> item.getTransaction_id().equals(transactionId)).findAny();
            return transactionInLatestReceiptInfo.orElse(null);
        }
    }


}
