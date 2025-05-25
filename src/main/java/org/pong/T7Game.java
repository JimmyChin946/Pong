package org.pong;

import java.awt.*;
import java.util.EventListener;
import javax.swing.*;

/**
 * Main entry point and controller for the multiplayer pong game.
 * It manages the GUI frame, initializes game components, and coordinates the hosting and subscribing
 * of game state over a network using the MQTT protocol.
 *
 * @author Nathan Lackie
 */
public class T7Game implements EventListener {
    private static JFrame frame;
    private T7GameRunner runner;

    public enum PlayerType {
        HOST,
        CLIENT
    }

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new T7Choice());

        frame.setVisible(true);
    }

    public static void makeGame(String playerName, PlayerType type) {
        frame.getContentPane().removeAll();
        frame.revalidate();
        frame.repaint();
        new T7Game(playerName, type);
    }

    private T7Game(String playerName, PlayerType type) {
        frame.setLayout(new GridLayout(2, 1));

        frame.addKeyListener(T7KeyHandler.getInstance());
        frame.setFocusable(true);
        frame.requestFocusInWindow();

        frame.add(new T7Field());
        frame.add(new T7ChatPanel(playerName));

        frame.setVisible(true);

        T7Publisher publisher = new T7Publisher("tcp://test.mosquitto.org:1883", "csc-307/pong-game", type);
        new Thread(publisher).start();

        new T7Subscriber("tcp://test.mosquitto.org:1883", "csc-307/pong-game/+", type);

        runner = new T7GameRunner(type);
        new Thread(runner).start();
    }
}
