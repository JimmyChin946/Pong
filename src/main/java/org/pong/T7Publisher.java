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
	private String broker;
	private String topic;
	private String id;
	private final boolean isHost;

	public T7Publisher(String broker, String topic, String id, boolean isHost) {
		this.broker = broker;
		this.topic = topic;
		this.id = id;
		this.isHost = isHost;
	}

	public void setTopic(String topic) { this.topic = topic; };

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
				if (isHost
						&& (!((subTopic == "ball") || (subTopic == "scoreHost") || (subTopic == "scoreClient") || (subTopic == "playerHost") || (subTopic == "chat")))) {
					// the host should only be able to publish ball, scoreHost, scoreClient, playerHost, and chat 
					System.out.println("HOST is not allowed to publish the following topic: " + subTopic);
					continue;
				}
				else if (!((subTopic == "playerClient") || (subTopic == "chat"))) {
					// the client should only be able to publish the playerClient, and chat
					System.out.println("CLIENT is not allowed to publish the following topic: " + subTopic);
					continue;
				}

				MqttMessage message = new MqttMessage(content);
				message.setQos(2);

				if (client.isConnected()) {
					client.publish(fullTopic, message);
				}

				Thread.sleep(5000); // TODO you can reduce this if you want
			}
		} catch (MqttException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}

