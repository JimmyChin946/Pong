package org.pong;

import java.awt.*;

public class T7Player {
    private static final long serialVersionUID = 1L;

    private int x, y;
    private final int width = 20, height = 100;
    private int score = 0;

    public T7Player() {

    }

    public T7Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void moveUp(int fieldHeight) {
        if (y - 10 >= 0) y -= 10;
    }

    public void moveDown(int fieldHeight) {
        if (y + height + 10 <= fieldHeight) y += 10;
    }

    public int getScore() {
        return score;
    }

    public void score() {
        int old = this.score;
        this.score = old + 1;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}