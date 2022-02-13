package com.massa844853.stockstracker.models;

import com.google.firebase.database.Exclude;

public class Message {
    private String username;
    private Long sendDate;
    private String message;
    @Exclude
    private int typeMessage;

    public Message()
    {

    }

    public Message(String username, Long sendDate, String message) {
        this.username = username;
        this.sendDate = sendDate;
        this.message = message;
        this.typeMessage = -1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getSendDate() {
        return sendDate;
    }

    public void setSendDate(Long sendDate) {
        this.sendDate = sendDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(int typeMessage) {
        this.typeMessage = typeMessage;
    }
}
