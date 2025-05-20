package org.pong;


/**
* A datastructure to hold a "message"
* this includes the name of the author
* and the content of the message
* 
* TODO we can add the date if we want later
* 
* @author Jude Shin 
* 
*/
public class T7Chat {
	private String author;
	private String content;
	// private Date dateSent;

	public T7Chat (String author, String content) {
		this.author = author;
		this.content = content;
	}

	public String getAuthor() { return author; }
	public String getContent() { return content; }
}
