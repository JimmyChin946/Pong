package org.pong;

import java.beans.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
* Singleton for all of the data used in pong
* ball: for the location of the ball
*	playerHost : for the location of one of the players paddle
* playerClient : for the location of the other players paddle
*	scoreHost : the score of one of the players 
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

	private Queue<PublishItem> publishQueue;

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

	public Ball getBall() { return Ball; }

	public T7Player getPlayerHost() { return playerHost; }
	public T7Player getPlayerClient() { return playerClient; }

	public int getScoreHost() { return scoreHost; }
	public void setScoreHost(int score) { scoreHost = score; }

	public int getScoreClient() { return scoreClient; }
	public void setScoreClient(int score) { scoreClient = score; }

	public ArrayList<T7Chat> getChatHistory() { return chatHistory; }
	public void addChatHistory(T7Chat chat) { chatHistory.add(chat); }

	public void pushPublishQueue(PublishItem publishItem) { publishQueue.offer(publishItem); }
	public PublishItem popPublishQueue() { return publishQueue.poll(); }
}


