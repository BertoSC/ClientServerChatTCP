package org.example.client;
import java.io.IOException;
import java.net.Socket;

public class ChatClient {
    final static String HOST = "localhost";
    final static int PORT = 57780;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORT);
            Thread th = new Thread(new ChatClientUserInput(socket));
            th.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
