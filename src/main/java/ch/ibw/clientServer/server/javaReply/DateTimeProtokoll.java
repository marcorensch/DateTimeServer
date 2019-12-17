package ch.ibw.clientServer.server.javaReply;

import ch.ibw.clientServer.shared.DateTimeInfo;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

class DateTimeProtokoll {
    // Formate für den Zeitpunkt
    private static SimpleDateFormat time = new SimpleDateFormat("'Es ist gerade 'H'.'mm' Uhr.'");
    private static SimpleDateFormat date = new SimpleDateFormat("'Heute ist 'EEEE', der 'dd.MM.yy");

    private Socket clientSocket;        // Socket in Verbindung mit dem Client
    private BufferedReader vomClient;   // Eingabe-Strom vom Client
    private PrintWriter zumClient;      // Ausgabe-Strom zum Client
    private ObjectOutputStream zumClientSerialized;

    public DateTimeProtokoll (Socket clientSocket) {
        try {
            this.clientSocket = clientSocket;
            vomClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            zumClient = new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("IO-Error");
            e.printStackTrace();
        }
    }

    // Methode die das Protokoll abwickelt
    public void transact() {
        System.out.println("Protokoll gestartet");
        try {
            zumClient.println("Geben Sie DATE oder TIME ein");
            String wunsch = vomClient.readLine();   // von Client empfangen
            Date jetzt = new Date();

            // vom Client empfangenes Kommando ausführen
            if (wunsch.equalsIgnoreCase("date")) {
                zumClient.println(new XmlSerializer().serialize(new DateTimeInfo(date.format(jetzt))));
            } else if (wunsch.equalsIgnoreCase("time")) {
                zumClient.println(new XmlSerializer().serialize(new DateTimeInfo(time.format(jetzt))));
            } else {
                zumClient.println(wunsch +" ist als Kommando unzulaessig!");
            }

            clientSocket.close();       // Socket, und damit auch Streams, schliessen
        } catch (IOException e) {
            System.out.println("IO-Error");
        }
        System.out.println("Protokoll beendet");
    }
}