package iap;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

@Slf4j
public class IapResultUtilTest {

    @Test
    public void getLatestReceiptInfo() {
        String iap ="{\n" +
                "\t\"environment\": \"Production\",\n" +
                "\t\"receipt\": {\n" +
                "\t\t\"receipt_type\": \"Production\",\n" +
                "\t\t\"adam_id\": 1314769817,\n" +
                "\t\t\"app_item_id\": 1314769817,\n" +
                "\t\t\"bundle_id\": \"com.tvbc.maiduidui\",\n" +
                "\t\t\"application_version\": \"2020110501\",\n" +
                "\t\t\"download_id\": 31084072972665,\n" +
                "\t\t\"version_external_identifier\": 838446228,\n" +
                "\t\t\"receipt_creation_date\": \"2020-11-26 08:29:48 Etc/GMT\",\n" +
                "\t\t\"receipt_creation_date_ms\": \"1606379388000\",\n" +
                "\t\t\"receipt_creation_date_pst\": \"2020-11-26 00:29:48 America/Los_Angeles\",\n" +
                "\t\t\"request_date\": \"2022-04-06 05:46:57 Etc/GMT\",\n" +
                "\t\t\"request_date_ms\": \"1649224017095\",\n" +
                "\t\t\"request_date_pst\": \"2022-04-05 22:46:57 America/Los_Angeles\",\n" +
                "\t\t\"original_purchase_date\": \"2020-08-20 11:47:01 Etc/GMT\",\n" +
                "\t\t\"original_purchase_date_ms\": \"1597924021000\",\n" +
                "\t\t\"original_purchase_date_pst\": \"2020-08-20 04:47:01 America/Los_Angeles\",\n" +
                "\t\t\"original_application_version\": \"2020081901\",\n" +
                "\t\t\"in_app\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"quantity\": \"1\",\n" +
                "\t\t\t\t\"product_id\": \"com.tvbc.new3.vip.1m.subscribe\",\n" +
                "\t\t\t\t\"transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\t\"original_transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\t\"purchase_date\": \"2020-11-26 08:29:44 Etc/GMT\",\n" +
                "\t\t\t\t\"purchase_date_ms\": \"1606379384000\",\n" +
                "\t\t\t\t\"purchase_date_pst\": \"2020-11-26 00:29:44 America/Los_Angeles\",\n" +
                "\t\t\t\t\"original_purchase_date\": \"2020-11-26 08:29:48 Etc/GMT\",\n" +
                "\t\t\t\t\"original_purchase_date_ms\": \"1606379388000\",\n" +
                "\t\t\t\t\"original_purchase_date_pst\": \"2020-11-26 00:29:48 America/Los_Angeles\",\n" +
                "\t\t\t\t\"expires_date\": \"2020-12-26 08:29:44 Etc/GMT\",\n" +
                "\t\t\t\t\"expires_date_ms\": \"1608971384000\",\n" +
                "\t\t\t\t\"expires_date_pst\": \"2020-12-26 00:29:44 America/Los_Angeles\",\n" +
                "\t\t\t\t\"web_order_line_item_id\": \"110000347470483\",\n" +
                "\t\t\t\t\"is_trial_period\": \"false\",\n" +
                "\t\t\t\t\"is_in_intro_offer_period\": \"false\"\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t},\n" +
                "\t\"latest_receipt_info\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": \"1\",\n" +
                "\t\t\t\"product_id\": \"com.tvbc.new3.vip.1m.subscribe\",\n" +
                "\t\t\t\"transaction_id\": \"110001032230034\",\n" +
                "\t\t\t\"original_transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\"purchase_date\": \"2021-06-14 13:49:10 Etc/GMT\",\n" +
                "\t\t\t\"purchase_date_ms\": \"1623678550000\",\n" +
                "\t\t\t\"purchase_date_pst\": \"2021-06-14 06:49:10 America/Los_Angeles\",\n" +
                "\t\t\t\"original_purchase_date\": \"2020-11-26 08:29:48 Etc/GMT\",\n" +
                "\t\t\t\"original_purchase_date_ms\": \"1606379388000\",\n" +
                "\t\t\t\"original_purchase_date_pst\": \"2020-11-26 00:29:48 America/Los_Angeles\",\n" +
                "\t\t\t\"expires_date\": \"2021-07-14 13:49:10 Etc/GMT\",\n" +
                "\t\t\t\"expires_date_ms\": \"1626270550000\",\n" +
                "\t\t\t\"expires_date_pst\": \"2021-07-14 06:49:10 America/Los_Angeles\",\n" +
                "\t\t\t\"cancellation_date\": \"2021-07-15 03:25:54 Etc/GMT\",\n" +
                "\t\t\t\"cancellation_date_ms\": \"1626319554000\",\n" +
                "\t\t\t\"cancellation_date_pst\": \"2021-07-14 20:25:54 America/Los_Angeles\",\n" +
                "\t\t\t\"web_order_line_item_id\": \"110000417112503\",\n" +
                "\t\t\t\"is_trial_period\": \"false\",\n" +
                "\t\t\t\"is_in_intro_offer_period\": \"false\",\n" +
                "\t\t\t\"cancellation_reason\": \"0\",\n" +
                "\t\t\t\"in_app_ownership_type\": \"PURCHASED\",\n" +
                "\t\t\t\"subscription_group_identifier\": \"20560972\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": \"1\",\n" +
                "\t\t\t\"product_id\": \"com.tvbc.new3.vip.1m.subscribe\",\n" +
                "\t\t\t\"transaction_id\": \"110001006880866\",\n" +
                "\t\t\t\"original_transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\"purchase_date\": \"2021-05-12 05:22:43 Etc/GMT\",\n" +
                "\t\t\t\"purchase_date_ms\": \"1620796963000\",\n" +
                "\t\t\t\"purchase_date_pst\": \"2021-05-11 22:22:43 America/Los_Angeles\",\n" +
                "\t\t\t\"original_purchase_date\": \"2020-11-26 08:29:48 Etc/GMT\",\n" +
                "\t\t\t\"original_purchase_date_ms\": \"1606379388000\",\n" +
                "\t\t\t\"original_purchase_date_pst\": \"2020-11-26 00:29:48 America/Los_Angeles\",\n" +
                "\t\t\t\"expires_date\": \"2021-06-12 05:22:43 Etc/GMT\",\n" +
                "\t\t\t\"expires_date_ms\": \"1623475363000\",\n" +
                "\t\t\t\"expires_date_pst\": \"2021-06-11 22:22:43 America/Los_Angeles\",\n" +
                "\t\t\t\"cancellation_date\": \"2021-05-13 09:20:18 Etc/GMT\",\n" +
                "\t\t\t\"cancellation_date_ms\": \"1620897618000\",\n" +
                "\t\t\t\"cancellation_date_pst\": \"2021-05-13 02:20:18 America/Los_Angeles\",\n" +
                "\t\t\t\"web_order_line_item_id\": \"110000392158827\",\n" +
                "\t\t\t\"is_trial_period\": \"false\",\n" +
                "\t\t\t\"is_in_intro_offer_period\": \"false\",\n" +
                "\t\t\t\"cancellation_reason\": \"0\",\n" +
                "\t\t\t\"in_app_ownership_type\": \"PURCHASED\",\n" +
                "\t\t\t\"subscription_group_identifier\": \"20560972\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": \"1\",\n" +
                "\t\t\t\"product_id\": \"com.tvbc.new3.vip.1m.subscribe\",\n" +
                "\t\t\t\"transaction_id\": \"110000961328965\",\n" +
                "\t\t\t\"original_transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\"purchase_date\": \"2021-03-14 09:10:55 Etc/GMT\",\n" +
                "\t\t\t\"purchase_date_ms\": \"1615713055000\",\n" +
                "\t\t\t\"purchase_date_pst\": \"2021-03-14 01:10:55 America/Los_Angeles\",\n" +
                "\t\t\t\"original_purchase_date\": \"2020-11-26 08:29:48 Etc/GMT\",\n" +
                "\t\t\t\"original_purchase_date_ms\": \"1606379388000\",\n" +
                "\t\t\t\"original_purchase_date_pst\": \"2020-11-26 00:29:48 America/Los_Angeles\",\n" +
                "\t\t\t\"expires_date\": \"2021-04-14 08:10:55 Etc/GMT\",\n" +
                "\t\t\t\"expires_date_ms\": \"1618387855000\",\n" +
                "\t\t\t\"expires_date_pst\": \"2021-04-14 01:10:55 America/Los_Angeles\",\n" +
                "\t\t\t\"cancellation_date\": \"2021-03-25 01:03:27 Etc/GMT\",\n" +
                "\t\t\t\"cancellation_date_ms\": \"1616634207000\",\n" +
                "\t\t\t\"cancellation_date_pst\": \"2021-03-24 18:03:27 America/Los_Angeles\",\n" +
                "\t\t\t\"web_order_line_item_id\": \"110000359206113\",\n" +
                "\t\t\t\"is_trial_period\": \"false\",\n" +
                "\t\t\t\"is_in_intro_offer_period\": \"false\",\n" +
                "\t\t\t\"cancellation_reason\": \"1\",\n" +
                "\t\t\t\"in_app_ownership_type\": \"PURCHASED\",\n" +
                "\t\t\t\"subscription_group_identifier\": \"20560972\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": \"1\",\n" +
                "\t\t\t\"product_id\": \"com.tvbc.new3.vip.1m.subscribe\",\n" +
                "\t\t\t\"transaction_id\": \"110000894961827\",\n" +
                "\t\t\t\"original_transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\"purchase_date\": \"2020-12-26 08:29:44 Etc/GMT\",\n" +
                "\t\t\t\"purchase_date_ms\": \"1608971384000\",\n" +
                "\t\t\t\"purchase_date_pst\": \"2020-12-26 00:29:44 America/Los_Angeles\",\n" +
                "\t\t\t\"original_purchase_date\": \"2020-11-26 08:29:48 Etc/GMT\",\n" +
                "\t\t\t\"original_purchase_date_ms\": \"1606379388000\",\n" +
                "\t\t\t\"original_purchase_date_pst\": \"2020-11-26 00:29:48 America/Los_Angeles\",\n" +
                "\t\t\t\"expires_date\": \"2021-01-26 08:29:44 Etc/GMT\",\n" +
                "\t\t\t\"expires_date_ms\": \"1611649784000\",\n" +
                "\t\t\t\"expires_date_pst\": \"2021-01-26 00:29:44 America/Los_Angeles\",\n" +
                "\t\t\t\"cancellation_date\": \"2020-12-29 01:14:22 Etc/GMT\",\n" +
                "\t\t\t\"cancellation_date_ms\": \"1609204462000\",\n" +
                "\t\t\t\"cancellation_date_pst\": \"2020-12-28 17:14:22 America/Los_Angeles\",\n" +
                "\t\t\t\"web_order_line_item_id\": \"110000347470484\",\n" +
                "\t\t\t\"is_trial_period\": \"false\",\n" +
                "\t\t\t\"is_in_intro_offer_period\": \"false\",\n" +
                "\t\t\t\"cancellation_reason\": \"0\",\n" +
                "\t\t\t\"in_app_ownership_type\": \"PURCHASED\",\n" +
                "\t\t\t\"subscription_group_identifier\": \"20560972\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"quantity\": \"1\",\n" +
                "\t\t\t\"product_id\": \"com.tvbc.new3.vip.1m.subscribe\",\n" +
                "\t\t\t\"transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\"original_transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\"purchase_date\": \"2020-11-26 08:29:44 Etc/GMT\",\n" +
                "\t\t\t\"purchase_date_ms\": \"1606379384000\",\n" +
                "\t\t\t\"purchase_date_pst\": \"2020-11-26 00:29:44 America/Los_Angeles\",\n" +
                "\t\t\t\"original_purchase_date\": \"2020-11-26 08:29:48 Etc/GMT\",\n" +
                "\t\t\t\"original_purchase_date_ms\": \"1606379388000\",\n" +
                "\t\t\t\"original_purchase_date_pst\": \"2020-11-26 00:29:48 America/Los_Angeles\",\n" +
                "\t\t\t\"expires_date\": \"2020-12-26 08:29:44 Etc/GMT\",\n" +
                "\t\t\t\"expires_date_ms\": \"1608971384000\",\n" +
                "\t\t\t\"expires_date_pst\": \"2020-12-26 00:29:44 America/Los_Angeles\",\n" +
                "\t\t\t\"web_order_line_item_id\": \"110000347470483\",\n" +
                "\t\t\t\"is_trial_period\": \"false\",\n" +
                "\t\t\t\"is_in_intro_offer_period\": \"false\",\n" +
                "\t\t\t\"in_app_ownership_type\": \"PURCHASED\",\n" +
                "\t\t\t\"subscription_group_identifier\": \"20560972\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"latest_receipt\": \"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\",\n" +
                "\t\"pending_renewal_info\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"expiration_intent\": \"1\",\n" +
                "\t\t\t\"auto_renew_product_id\": \"com.tvbc.new3.vip.1m.subscribe\",\n" +
                "\t\t\t\"is_in_billing_retry_period\": \"0\",\n" +
                "\t\t\t\"product_id\": \"com.tvbc.new3.vip.1m.subscribe\",\n" +
                "\t\t\t\"original_transaction_id\": \"110000873852613\",\n" +
                "\t\t\t\"auto_renew_status\": \"0\"\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"status\": 0\n" +
                "}";
        AppleIapResult iapResult = JSON.parseObject(iap,AppleIapResult.class);
        boolean rs = checkAppleIapResult(iapResult,"110000873852613",true);
//        LatestReceiptInfo latestReceiptInfo = IapResultUtil.getLatestReceiptInfo(iapResult);
        log.info("{}",rs);
    }
    private boolean checkAppleIapResult(AppleIapResult result, String transactionId, Boolean isAutoRenew) {
        if (result == null) {
            return false;
        }
        if (result.getStatus() != 0) {
            return false;
        }
        if (result.getReceipt() == null) {
            return false;
        }

        List<InApp> inApps = result.getReceipt().getIn_app();
        Optional<InApp> transactionInApp = inApps.stream().filter(inApp -> inApp.getTransaction_id().equals(transactionId)).findAny();
        //注意:自动续费类型的苹果票据并不总是会返回transactionId对应的记录的,因此这里只能验证非自动续费的
        if (!isAutoRenew) {
            if (!transactionInApp.isPresent()) {
                log.warn("no transaction matched in receipt inApp,transactionId:{} ", transactionId);
                return false;
            }
        }
        //发现存在用户拿退费后的票据进行验单的情况，
        // 因此增加判断若transactionId已经退费，则视为非法
        boolean refund = result.getLatest_receipt_info().stream().filter(latest -> latest.getTransaction_id().equals(transactionId) && StringUtils.isNotBlank(latest.getCancellation_date())).findAny().isPresent();
        if (refund) {
            log.warn("transaction: {} has refund",transactionId);
            return false;
        }
        return true;
    }
}