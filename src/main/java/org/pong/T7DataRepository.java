package org.pong;

import java.beans.*;
import java.util.ArrayList;


/**
* Singleton for all of the data used in pong
* ball: for the location of the ball
*	paddleA : for the location of one of the players paddle
* paddleB : for the location of the other players paddle
*	scoreA : the score of one of the players 
* scoreB : the score of the other player
* chatHistory : the log of the game's chat history
* 
* @author Jude Shin 
* 
*/
public class T7DataRepository extends PropertyChangeSupport {
  private static T7DataRepository instance;

	private T7Ball ball;

	private T7Player paddleA;
	private T7Player paddleB;

	private int scoreA;
	private int scoreB;

	private ArrayList<T7Chat> chatHistory;

  private T7DataRepository() {
    super(new Object());
		
		// default values
		ball = new T7Ball();
		paddleA = new T7Player();
		paddleB = new T7Player();
		scoreA = 0;
		scoreB = 0;
		chatHistory = new ArrayList<>();
  }

  public static T7DataRepository getInstance() {
    if (instance == null) { instance = new T7DataRepository(); }
    return instance;
  }

	public ArrayList<T7Chat> getChatHistory() { return chatHistory; }
	public int scoreA() { return scoreA; }
	public int scoreB() { return scoreB; }

}


