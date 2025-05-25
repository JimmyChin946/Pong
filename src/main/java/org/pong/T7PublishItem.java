package org.pong;


/**
 * A datastructure to hold a "message"
 * this includes the name of the author
 * and the content of the message
 *
 * @author Jude Shin
 *
 */
class T7PublishItem {
	private final String subTopic;
	private final byte[] message; 

	public T7PublishItem(String subTopic, byte[] message) {
		this.subTopic = subTopic; // ball playerHost playerClient scoreHost scoreClient chatHost chatClient
		this.message = message;	
	}

	public String getSubTopic() { return subTopic; }
	public byte[] getMessage() { return message; }
}
