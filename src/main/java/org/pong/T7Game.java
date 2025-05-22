package org.pong;

import javax.swing.*;

/**
 *
 *
 * @author Nathan Lackie
 */
public class T7Game {
    static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame();

        frame.add(new T7ChatPanel());
        frame.setSize(800, 600);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
