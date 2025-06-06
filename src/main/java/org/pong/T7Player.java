package org.pong;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * Represents a player's paddle in the game with a fixed size and position.
 *
 * @author Kai Swangler
 */
public class T7Player implements Serializable {
    private double x, y;
    private final double width = 0.05, height = 0.3;

    public T7Player(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g, double scale) {
        g.setColor(Color.BLUE);
        g.fillRect((int) (x * scale), (int) (y * scale), (int) (width * scale), (int) (height * scale));
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, width, height);
    }

    public void moveUp() {
        if (y >= 0) y -= 0.01;
    }

    public void moveDown() {
        if (y <= 1 - height) y += 0.01;
    }
}