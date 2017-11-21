package zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by admin on 2017/11/21.
 */
public class CuratorClient {
    private final static Logger LOGGER = LoggerFactory.getLogger(CuratorClient.class);
    public static void main(String[] args) throws Exception {
        String connectionInfo = "192.168.10.216:2181";
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        LOGGER.info("begin");
       /* CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(connectionInfo)
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .build();
        client.start();
        client.getData().forPath("/cfg/apps/push-api-mix/192.168.10.236/status");*/
        CuratorFramework client = CuratorFrameworkFactory.newClient(connectionInfo, retryPolicy);
        client.start();
        System.out.println("zk operation");
        client.close();

        LOGGER.info("end");

    }




}

