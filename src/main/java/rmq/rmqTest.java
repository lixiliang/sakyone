package rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
 
import java.io.IOException;
import java.util.Random;
 
class send extends Thread {
    private final static String QUEUE_NAME = "hello";
 
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.10.215");
        factory.setUsername("admin");
        factory.setPassword("admin");
 
        Random randomIntNumber = new Random();
 
        try {
            while (true) {
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
 
                channel.queueDeclare(QUEUE_NAME, true, false, false, null);
                String message = "Hello World!";
                Long ThreadID = send.currentThread().getId();
                message = message + " Thread ID: " + ThreadID.toString() + " Random Num: " + randomIntNumber.nextInt();
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println("Sent '" + message + "'");
                System.out.println("");
                channel.close();
                connection.close();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
 
 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
 
class receiver extends Thread {
    private final static String QUEUE_NAME = "hello";
 
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.10.215");
        factory.setUsername("admin");
        factory.setPassword("admin");
        try {
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
 
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
 
            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(QUEUE_NAME, true, consumer);
 
            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                String message = new String(delivery.getBody());
                System.out.println("Received '" + message + "'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
}
 
public class rmqTest {
 
    public static void main(String[] args) throws IOException {
      /*  send send = new send();
        send.start();*/
 
        receiver receiver = new receiver();
        receiver.start();
    }
}