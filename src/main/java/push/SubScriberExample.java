package push;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class SubScriberExample {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String[] topicFilters = {"topic1", "topic2"};
        String broker = "tcp://192.168.10.204:1883";
        String clientId = "paho-1";
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(false);
            System.out.println("Connecting to broker: " + broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            sampleClient.setCallback(new CustomMQTTCallBack());
            sampleClient.subscribe(topicFilters, new int[]{1, 1});
            System.out.println("Subscribe success for: " + topicFilters.toString());
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }

    }

}
class CustomMQTTCallBack implements MqttCallback {
    public void connectionLost(Throwable throwable) {
        System.out.println("conn lost.");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("messageArrived,topic:" + s + " msg:" + mqttMessage.toString());
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("deliveryComplete");
    }
}