package dev.cat.musictheoryfx.event;

import org.springframework.context.ApplicationEvent;

public class ShowHidePlayedKeysEvent extends ApplicationEvent {

    private boolean mustShowKeys;

    public ShowHidePlayedKeysEvent(Object source, boolean mustShowKeys) {
        super(source);
        this.mustShowKeys = mustShowKeys;
    }

    public boolean mustShowKeys() {
        return mustShowKeys;
    }




}
