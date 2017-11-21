package spring;

import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.rmq.MQProducer;
import spring.rmq.QueueListenter;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
    "classpath:/spring/applicationContext.xml"})

public class TestQueue{
    @Autowired
    MQProducer mqProducer;

    @Autowired
    QueueListenter queueListenter;

    final String queue_key = "test_queue_key";

    @Test
    public void send() throws IOException, InterruptedException {
        Map<String,Object> msg = Maps.newHashMap();
        Random random = new Random(100);
        for (int i = 0; i < 100; i++) {
            msg.put("data","hello,rabbmitmq ,num: "+ i);
            mqProducer.sendDataToQueue(queue_key,msg);
            Thread.sleep(2000);
        }
//        System.in.read();
    }

}