package org.pong;

import java.awt.*;
import javax.swing.*;

/**
 * T7Choice is a JPanel that provides a simple user interface to enter a player name
 * and choose whether to host or join a game as a client. It displays a text field for
 * the user's name and two buttons for selecting the game role.
 *
 * @author Nathan Lackie
 */
public class T7Choice extends JPanel {
    private JTextField nameField;
    private JButton hostButton;
    private JButton clientButton;

    public T7Choice() {
        super();

        setLayout(new GridLayout(4, 1));

        add(new JLabel("Enter your name:"));
        nameField = new JTextField("Me");
        add(nameField);

        hostButton = new JButton("Host");
        hostButton.addActionListener(e -> makeGame(T7Game.PlayerType.HOST));
        add(hostButton);

        clientButton = new JButton("Client");
        clientButton.addActionListener(e -> makeGame(T7Game.PlayerType.CLIENT));
        add(clientButton);

        setVisible(true);
    }

    private void makeGame(T7Game.PlayerType type) {
        T7Game.makeGame(nameField.getText(), type);
    }
}
