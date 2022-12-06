package com.example.centroscommesse;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private BufferedReader response;
    private BufferedWriter request;

    Client(Socket socket) throws IOException {
        this.socket = socket;

        //System.out.println("😃 Server found!\n");
        // this isn't something you should be happy about
        System.out.println("Server found.\n");
        response = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        request = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
    }

    public int getBalance() throws IOException, NoReadyException {
        request.write("MONTEPREMI\r\n");
        String msg = response.readLine();
        if (msg.equalsIgnoreCase("NOREADY"))
            throw new NoReadyException("Match hasn't started yet");
        return Integer.parseInt(msg);
    }

    public boolean start(String key, int balance) throws IOException, UnauthorizedException {
        request.write(String.format("START,%s,%d\r\n", key, balance));
        String msg = response.readLine();
        if (msg.equalsIgnoreCase("NOAUTH"))
            throw new UnauthorizedException("Master key is wrong");
        return msg.equalsIgnoreCase("OK");
    }

    public boolean stop(String key) throws IOException, NoReadyException, UnauthorizedException {
        request.write(String.format("STOP,%s\r\n", key));
        String msg = response.readLine();
        if (msg.equalsIgnoreCase("NOAUTH"))
            throw new UnauthorizedException("Master key is wrong");
        if (msg.equalsIgnoreCase("NOREADY"))
            throw new NoReadyException("Match hasn't started yet");
        return msg.equalsIgnoreCase("OK");
    }

    public int bet(int stake, int number) throws IOException, NoMoneyException, NoReadyException {
        request.write(String.format("PUNTATA,%d,%d\r\n", stake, number));
        String msg = response.readLine();
        if (msg.equalsIgnoreCase("LOST"))
            return -stake;
        if (msg.equalsIgnoreCase("NOREADY"))
            throw new NoMoneyException("Match hasn't started yet");
        if (msg.equalsIgnoreCase("NOMONEY"))
            throw new NoMoneyException("Balance cashed out");
        if (msg.equalsIgnoreCase("LOST\r\n"))
            return -stake;
        String[] command = msg.split(",");
        return Integer.parseInt(command[1]);
    }

    // Helper method to close everything so you don't have to repeat yourself.
    public void closeEverything(Socket socket) {
        try {
            socket.close();
        } catch (IOException e) { }
    }
}
