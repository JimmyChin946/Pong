package org.Pong

class PublishItem {
	private final String subTopic;
	private final byte[] message; // cast this into whatever we need to use 

	public PublishItem(String subTopic String message) {
		this.subTopic = subTopic; // ball playerA playerB scoreA scoreB messageA messageB
		this.message = message;	
	}
	
	public String getTopic() { return subTopic; }
}
