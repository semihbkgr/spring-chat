package com.semihbkgr.springchat;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessage {

    private String sender;
    private String recipient;
    private String text;
    private long sentAt;

}