package org.example.server;

import java.io.IOException;

public class ChatServerMain {
    public static void main(String[] args) {
        try {
            ChatServer chatServer = new ChatServer();
            chatServer.startChat();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
