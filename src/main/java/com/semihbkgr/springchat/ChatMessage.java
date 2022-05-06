package com.semihbkgr.springchat;

import lombok.Data;

@Data
public class ChatMessage {

    private String from;
    private String recipient;
    private String text;

}