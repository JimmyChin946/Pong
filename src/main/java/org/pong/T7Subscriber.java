package org.pong;

import org.eclipse.paho.client.mqttv3.*;

import java.io.IOException;


/**
*	Subscribes for the cloud based on a topic
* there should be multiple subscribers for multiple topics called
* 
* @author Jude Shin
* 
*/
public class T7Subscriber implements MqttCallback {
	// private final static String BROKER = "tcp://broker.hivemq.com:1883";
	// private final static String TOPIC = "cal-poly/csc/307/meee";
	// private final static String CLIENT_ID = "god-listener";
	private String subTopic;


	public T7Subscriber(String broker, String baseTopic, String subTopic, String id) {
		this.subTopic = subTopic;
		try {
			MqttClient client = new MqttClient(broker, id);
			client.setCallback(this);
			client.connect();
			System.out.println("Connected to BROKER: " + broker);
			client.subscribe(baseTopic + "/" + subTopic);
			System.out.println("Subscribed to TOPIC: " + baseTopic + "/" + subTopic);
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

		System.out.println("Message Arrived! TOPIC: " + subTopic);

		switch (subTopic) {
			case "ball":
				T7Ball ball = T7ByteConverter.fromBytes(bytes, T7Ball.class);
				T7DataRepository.getInstance().setBall(ball);
			break;
			case "playerHost":
				T7Player playerHost = T7ByteConverter.fromBytes(bytes, T7Player.class);
				T7DataRepository.getInstance().setPlayerHost(playerHost);
			break;
			case "playerClient":
				T7Player playerClient = T7ByteConverter.fromBytes(bytes, T7Player.class);
				T7DataRepository.getInstance().setPlayerClient(playerClient);
			break;
			case "scoreHost":
				int scoreHost = T7ByteConverter.fromBytes(bytes, Integer.class);
				T7DataRepository.getInstance().setScoreHost(scoreHost);
			break;
			case "scoreClient":
				int scoreClient = T7ByteConverter.fromBytes(bytes, Integer.class);
				T7DataRepository.getInstance().setScoreClient(scoreClient);
			break;
			case "chat":
				T7Chat chat = T7ByteConverter.fromBytes(bytes, T7Chat.class);
				T7DataRepository.getInstance().addChatHistory(chat);
			break;
			default:
				System.out.println("given SubTopic does not match any forms");
				System.out.println("message not saved");
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}

}

