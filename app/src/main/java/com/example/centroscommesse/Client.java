package com.example.centroscommesse;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    Client(Socket socket) throws IOException {
        this.socket = socket;

        //System.out.println("😃 Server found!\n");
        // this isn't something you should be happy about
        System.out.println("Server found!\n");

    }

    public int getBalance() {
        return 100;
    }

    public String start(String key, int balance) {
        return "NOAUTH";
    }

    public String stop(String key) {
        return "NOREADY";
    }

    public String bet(int balance) {
        return "LOST";
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void closeEverything(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) { }
    }
}
