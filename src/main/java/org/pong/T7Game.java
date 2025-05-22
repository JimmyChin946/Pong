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

        T7ChatHandler chatHandler = new T7ChatHandler();
        frame.add(chatHandler.getPanel());
    }
}
