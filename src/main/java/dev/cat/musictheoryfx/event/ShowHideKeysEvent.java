package dev.cat.musictheoryfx.event;

import org.springframework.context.ApplicationEvent;

public class ShowHideKeysEvent extends ApplicationEvent {

    private boolean mustShowKeys;

    public ShowHideKeysEvent(Object source, boolean mustShowKeys) {
        super(source);
        this.mustShowKeys = mustShowKeys;
    }

    public boolean mustShowKeys() {
        return mustShowKeys;
    }




}
