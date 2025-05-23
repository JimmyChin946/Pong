package org.pong;

import java.awt.*;

/**
 * A data structure for the ball
 *
 * @author Kai Swangler
 * @version 1.0
 */
public class T7Ball {
    private int x, y;
    private int dx = 5, dy =5;
    private int size = 20;


    public T7Ball() {
        x = 100;
        y = 100;
    }

    public void moveBall(int width, int height) {
        x += dx;
        y += dy;

        if (y <= 0 || y + size >= height) {
            dy = -dy;
        }

        if (x <= 0 || x + size >= width) {
            dx = -dx;
        }

        T7DataRepository.getInstance().setBall(this);

    }

    public void reset() {
        x = 400;
        y = 300;

        T7DataRepository.getInstance().setBall(this);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public int getX() {return x;}
    public int getY() {return y;}


    public void reverseX() {
        dx = -dx;
    }
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, size, size);

        System.out.println(x + " " +y);
    }
}