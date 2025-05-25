package org.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Singleton class that handles keyboard input events for the UP and DOWN arrow keys.
 *
 * @author Kai Swangler
 */
public class T7KeyHandler implements KeyListener {
    private static T7KeyHandler instance = new T7KeyHandler();

    private boolean upPressed, downPressed;

    public boolean isUpPressed() { return upPressed; }
    public boolean isDownPressed() { return downPressed; }

    private T7KeyHandler() {}

    public static T7KeyHandler getInstance() {
        return instance;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> upPressed = true;
            case KeyEvent.VK_DOWN -> downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> upPressed = false;
            case KeyEvent.VK_DOWN -> downPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }
}