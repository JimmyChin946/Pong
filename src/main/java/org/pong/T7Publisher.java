package org.pong;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
* Publishes data to the cloud with mqtt 
* 
* @author Jude Shin 
* 
*/
public class T7Publisher implements Runnable {
  // private final static String BROKER = "tcp://broker.hivemq.com:1883";
  // private final static String TOPIC = "cal-poly/csc/307/meee";
  // private final static String CLIENT_ID = "god-sender";
	private string broker;
	private string topic;
	private string id;
		
	public T7Publisher(String broker, String topic, String id) {
			this.broker = broker;	
			this.topic = topic;
			this.id = id;
	}

	public void setTopic(String topic) { this.topic = topic; };

  @Override
  public void run() {
    try {
      MqttClient client = new MqttClient(broker, id);
      client.connect();
      System.out.println("Connected to BROKER: " + broker);

      int counter = 0;
      while (true) {

				PublishItem publishItem = T7DataRepository.getInstance().popPublishQueue();
				if (publishItem == null) { continue; }
        
				//String content = (T7DataRepository.getInstance().getX()) + "," + (T7DataRepository.getInstance().getY());
				byte[] content publishItem.
        MqttMessage message = new MqttMessage(content.getBytes());
        message.setQos(2);
        if (client.isConnected())
          client.publish(topic, message);
        counter++;
        System.out.println("Message published: " + content);
        Thread.sleep(5000);
      }
    } catch (MqttException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}

