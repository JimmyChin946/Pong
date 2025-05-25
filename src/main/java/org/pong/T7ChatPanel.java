package org.pong;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    private final T7DataRepository repository;
    private final JPanel messagePrompt;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

    public T7ChatPanel(String playerName) {
        super();

        repository = T7DataRepository.getInstance();
        repository.addPropertyChangeListener("chatHistory", this);

        setLayout(new GridLayout(MAX_MESSAGES + 1, 1));

        messagePrompt = new JPanel();
        messagePrompt.setLayout(new GridLayout(1, 2));

        JTextField messageField = new JTextField();
        messagePrompt.add(messageField);
        messageField.setVisible(true);

        JButton messageButton = new JButton("Send");
        messagePrompt.add(messageButton);
        messageButton.addActionListener(e -> {
            T7Chat message = new T7Chat(playerName, messageField.getText(), LocalDateTime.now());
            try {
                repository.addChatHistory(message, false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            messageField.setText("");
        });
        messageButton.setVisible(true);

        draw(new ArrayList<>());

        messagePrompt.setVisible(true);
    }

    public void draw(ArrayList<T7Chat> messages) {
        removeAll();
        revalidate();
        repaint();

        for (int i = messages.size() - MAX_MESSAGES; i < messages.size(); i++) {
            String line;
            if (i >= 0) {
                T7Chat message = messages.get(i);
                line = "[" + message.getDate().format(formatter) + "] " + message.getAuthor() + ": " + message.getContent();
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
        draw(repository.getChatHistory());
    }
}
