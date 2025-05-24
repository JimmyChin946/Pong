package org.pong;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
* A datastructure to hold a "message"
* this includes the name of the author
* and the content of the message
* 
* @author Jude Shin 
* 
*/
public class T7Chat implements Serializable {
	private static final long serialVersionUID = 1L;

	private String author;
	private String content;
	private LocalDateTime date;

	public T7Chat(String author, String content, LocalDateTime date) {
		this.author = author;
		this.content = content;
		this.date = date;
	}

	public String getAuthor() { return author; }
	public String getContent() { return content; }
	public LocalDateTime getDate() { return date; }
}
