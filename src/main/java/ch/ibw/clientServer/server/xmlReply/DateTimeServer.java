package ch.ibw.clientServer.server.xmlReply;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class DateTimeServer {
    public static void main(String[] args) {
        try {
            int port = Integer.parseInt(args[0]);               // Port-Nummer
            ServerSocket server = new ServerSocket(port);       // Server-Socket
            System.out.println("DateTimeServer läuft");         // Statusmeldung

            while(true){                                        // Eine Verbindung nach den anderen annehmen
                final Socket socket = server.accept();          // Client Verbindung akzeptieren
                new Thread(new Runnable() {                     // Verbindung in Thread auslagern, und direkt auf neue warten
                    public void run() {
                        new DateTimeProtokoll(socket).transact(); // Protokoll im Thread abwickeln
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