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
public class T7ChatPanel extends JPanel implements PropertyChangeListener {
    private final int MAX_MESSAGES = 8;

    private T7DataRepository repository;
    private JPanel messagePrompt;

    public T7ChatPanel() {
        super();

        repository = T7DataRepository.getInstance();
        repository.addPropertyChangeListener("chat", this);

        setLayout(new GridLayout(MAX_MESSAGES + 1, 1));

        messagePrompt = new JPanel();
        messagePrompt.setLayout(new GridLayout(1, 2));

        JTextField messageField = new JTextField();
        messagePrompt.add(messageField);
        messageField.setVisible(true);

        JButton messageButton = new JButton("Send");
        messagePrompt.add(messageButton);
        messageButton.addActionListener(e -> {
            T7Chat message = new T7Chat("Me", messageField.getText());
            repository.addChatHistory(message);
        });
        messageButton.setVisible(true);

        draw(new ArrayList<>());

        messagePrompt.setVisible(true);

        setVisible(true);
    }

    public void draw(ArrayList<T7Chat> messages) {
        removeAll();

        for (int i = messages.size() - 1; i > messages.size() - 1 - MAX_MESSAGES; i--) {
            String line;
            if (i > 0) {
                line = messages.get(i).getAuthor() + messages.get(i).getContent();
            } else {
                line = "";
            }

            JLabel label = new JLabel(line);
            add(label);
            label.setVisible(true);
        }

        add(messagePrompt);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        draw((ArrayList<T7Chat>) evt.getNewValue());
    }
}
