package dev.cat.musictheoryfx.event;

import org.springframework.context.ApplicationEvent;

public class LoginEvent extends ApplicationEvent {

    private String userName;

    public LoginEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

}
