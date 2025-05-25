package org.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JPanel that visually represents the game field for the game.
 * It listens for changes in key properties of the game state (ball position, player positions, and scores)
 * and repaints the panel accordingly.
 *
 * @author Kai Swangler
 */
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

        int playerAScore = repository.getScoreHost();
        int playerBScore = repository.getScoreClient();

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player A Score: " + playerAScore, 20, 30);
        g.drawString("Player B Score: " + playerBScore, getWidth() - 220, 30);

        if (playerAScore >= 7) {
            T7Win.draw(g, scale, "Player A");
        } else if (playerBScore >= 7) {
            T7Win.draw(g, scale, "Player B");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        repaint();
    }
}