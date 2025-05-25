package org.pong;

import java.util.ArrayList;
import org.eclipse.paho.client.mqttv3.*;
import java.io.IOException;


/**
 * Subscribes for the cloud based on a topic
 *
 * @author Jude Shin
 * 
 */
public class T7Subscriber implements MqttCallback {
	private final T7Game.PlayerType type;

	public T7Subscriber(String broker, String topic, T7Game.PlayerType type) {
		this.type = type;
		String id = type == T7Game.PlayerType.HOST ? "PongHostSubscriber" : "PongClientSubscriber";

		try {
			MqttClient client = new MqttClient(broker, id);
			client.setCallback(this);
			client.connect();
			System.out.println("Connected to BROKER: " + broker);
			client.subscribe(topic);
			System.out.println("Subscribed to TOPIC: " + topic);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost: " + throwable.getMessage());
	}

	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) throws IOException, ClassNotFoundException {
		byte[] bytes = mqttMessage.getPayload();
		String[] topics = s.split("/");
		String subTopic = topics[topics.length - 1];

		if (type == T7Game.PlayerType.HOST) {
			switch (subTopic) {
				case "playerClient":
					T7Player playerClient = T7ByteConverter.fromBytes(bytes, T7Player.class);
					T7DataRepository.getInstance().setPlayerClient(playerClient, true);
					break;
				case "chat":
					ArrayList<T7Chat> chat = T7ByteConverter.fromBytes(bytes, ArrayList.class);
					T7DataRepository.getInstance().setChatHistory(chat, true);
					break;
				default:
			}
		}
		else {
			switch (subTopic) {
				case "ball":
					T7Ball ball = T7ByteConverter.fromBytes(bytes, T7Ball.class);
					T7DataRepository.getInstance().setBall(ball, true);
					break;
				case "playerHost":
					T7Player playerHost = T7ByteConverter.fromBytes(bytes, T7Player.class);
					T7DataRepository.getInstance().setPlayerHost(playerHost, true);
					break;
				case "scoreHost":
					int scoreHost = T7ByteConverter.fromBytes(bytes, Integer.class);
					T7DataRepository.getInstance().setScoreHost(scoreHost, true);
					break;
				case "scoreClient":
					int scoreClient = T7ByteConverter.fromBytes(bytes, Integer.class);
					T7DataRepository.getInstance().setScoreClient(scoreClient, true);
					break;
				case "chat":
					ArrayList<T7Chat> chat = T7ByteConverter.fromBytes(bytes, ArrayList.class);
					T7DataRepository.getInstance().setChatHistory(chat, true);
					break;
				default:
			}
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}
}

