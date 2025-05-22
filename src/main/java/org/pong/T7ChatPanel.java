package org.pong;

import javax.swing.*;

public class T7ChatPanel extends JPanel {
    JPanel messagePanel;
    JTextField messageField;

    public T7ChatPanel() {
        super();

        messagePanel = new JPanel();
        messageField = new JTextField();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(messagePanel);
        add(messageField);

        messagePanel.setVisible(true);
        setVisible(true);


    }
}
