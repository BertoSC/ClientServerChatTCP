package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ChatServer {
    Map<String, ChatServerWorker> chatServerWorkerList = new HashMap<>();
    final static int PORT = 57780;
    ServerSocket serverSocket;

    public ChatServer() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    public void startChat() throws IOException {
        Socket socket = null;
        while(true){
          socket = serverSocket.accept();
          ChatServerWorker chatServerWorker = new ChatServerWorker(socket, this);
          Thread th = new Thread(chatServerWorker);
          th.start();

        }
    }

    synchronized boolean addClient(String nickName, ChatServerWorker chatServerWorker){
        if (chatServerWorkerList.containsKey(nickName)){
            return false;
        }
        chatServerWorkerList.put(nickName, chatServerWorker);
        return true;
    }

    synchronized boolean removeClient(String nickName, ChatServerWorker chatServerWorker){
        if (chatServerWorkerList.containsKey(nickName)){
            chatServerWorkerList.remove(nickName);
            return true;
        }
        return false;
    }

    synchronized void broadcast (String message){
        for (ChatServerWorker worker: chatServerWorkerList.values()){
            worker.sendMessage(message);
        }
    }

    synchronized boolean privateMessage (String sender, String receiver, String message){
        ChatServerWorker chatServerWorker = chatServerWorkerList.get(receiver);
        if (chatServerWorker!=null) {
            chatServerWorker.sendMessage(sender +": "+message);
            return true;
        }
        return false;
    }



}
