package com.compek;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket socket;

    public static void main(String[] args) throws IOException {
        sendPreparedText();
//        sendObject();
//        sendBulkText();
//        sendTextFromConsole();
    }

    private static void init() throws IOException {
        socket = new Socket("localhost", 8182);
        System.out.println("Client initialized!");
    }

    public static void sendBulkText() throws IOException {
        for (int i = 0; i < 20; i++) {
            init();

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeBytes(ConnectionType.TEXT.getType() + '\n');

            String dataFromClient = "sample data " + i;
            System.out.println("request: " + dataFromClient);
            dataOutputStream.writeBytes(dataFromClient + '\n');

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String dataFromServer = inFromServer.readLine();
            System.out.println("response: " + dataFromServer);

            socket.close();
        }
    }

    public static void sendObject() throws IOException {
        init();

        OutputStream outputStream = socket.getOutputStream();

        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeBytes(ConnectionType.OBJECT.getType() + '\n');

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        SampleObject dataObject = new SampleObject("sample data in object");
        objectOutputStream.writeObject(dataObject);

        String dataFromClient = dataObject.getText();
        System.out.println("request: " + dataFromClient);

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String dataFromServer = inFromServer.readLine();
        System.out.println("response: " + dataFromServer);
        socket.close();
    }

    public static void sendPreparedText() throws IOException {
        init();

        String dataFromClient = "asdf 1234567";
        System.out.println("request: " + dataFromClient);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeBytes(ConnectionType.TEXT.getType() + '\n');
        dataOutputStream.writeBytes(dataFromClient + '\n');

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String dataFromServer = inFromServer.readLine();
        System.out.println("response: " + dataFromServer);
        socket.close();
    }

    public static void sendTextFromConsole() throws IOException {
        init();

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String dataFromClient = inFromUser.readLine();
        inFromUser.readLine();
        System.out.println("request: " + dataFromClient);

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataOutputStream.writeBytes(ConnectionType.TEXT.getType() + '\n');
        dataOutputStream.writeBytes(dataFromClient + '\n');

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String dataFromServer = inFromServer.readLine();
        System.out.println("response: " + dataFromServer);
        socket.close();
    }
}
