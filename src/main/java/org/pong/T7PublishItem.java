package org.pong;

class T7PublishItem {
	private final String subTopic;
	private final byte[] message; // cast this into whatever we need to use 

	public T7PublishItem(String subTopic, byte[] message) {
		this.subTopic = subTopic; // ball playerHost playerClient scoreHost scoreClient chatHost chatClient
		this.message = message;	
	}
	
	public String getSubTopic() { return subTopic; }
	public byte[] getMessage() { return message; }
}
