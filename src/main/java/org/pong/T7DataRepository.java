package org.pong;

import javax.swing.*;
import java.awt.event.*;
import java.beans.*;

public class T7DataRepository extends PropertyChangeSupport {
  private static T7DataRepository instance;

	private Ball ball;

	private Paddle paddle1;
	private Paddle paddle2;

	private int score1;
	private int score2;

  private T7DataRepository() {
    super(new Object());
  }

  public static T7DataRepository getInstance() {
    if (instance == null) { instance = new Repository(); }
    return instance;
  }
}


