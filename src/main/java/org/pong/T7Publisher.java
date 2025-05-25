package org.pong;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.util.Arrays; 

/**
 * Publishes data to the cloud with mqtt 
 * 
 * @author Jude Shin 
 * 
 */
public class T7Publisher implements Runnable {
	private String broker;
	private String topic;
	private String id;
	private final T7Game.PlayerType type;

	public T7Publisher(String broker, String topic, T7Game.PlayerType type) {
		this.broker = broker;
		this.topic = topic;
		this.id = type == T7Game.PlayerType.HOST ? "PongHostPublisher" : "PongClientPublisher";
		this.type = type;
	}

	@Override
	public void run() {
		try {
			MqttClient client = new MqttClient(broker, id);
			client.connect();
			System.out.println("Connected to BROKER: " + broker);

			while (true) {
				T7PublishItem publishItem = T7DataRepository.getInstance().popPublishQueue();
				if (publishItem == null) { continue; }

				byte[] content = publishItem.getMessage();
				String subTopic = publishItem.getSubTopic();
				String fullTopic = topic + "/" + subTopic;
				

				// checks to make sure we are publishing the correct things
				if (type == T7Game.PlayerType.HOST) {
					String[] allowedHostTopics = {"ball", "scoreHost", "scoreClient", "playerHost", "chat"};
					if (!Arrays.asList(allowedHostTopics).contains(subTopic)) {
						System.out.println("HOST is not allowed to publish the following topic: " + subTopic);
						continue;
					}
				}
				else if (type == T7Game.PlayerType.CLIENT) {
					String[] allowedClientTopics = {"playerClient", "chat"};
					if (!Arrays.asList(allowedClientTopics).contains(subTopic)) {
						System.out.println("CLIENT is not allowed to publish the following topic: " + subTopic);
						continue;
					}
				}

				MqttMessage message = new MqttMessage(content);
				message.setQos(0); // {"ball", "playerHost", "playerClient"};
				String[] priority2Topics = {"chat", "scoreClient", "scoreHost"};
				if (Arrays.asList(priority2Topics).contains(subTopic)) { message.setQos(2); }

				if (client.isConnected()) {
					client.publish(fullTopic, message);
				}
			}
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
}

