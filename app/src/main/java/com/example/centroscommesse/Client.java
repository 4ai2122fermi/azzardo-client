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

        System.out.println("😃 Server found!\n");
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void closeEverything(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) { }
    }
}
