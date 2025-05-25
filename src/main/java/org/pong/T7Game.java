package org.pong;

import java.awt.*;
import java.util.EventListener;
import javax.swing.*;

/**
 *
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

        frame.add(new T7Field());
        frame.add(new T7ChatPanel(playerName));

        frame.setVisible(true);

        runner = new T7GameRunner(type);
        new Thread(runner).start();
    }
}
