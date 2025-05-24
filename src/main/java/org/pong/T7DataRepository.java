package org.pong;

import java.beans.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Singleton for all of the data used in pong
 * ball: for the location of the ball
 * playerHost : for the location of one of the players paddle
 * playerClient : for the location of the other players paddle
 * scoreHost : the score of one of the players
 * scoreClient : the score of the other player
 * chatHistory : the log of the game's chat history
 * 
 * @author Jude Shin 
 * 
 */
public class T7DataRepository extends PropertyChangeSupport {
	private static T7DataRepository instance;

	private T7Ball ball;

	private T7Player playerHost;
	private T7Player playerClient;

	private int scoreHost;
	private int scoreClient;

	private ArrayList<T7Chat> chatHistory;

	private Queue<T7PublishItem> publishQueue;

	private T7DataRepository() {
		super(new Object());

		// default values
		ball = new T7Ball();
		playerHost = new T7Player();
		playerClient = new T7Player();
		scoreHost = 0;
		scoreClient = 0;
		chatHistory = new ArrayList<>();
		publishQueue = new LinkedList<>();
	}

	public static T7DataRepository getInstance() {
		if (instance == null) { instance = new T7DataRepository(); }
		return instance;
	}

	public T7Ball getBall() { return ball; }
	// so it doesn't break everything that has been worked on... could be refactored later
	public void setBall(T7Ball ball) { this.setBall(ball, false); } 
	public void setBall(T7Ball ball, boolean isSilent) {
		this.ball = ball; 
		if (isSilent)	{ firePropertyChange("ball", null, ball); }
	}

	public T7Player getPlayerHost() { return playerHost; }
	// so it doesn't break everything that has been worked on... could be refactored later
	public void setPlayerHost(T7Player player) { this.setPlayerHost(player, false); } 
	public void setPlayerHost(T7Player player, boolean isSilent) { 
		this.playerHost = player; 
		if (isSilent)	{ firePropertyChange("playerHost", null, playerHost); }
	}

	public T7Player getPlayerClient() { return playerClient; }
	// so it doesn't break everything that has been worked on... could be refactored later
	public void setPlayerClient(T7Player player) { this.setPlayerClient(player, false); }
	public void setPlayerClient(T7Player player, boolean isSilent) { 
		this.playerClient = player; 
		if (isSilent)	{ firePropertyChange("playerClient", null, playerClient); }
	}

	public int getScoreHost() { return scoreHost; }
	// so it doesn't break everything that has been worked on... could be refactored later
	public void setScoreHost(int score) { this.setScoreHost(score, false); }
	public void setScoreHost(int score, boolean isSilent) {
		scoreHost = score; 
		if (isSilent)	{ firePropertyChange("scoreHost", null, scoreHost); }
	}

	public int getScoreClient() { return scoreClient; }
	// so it doesn't break everything that has been worked on... could be refactored later
	public void setScoreClient(int score) { this.setScoreClient(score, false); }
	public void setScoreClient(int score, boolean isSilent) { 
		scoreClient = score; 
		if (isSilent)	{ firePropertyChange("scoreHost", null, scoreHost); }
	}

	public ArrayList<T7Chat> getChatHistory() { return chatHistory; }
	// so it doesn't break everything that has been worked on... could be refactored later
	public void addChatHistory(T7Chat chat) { this.addChatHistory(chat, false); }
	public void addChatHistory(T7Chat chat, boolean isSilent) {
		chatHistory.add(chat); 
		if (isSilent)	{ firePropertyChange("chatHistory", null, chatHistory); }
	}

	public void pushPublishQueue(T7PublishItem publishItem) { publishQueue.offer(publishItem); }
	public T7PublishItem popPublishQueue(boolean isSilent) {
		T7PublishItem publishItem = publishQueue.poll();
		return publishItem;
	}
}


