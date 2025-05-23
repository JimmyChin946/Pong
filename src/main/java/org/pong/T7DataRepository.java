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

	public T7Ball getBall() { return Ball; }
	public void setBall(T7Ball ball) {
		this.ball = ball; 
		firePropertyChange("ball", null, ball);
	}

	public T7Player getPlayerHost() { return playerHost; }
	public void setPlayerHost(T7Player player) { 
		this.playerHost = player; 
		firePropertyChange("playerHost", null, playerHost);
	}

	public T7Player getPlayerClient() { return playerClient; }
	public void setPlayerClient(T7Player player) { 
		this.playerClient = player; 
		firePropertyChange("playerClient", null, playerClient);
	}

	public int getScoreHost() { return scoreHost; }
	public void setScoreHost(int score) {
		scoreHost = score; 
		firePropertyChange("scoreHost", null, scoreHost);
	}

	public int getScoreClient() { return scoreClient; }
	public void setScoreClient(int score) { 
		scoreClient = score; 
		firePropertyChange("scoreHost", null, scoreHost);
	}

	public ArrayList<T7Chat> getChatHistory() { return chatHistory; }
	public void addChatHistory(T7Chat chat) {
		chatHistory.add(chat); 
		firePropertyChange("chatHistory", null, chatHistory);
	}

	public void pushPublishQueue(T7PublishItem publishItem) { publishQueue.offer(publishItem); }
	public T7PublishItem popPublishQueue() {
		T7PublishItem publishItem = publishQueue.poll();
		firePropertyChange("publishQueue", null, publishQueue);
		// firePropertyChange("publishItem", null, publishItem);
		return publishItem;
	}
}


