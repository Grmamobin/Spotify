package com.example.spotify.Handler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    public static List<Socket> sockets;
    public static List<Thread> threads;
    public static void main(String[] args) {
        sockets = new ArrayList<>();
        threads = new ArrayList<>();
        try {

            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Server is connected....");

            while (true){
                Socket socket = serverSocket.accept();
                System.out.println("IP address of client is:" + socket.getLocalAddress() + ". and it has been excepted .");
                ServerApplication application = new ServerApplication(socket);
                sockets.add(socket);
                Thread thread = new Thread((Runnable) application);
                threads.add(thread);
                thread.start();
            }

        } catch (IOException ioException){
            ioException.printStackTrace();
        }

    }

}