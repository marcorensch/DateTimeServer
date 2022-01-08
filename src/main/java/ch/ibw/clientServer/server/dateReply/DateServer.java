package ch.ibw.clientServer.server.dateReply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
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
        try (ServerSocket listener = new ServerSocket(6060)) {
            System.out.println("DateServer läuft");
            while(true) {
                final Socket socket = listener.accept();
                new Thread(() -> {
                    try {
                        transact(socket);
                    }catch (Exception ex){
                        System.out.println(ex);
                    }
                }).start();
            }
        }
    }

    private static void transact(Socket socket) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println("Was möchtest du (date/time)?");     // Sende Optionen an Client

        BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String dateOrTime = fromClient.readLine();

        System.out.println(dateOrTime);
        if (dateOrTime.equalsIgnoreCase("date")) {
            out.println(new Date());
        } else if (dateOrTime.equalsIgnoreCase("time")) {
            out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
        socket.close();
    }
}