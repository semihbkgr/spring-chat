package com.semihbkgr.springchat;

import lombok.NonNull;

public interface ChatMessageBroadcastService {

    void send(@NonNull ChatMessage message);

}
