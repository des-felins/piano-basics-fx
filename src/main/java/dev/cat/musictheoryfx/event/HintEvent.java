package dev.cat.musictheoryfx.event;

import org.springframework.context.ApplicationEvent;

public class HintEvent extends ApplicationEvent {

    private boolean showHints;

    public HintEvent(Object source, boolean showHints) {
        super(source);
        this.showHints = showHints;
    }

    public boolean isShowHints() {
        return showHints;
    }
}
