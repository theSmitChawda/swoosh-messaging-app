package com.client.clientapp;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    public Client(Socket socket) {
        try {
            this.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }
    public static void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        if(socket!=null) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(bufferedReader!=null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if(bufferedWriter!=null) {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void receiveMessageFromServer(VBox vBox) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        String msg = bufferedReader.readLine();
                        Controller.addMessage(msg, vBox);
                    } catch (IOException e) {
                        System.out.println("Error reading from server: in Client class");
                        e.printStackTrace();
                        closeEverything(socket,bufferedReader,bufferedWriter);
                        break;
                    }
                }
            }
        }).start();
    }

    public void sendMessageToServer(String msg) {
        try {
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Error sending message to server: in Client class");
            e.printStackTrace();
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
}
