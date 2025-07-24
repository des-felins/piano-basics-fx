package dev.cat.musictheoryfx.event;


import dev.cat.musictheoryfx.controller.ui.Key;
import org.springframework.context.ApplicationEvent;

import javafx.scene.input.KeyEvent;

public class KeyActionEvent extends ApplicationEvent {

    // pass not they KeyEvent, but Key. Get it thought KeyboardController

    Key key;

    public KeyActionEvent(Object source, Key keyEvent) {
        super(source);
        this.key = keyEvent;
    }

    public Key getKey() {
        return key;
    }
}
