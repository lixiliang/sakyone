package rmq;

import com.rabbitmq.client.*;
import com.sun.deploy.util.StringUtils;

import java.io.IOException;
import java.lang.String;
import java.lang.System;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {
    //exchange type
    public enum XT {
        DIRECT, TOPIC, HEADERS, FANOUT
    }

    private static final String QUEUE_NAME = "log2";

    private static Scanner scanner = new Scanner(System.in);
    private static String message = "";
    public static String XCHG_NAME = "ex_test";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.10.215");
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection(); //声明一个连接
        Channel channel = connection.createChannel(); //声明消息通道

        //exchange类型 参考:http://stephansun.iteye.com/blog/1452853
        XT xt = XT.DIRECT;
        switch (xt) {
            case FANOUT:
                //广播给所有队列  接收方也必须通过fanout交换机获取消息,所有连接到该交换机的consumer均可获取消息
                //如果producer在发布消息时没有consumer在监听，消息将被丢弃
                //定义一个交换机
                //参数1：交换机名称
                //参数2：交换机类型
                //参数3：交换机持久性，如果为true则服务器重启时不会丢失
                //参数4：交换机在不被使用时是否删除
                //参数5：交换机的其他属性
                channel.exchangeDeclare(XCHG_NAME, "fanout", true, true, null);
                while (GetInputString()) {
                    //发送一条广播消息,参数2此时无意义
                    channel.basicPublish(XCHG_NAME, "", null, message.getBytes());

                    System.out.println("Send " + message);
                }
                break;
            case DIRECT:
                //向所有绑定了相应routing key的队列发送消息
                //如果producer在发布消息时没有consumer在监听，消息将被丢弃
                //如果有多个consumer监听了相同的routing key  则他们都会受到消息
                channel.exchangeDeclare(XCHG_NAME, "direct", true, false, null);

                while (GetInputString()) {
                    //input like : info message
                    String[] temp = StringUtils.splitString(message, " ");
                    channel.basicPublish(XCHG_NAME, temp[0], null, temp[1].getBytes());
                    System.out.println("Send " + message);
                }
                break;
            case TOPIC:
                //与direct模式有类似之处，都使用routing key作为路由
                //不同之处在于direct模式只能指定固定的字符串，而topic可以指定一个字符串模式
                channel.exchangeDeclare(XCHG_NAME, "topic", true, true, null);
                while (GetInputString()) {
                    //input like : topic message
                    String[] temp = StringUtils.splitString(message, " ");
                    channel.basicPublish(XCHG_NAME, temp[0], null, temp[1].getBytes());
                    System.out.println("Send " + message);
                }
                break;
            case HEADERS:
                //与topic和direct有一定相似之处，但不是通过routing key来路由消息
                //通过headers中词的匹配来进行路由
                channel.exchangeDeclare(XCHG_NAME, "headers", true, true, null);
                while (GetInputString()) {
                    //input like : headers message
                    String[] temp = StringUtils.splitString(message, " ");

                    Map<String, Object> headers = new HashMap<String, Object>();
                    headers.put("name", temp[0]); //定义headers
                    headers.put("sex", temp[1]);
                    AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder().headers(headers);

                    channel.basicPublish(XCHG_NAME, "", builder.build(), temp[2].getBytes()); //根据headers路由到相应的consumer
                    System.out.println("Send " + message);
                }
                break;
        }
        channel.close();
        connection.close();
    }

    private static boolean GetInputString() {
        message = scanner.nextLine();
        if (message.length() == 0) return false;
        return true;
    }
}