package org.pong;

import java.awt.geom.Point2D;
import java.io.IOException;

/**
 * Implements the main game loop in a separate thread.
 * It handles movement and game state updates depending on whether the instance is running
 * as a host or a client. Hosts process full game logic, while clients only process player movement.
 *
 *
 * @author Nathan Lackie
 */
public class T7GameRunner implements Runnable {
    T7Game.PlayerType type;
    T7DataRepository repository = T7DataRepository.getInstance();
    T7KeyHandler keyHandler = T7KeyHandler.getInstance();
    boolean gameWon = false;

    public T7GameRunner(T7Game.PlayerType type) {
        this.type = type;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (type == T7Game.PlayerType.HOST) {
                    gameTick();
                }
                movementTick(type);
                if (gameWon) {
                    return;
                }
                Thread.sleep(30);
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void gameTick() throws IOException {
        T7Ball ball = repository.getBall();
        T7Player host = repository.getPlayerHost();
        T7Player client = repository.getPlayerClient();

        ball.moveBall();

        Point2D position = ball.getPosition();
        if (position.getY() <= 0 || position.getY() >= 1) {
            ball.reverseY();
        }

        if (ball.getBounds().intersects(host.getBounds()) || ball.getBounds().intersects(client.getBounds())) {
            ball.reverseX();
        }

        else if (position.getX() <= 0) {
            System.out.println("Player B scores!");
            repository.setScoreClient(repository.getScoreClient() + 1, false);
            ball.setPosition(new Point2D.Double(0.8, 0.5));
            ball.reverseX();
        }

        else if (position.getX() >= 2) {
            System.out.println("Player A scores!");
            repository.setScoreHost(repository.getScoreHost() + 1, false);
            ball.setPosition(new Point2D.Double(0.2, 0.5));
        }

        if (repository.getScoreHost() >= 7 || repository.getScoreClient() >= 7) {
            gameWon = true;
        }

        repository.setBall(ball, false);
    }

    private void movementTick(T7Game.PlayerType type) throws IOException {
        T7Player player = type == T7Game.PlayerType.HOST ? repository.getPlayerHost() : repository.getPlayerClient();

        if (keyHandler.isUpPressed()) {
            player.moveUp();
        } else if (keyHandler.isDownPressed()) {
            player.moveDown();
        }

        if (type == T7Game.PlayerType.HOST) {
            repository.setPlayerHost(player, false);
        } else {
            repository.setPlayerClient(player, false);
        }
    }
}
