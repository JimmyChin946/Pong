package org.pong;

import javax.swing.*;
import java.awt.event.*;
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

	private Ball ball;

	private Paddle paddleA;
	private Paddle paddleB;

	private int scoreA;
	private int scoreB;

	private ArrayList<Chat> chatHistory;

  private T7DataRepository() {
    super(new Object());
		
		// default values
		ball = new Ball();
		paddleA = new PaddleA();
		paddleB = new PaddleB();
		scoreA = 0;
		scoreB 0;
		chatHistory = new ArrayList<Chat>();
  }

  public static T7DataRepository getInstance() {
    if (instance == null) { instance = new Repository(); }
    return instance;
  }

	public ArrayList<Chat> getchatHistory() { return chatHistory; }
	public int scoreA() { return scoreA; }
	public int scoreB() { return scoreB; }

}


