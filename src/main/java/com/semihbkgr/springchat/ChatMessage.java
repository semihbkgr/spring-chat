package com.semihbkgr.springchat;

import lombok.Data;

@Data
public class ChatMessage {

    private String sender;
    private String recipient;
    private String text;
    private long sentAt;

}