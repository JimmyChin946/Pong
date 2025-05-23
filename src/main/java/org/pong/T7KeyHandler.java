package org.pong;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class T7KeyHandler implements KeyListener {
    private boolean upPressed, downPressed;

    public boolean isUpPressed() { return upPressed; }
    public boolean isDownPressed() { return downPressed; }

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