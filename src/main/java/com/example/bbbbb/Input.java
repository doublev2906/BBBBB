package com.example.bbbbb;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Input implements EventHandler<KeyEvent> {

    private boolean[] keys = new boolean[120];
    public boolean up, down, left, right, space;

    public void update() {
        up = keys[KeyCode.UP.getCode()];
        down = keys[KeyCode.DOWN.getCode()];
        left = keys[KeyCode.LEFT.getCode()];
        right = keys[KeyCode.RIGHT.getCode()];
        space = keys[KeyCode.SPACE.getCode()];
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if (keyEvent.getEventType() == KeyEvent.KEY_PRESSED){
            keys[keyEvent.getCode().getCode()] = true;
        }
        else if (keyEvent.getEventType() == KeyEvent.KEY_RELEASED){
            keys[keyEvent.getCode().getCode()] = false;
        }
        update();
    }
}
