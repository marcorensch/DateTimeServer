package ch.ibw.clientServer.server.dateReply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

/**
 * A simple TCP server. When a client connects, it sends the client the current
 * datetime, then closes the connection. This is arguably the simplest server
 * you can write. Beware though that a client has to be completely served its
 * date before the server will be able to handle another client.
 *
 * @see <https://cs.lmu.edu/~ray/notes/javanetexamples/>
 */
public class DateServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket listener = new ServerSocket(6060)) { // Port reservieren
            System.out.println("DateServer läuft");

            while(true){
                try (Socket socket = listener.accept()) {   // Warte auf Clientverbindung
                    PrintWriter zumClient = new PrintWriter(socket.getOutputStream(), true);
                    zumClient.println("Datumsformat?");

                    BufferedReader vomClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String dateFormat = vomClient.readLine();

                    String date = "unbekanntes Format";
                    if(dateFormat.equalsIgnoreCase("date")){
                        date = new Date().toString();
                    } else if (dateFormat.equalsIgnoreCase("time")){
                        date = DateFormat.getTimeInstance().format(new Date());
                    }

                    zumClient.println(date);
                }
            }
        }
    }
}