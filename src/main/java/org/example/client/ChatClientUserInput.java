package org.example.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientUserInput implements Runnable {
    Socket socket;
    Scanner sc;
    BufferedReader flujoIn;
    PrintWriter flujoOut;

    public ChatClientUserInput(Socket socket) throws IOException {
        this.socket=socket;
        this.sc = new Scanner(System.in);
        this.flujoIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.flujoOut = new PrintWriter(socket.getOutputStream(), true);
    }
    @Override
    public void run() {

        Thread receptionThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String response;
                try {
                    while ((response = flujoIn.readLine()) != null) {
                        System.out.println(response);
                    }
                }catch (IOException e) {
                    System.out.println("Conexión cerrada");
                }

            }
        });

        receptionThread.start();

        while(true){
            try { System.out.println("Escribe tu comando");
            String peticion = sc.nextLine();
            flujoOut.println(peticion);
            if (peticion.equals("QUIT")) {

                socket.close();

                break;
            }
            }  catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
