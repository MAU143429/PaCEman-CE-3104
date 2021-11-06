package Socket;

import java.net.*;
import java.io.*;

/**
 * One more time use inheritance to apply threads and encapsulated variable
 */

public class Server extends Thread
{
    private ServerSocket server;
    private Socket socket;
    private DataInputStream in;
    int port;

    /** the class created a server in one determinate port, then when now connection
     * established the client can to send message to server.
     *i used constructor to send parameter of port
     */
    public Server (int port2){
        port = port2;



    }

    /**
     * i used while to always keep server started and only stop server connection when you send message "EXIT"
     *
     */
    @Override
    public void run() {
        try {

            server = new ServerSocket(port);
            System.out.println("Server start in port:" + port);
            System.out.println("Waiting for client...");
            socket = new Socket();
            socket = server.accept();
            System.out.println("Client Connect");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = " ";

            while (!line.equals("exit")) {
                try {
                    line = in.readUTF();
                    System.out.println(line);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println("closing connection");
            socket.close();

            in.close();
            socket.close();
        } catch(Exception e) {
        }

    }


}
