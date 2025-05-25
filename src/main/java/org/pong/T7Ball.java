package org.pong;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

/**
 * A data structure for the ball
 *
 * @author Kai Swangler
 * @version 1.0
 */
public class T7Ball implements Serializable {
    private Point2D position;
    private Point2D velocity;
    private double size = 0.05;


    public T7Ball(Point2D position) {
        this.position = position;
        this.velocity = new Point2D.Double(0.01, 0.01);
    }

    public void moveBall() {
        position.setLocation(position.getX() + velocity.getX(), position.getY() + velocity.getY());
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(position.getX(), position.getY(), size, size);
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void reverseY() {
        velocity.setLocation(velocity.getX(), -velocity.getY());
    }

    public void reverseX() {
        velocity.setLocation(-velocity.getX(), velocity.getY());
    }

    protected void draw(Graphics g, double scale) {
        g.setColor(Color.ORANGE);
        int screenSize = (int) (size * scale);
        g.fillRect((int) (position.getX() * scale), (int) (position.getY() * scale), screenSize, screenSize);
    }
}