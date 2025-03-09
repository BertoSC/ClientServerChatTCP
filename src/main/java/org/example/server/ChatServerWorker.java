package org.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ChatServerWorker implements Runnable {
    Socket socket;
    ChatServer chatServer;
    String nickname;
    BufferedReader flujoIn;
    PrintWriter flujoOut;

    public ChatServerWorker(Socket socket, ChatServer chatServer) throws IOException {
        this.socket = socket;
        this.chatServer = chatServer;
        flujoIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        flujoOut = new PrintWriter(socket.getOutputStream(), true);
    }


    @Override
    public void run() {
        while (true) {
            try {
                String peticion = flujoIn.readLine();
                if (peticion == null) { // Cliente desconectado
                    chatServer.removeClient(nickname, this);
                    socket.close();
                    return;
                }


                String[] peticionParts = peticion.split(" ", 2);
                String comando = peticionParts[0];
                switch (comando) {
                    case "NICK":
                        String requestNick = peticionParts[1];
                        if (chatServer.addClient(requestNick, this)) {
                            this.nickname = requestNick;
                            sendMessage("OK NICK");
                        } else {
                            sendMessage("NICK EN USO");
                        }
                        break;

                    case "BCAST":
                        chatServer.broadcast(nickname + " : " + peticionParts[1]);
                        break;

                    case "MSG":
                        String[] peticionPrivada = peticion.split(" ", 3);
                        String receptor = peticionPrivada[1];
                        String message = peticionPrivada[2];
                        if (chatServer.privateMessage(nickname, receptor, message)) {
                            sendMessage("OK MSG");
                        } else {
                            sendMessage("ERROR: Usuario no encontrado.");
                        }
                        break;

                    case "QUIT":
                        sendMessage("BYE");
                        chatServer.removeClient(nickname, this);
                        socket.close();
                        return;

                    default:
                        sendMessage("UNKNOWN: Comando no reconocido.");

                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }


    }

    public void sendMessage(String message) {
        flujoOut.println(message);
    }
}

