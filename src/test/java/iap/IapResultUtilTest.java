package iap;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.*;
@Slf4j
public class IapResultUtilTest {

    @Test
    public void getLatestReceiptInfo() {
        String iap ="{\"status\":0, \"environment\":\"Production\", \n" +
                "\"receipt\":{\"receipt_type\":\"Production\", \"adam_id\":1314769817, \"app_item_id\":1314769817, \"bundle_id\":\"com.tvbc.maiduidui\", \"application_version\":\"2019121903\", \"download_id\":95035725190615, \"version_external_identifier\":834078446, \"receipt_creation_date\":\"2020-01-10 04:06:44 Etc/GMT\", \"receipt_creation_date_ms\":\"1578629204000\", \"receipt_creation_date_pst\":\"2020-01-09 20:06:44 America/Los_Angeles\", \"request_date\":\"2020-02-19 06:43:34 Etc/GMT\", \"request_date_ms\":\"1582094614488\", \"request_date_pst\":\"2020-02-18 22:43:34 America/Los_Angeles\", \"original_purchase_date\":\"2018-10-01 02:18:56 Etc/GMT\", \"original_purchase_date_ms\":\"1538360336000\", \"original_purchase_date_pst\":\"2018-09-30 19:18:56 America/Los_Angeles\", \"original_application_version\":\"2018091201\", \n" +
                "\"in_app\":[\n" +
                "{\"quantity\":\"1\", \"product_id\":\"com.tvbc.new3.vip.1m.subscribe\", \"transaction_id\":\"550000542684250\", \"original_transaction_id\":\"550000529041885\", \"purchase_date\":\"2020-01-10 04:06:42 Etc/GMT\", \"purchase_date_ms\":\"1578629202000\", \"purchase_date_pst\":\"2020-01-09 20:06:42 America/Los_Angeles\", \"original_purchase_date\":\"2019-12-08 23:49:12 Etc/GMT\", \"original_purchase_date_ms\":\"1575848952000\", \"original_purchase_date_pst\":\"2019-12-08 15:49:12 America/Los_Angeles\", \"expires_date\":\"2020-02-10 04:06:42 Etc/GMT\", \"expires_date_ms\":\"1581307602000\", \"expires_date_pst\":\"2020-02-09 20:06:42 America/Los_Angeles\", \"web_order_line_item_id\":\"550000179471362\", \"is_trial_period\":\"false\", \"is_in_intro_offer_period\":\"false\"}, \n" +
                "{\"quantity\":\"1\", \"product_id\":\"com.tvbc.new3.vip.1m.subscribe.discount\", \"transaction_id\":\"550000529041885\", \"original_transaction_id\":\"550000529041885\", \"purchase_date\":\"2019-12-08 23:49:10 Etc/GMT\", \"purchase_date_ms\":\"1575848950000\", \"purchase_date_pst\":\"2019-12-08 15:49:10 America/Los_Angeles\", \"original_purchase_date\":\"2019-12-08 23:49:12 Etc/GMT\", \"original_purchase_date_ms\":\"1575848952000\", \"original_purchase_date_pst\":\"2019-12-08 15:49:12 America/Los_Angeles\", \"expires_date\":\"2020-01-08 23:49:10 Etc/GMT\", \"expires_date_ms\":\"1578527350000\", \"expires_date_pst\":\"2020-01-08 15:49:10 America/Los_Angeles\", \"web_order_line_item_id\":\"550000179471361\", \"is_trial_period\":\"false\", \"is_in_intro_offer_period\":\"true\"}]}, \n" +
                "\"latest_receipt_info\":[\n" +
                "{\"quantity\":\"1\", \"product_id\":\"com.tvbc.new3.vip.1m.subscribe.discount\", \"transaction_id\":\"550000529041885\", \"original_transaction_id\":\"550000529041885\", \"purchase_date\":\"2019-12-08 23:49:10 Etc/GMT\", \"purchase_date_ms\":\"1575848950000\", \"purchase_date_pst\":\"2019-12-08 15:49:10 America/Los_Angeles\", \"original_purchase_date\":\"2019-12-08 23:49:12 Etc/GMT\", \"original_purchase_date_ms\":\"1575848952000\", \"original_purchase_date_pst\":\"2019-12-08 15:49:12 America/Los_Angeles\", \"expires_date\":\"2020-01-08 23:49:10 Etc/GMT\", \"expires_date_ms\":\"1578527350000\", \"expires_date_pst\":\"2020-01-08 15:49:10 America/Los_Angeles\", \"web_order_line_item_id\":\"550000179471361\", \"is_trial_period\":\"false\", \"is_in_intro_offer_period\":\"true\", \"subscription_group_identifier\":\"20560972\"}, \n" +
                "{\"quantity\":\"1\", \"product_id\":\"com.tvbc.new3.vip.1m.subscribe\", \"transaction_id\":\"550000542684250\", \"original_transaction_id\":\"550000529041885\", \"purchase_date\":\"2020-01-10 04:06:42 Etc/GMT\", \"purchase_date_ms\":\"1578629202000\", \"purchase_date_pst\":\"2020-01-09 20:06:42 America/Los_Angeles\", \"original_purchase_date\":\"2019-12-08 23:49:12 Etc/GMT\", \"original_purchase_date_ms\":\"1575848952000\", \"original_purchase_date_pst\":\"2019-12-08 15:49:12 America/Los_Angeles\", \"expires_date\":\"2020-02-10 04:06:42 Etc/GMT\", \"expires_date_ms\":\"1581307602000\", \"expires_date_pst\":\"2020-02-09 20:06:42 America/Los_Angeles\", \"web_order_line_item_id\":\"550000179471362\", \"is_trial_period\":\"false\", \"is_in_intro_offer_period\":\"false\", \"subscription_group_identifier\":\"20560972\"}, \n" +
                "{\"quantity\":\"1\", \"product_id\":\"com.tvbc.new3.vip.1m.subscribe\", \"transaction_id\":\"550000561143508\", \"original_transaction_id\":\"550000529041885\", \"purchase_date\":\"2020-02-17 09:55:08 Etc/GMT\", \"purchase_date_ms\":\"1581933308000\", \"purchase_date_pst\":\"2020-02-17 01:55:08 America/Los_Angeles\", \"original_purchase_date\":\"2019-12-08 23:49:12 Etc/GMT\", \"original_purchase_date_ms\":\"1575848952000\", \"original_purchase_date_pst\":\"2019-12-08 15:49:12 America/Los_Angeles\", \"expires_date\":\"2020-03-17 08:55:08 Etc/GMT\", \"expires_date_ms\":\"1584435308000\", \"expires_date_pst\":\"2020-03-17 01:55:08 America/Los_Angeles\", \"cancellation_date\":\"2020-02-18 02:31:54 Etc/GMT\", \"cancellation_date_ms\":\"1581993114000\", \"cancellation_date_pst\":\"2020-02-17 18:31:54 America/Los_Angeles\", \"web_order_line_item_id\":\"550000187194165\", \"is_trial_period\":\"false\", \"is_in_intro_offer_period\":\"false\", \"cancellation_reason\":\"0\", \"subscription_group_identifier\":\"20560972\"}], \n" +
                "\"latest_receipt\":\"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\", \n" +
                "\"pending_renewal_info\":[\n" +
                "{\"auto_renew_product_id\":\"com.tvbc.new3.vip.1m.subscribe\", \"original_transaction_id\":\"550000529041885\", \"product_id\":\"com.tvbc.new3.vip.1m.subscribe\", \"auto_renew_status\":\"0\"}]}\n";
        AppleIapResult iapResult = JSON.parseObject(iap,AppleIapResult.class);
        LatestReceiptInfo latestReceiptInfo = IapResultUtil.getLatestReceiptInfo(iapResult);
        log.info("{}",latestReceiptInfo);
    }
}