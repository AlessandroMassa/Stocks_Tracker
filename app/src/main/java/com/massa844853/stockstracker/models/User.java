package com.massa844853.stockstracker.models;

public class User {
    private Long loginDateTime;
    private int nextMessage;
    private String username;
    public User()
    {

    }

    public User(Long loginDateTime, int nextMessage, String username) {
        this.loginDateTime = loginDateTime;
        this.nextMessage = nextMessage;
        this.username = username;
    }

    public Long getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(Long loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    public int getNextMessage() {
        return nextMessage;
    }

    public void setNextMessage(int nextMessage) {
        this.nextMessage = nextMessage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
