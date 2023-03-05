package com.semihbkgr.springchat;

import lombok.NonNull;

public interface ChatMessageBroadcastService {

    void publish(@NonNull ChatMessage message);

}
