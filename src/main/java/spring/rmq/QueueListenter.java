package spring.rmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class QueueListenter implements MessageListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(MQProducer.class);
    @Override
    public void onMessage(Message message) {
        try{
            LOGGER.info("onMessage:"+message.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}