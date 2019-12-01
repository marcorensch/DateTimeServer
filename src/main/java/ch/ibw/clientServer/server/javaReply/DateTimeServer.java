package ch.ibw.clientServer.server.javaReply;

import java.io.*;
import java.net.*;
class DateTimeServer {
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);               // Port-Nummer
            ServerSocket server = new ServerSocket(port);       // Server-Socket
            System.out.println("DateTimeServer läuft");         // Statusmeldung

            while(true){
                final Socket socket = server.accept();               // Client Verbindung akzeptieren
                new Thread(new Runnable() {
                    public void run() {
                        new DateTimeProtokoll(socket).transact();    // Protokoll abwickeln
                    }
                }).start();
            }
        } catch (ArrayIndexOutOfBoundsException ae) {
            System.out.println("Aufruf: java DateTimeServer <Port-Nr>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}