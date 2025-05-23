package org.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class T7Field extends JPanel implements ActionListener {
    private final T7Ball ball;
    private final T7Player playerHost;
    private final T7Player playerClient;
    private final T7KeyHandler keyHandler;
    private final Timer timer;

    public T7Field() {
        setFocusable(true);
        requestFocusInWindow();

        ball = new T7Ball();
        playerHost = new T7Player(20, 250);
        playerClient = new T7Player(750, 250); // Right side
        keyHandler = new T7KeyHandler();

        T7DataRepository.getInstance().setPlayerHost(playerHost);
        T7DataRepository.getInstance().setPlayerClient(playerClient);

        addKeyListener(keyHandler);

        timer = new Timer(16, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ball.draw(g);
        playerHost.draw(g);
        playerClient.draw(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player A Score: " + playerHost.getScore(), 20, 30);
        g.drawString("Player B Score: " + playerClient.getScore(), 600, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.moveBall(getWidth(), getHeight());

        if (ball.getBounds().intersects(playerHost.getBounds()) || ball.getBounds().intersects(playerClient.getBounds())) {
            ball.reverseX();
        }

        else if (ball.getX() <= 10) {
            System.out.println("Player B scores!");
            playerClient.score();
            T7DataRepository.getInstance().setScoreClient(playerClient.getScore());
            ball.reset();
        }

        else if (ball.getX() >= 760) {
            System.out.println("Player A scores!");
            playerHost.score();
            T7DataRepository.getInstance().setScoreClient(playerHost.getScore());
            ball.reset();
        }


        if (keyHandler.isUpPressed()) {
            playerHost.moveUp(getHeight());
        }
        if (keyHandler.isDownPressed()) {
            playerHost.moveDown(getHeight());
        }

        repaint();
    }
}