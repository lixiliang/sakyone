package zk;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import spring.rmq.MQProducer;

/**
 * Created by admin on 2017/11/21.
 */
public class ZKTest {
    private ZkClient zkClient = null;
    private final static Logger LOGGER = LoggerFactory.getLogger(ZKTest.class);
    private ZkClient zkInstance() {
        if (zkClient == null) {
            synchronized (ZKTest.class) {
                if (zkClient == null) {
//                    String zkString = "192.168.203.234:2181";
                    String zkString = "192.168.10.216:2181";
//                    String zkString = "10.10.50.120:2181";
                    zkClient = new ZkClient(zkString, 50000);
                    zkClient.setZkSerializer(new ZKStringSerializer());
                }
            }
        }
        return zkClient;
    }

    public static void main(String[] args) {
       /* ZKTest zk = new ZKTest();
        LOGGER.info("begin create client");
        ZkClient zkClient = zk.zkInstance();
        LOGGER.info("create client end");
        LOGGER.info("begin read");
        String status = zkClient.readData("/cfg/apps/push-api-mix/192.168.10.236/status", true);
        LOGGER.info("end read");*/
        System.out.println("connect begin ");

       ZkClient z = new ZkClient("192.168.10.216:2181",1000,10000,new SerializableSerializer());
       /*  ZkClient z2 = new ZkClient("192.168.10.216:2181",1000,10000,new SerializableSerializer());
        ZkClient z3 = new ZkClient("192.168.10.216:2181",1000,10000,new SerializableSerializer());*/
        System.out.println("conneted ok!");
    }
}
