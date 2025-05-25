package org.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T7Field extends JPanel implements PropertyChangeListener {
    private final T7DataRepository repository;

    public T7Field() {
        super();

        repository = T7DataRepository.getInstance();

        repository.addPropertyChangeListener("ball", this);
        repository.addPropertyChangeListener("playerHost", this);
        repository.addPropertyChangeListener("playerClient", this);
        repository.addPropertyChangeListener("scoreHost", this);
        repository.addPropertyChangeListener("scoreClient", this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double scale = Math.min(getWidth() / 2, getHeight());
        repository.getBall().draw(g, scale);
        repository.getPlayerHost().draw(g, scale);
        repository.getPlayerClient().draw(g, scale);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player A Score: " + repository.getScoreHost(), 20, 30);
        g.drawString("Player B Score: " + repository.getScoreClient(), getWidth() - 220, 30);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        paintComponent(getGraphics());
    }
}