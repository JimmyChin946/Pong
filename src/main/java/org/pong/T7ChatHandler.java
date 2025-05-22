package org.pong;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 *
 *
 * @author Nathan Lackie
 */
public class T7ChatHandler implements PropertyChangeListener {
    private T7DataRepository dataRepository;
    private JPanel chatPanel;

    public T7ChatHandler() {
        chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));

        dataRepository = T7DataRepository.getInstance();
        dataRepository.addPropertyChangeListener("chat", this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateChat(dataRepository.getchatHistory());
    }

    public JPanel getPanel() {
        return this.chatPanel;
    }

    private void updateChat(ArrayList<T7Chat> messages) {
        int numMessages = messages.size();
        for (int i = Math.max(0, numMessages - 6); i < numMessages; i++) {
            T7Chat message = messages.get(i);
            chatPanel.add(new JLabel(message.getAuthor() + ": " + message.getContent()));
        }
    }
}
