package org.pong;

/**
*	Subscribes for the cloud 
* 
* @author Jude Shin
* 
*/
public class T7Subscriber implements MqttCallback {
	private final static String BROKER = "tcp://broker.hivemq.com:1883";
	private final static String TOPIC = "cal-poly/csc/307/meee";
	private final static String CLIENT_ID = "god-listener";

	public T7Subscriber() {
		try {
			MqttClient client = new MqttClient(BROKER, CLIENT_ID);
			client.setCallback(this);
			client.connect();
			System.out.println("Connected to BROKER: " + BROKER);
			client.subscribe(TOPIC);
			System.out.println("Subscribed to TOPIC: " + TOPIC);
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection lost: " + throwable.getMessage());
	}

	@Override
	public void messageArrived(String s, MqttMessage mqttMessage) {
		String message = new String(mqttMessage.getPayload());
		String[] splitStrings = message.split(",");

		int x = Integer.parseInt(splitStrings[0]);
		int y = Integer.parseInt(splitStrings[1]);

		System.out.println("Message arrived. Point: " + s +
				" Message: " + "(" + message + ")");

		T7DataRepository.getInstance().setPoint(x, y);
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		System.out.println("Delivered complete: " + iMqttDeliveryToken.getMessageId());
	}

}

