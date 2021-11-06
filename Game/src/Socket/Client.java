package Socket;

import java.io.*;
import java.net.*;


/**
 * this class create a client and connect to server port using sockets.
 * i used threads and encapsulated some  variable, with help from constructor i used a port parameter to connect with server
 */
public class Client {

        private Socket s;
        private DataInputStream in;
        private DataOutputStream out;
        /**
         *in run method i used inheritance from Thread class, then i connect to local host and created a socket that allowed me to communicate
         * with a port to transfers a string.I used try catch to avoid error
         */

        public Client(String address, int port){
            try{
                s = new Socket(address, port);
                System.out.println("Connected");

                in = new DataInputStream(System.in);
                out = new DataOutputStream(s.getOutputStream());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }

            String line ="";
            while(!line.equals("Exit")){
                try{

                    line = in.readLine();
                    out.writeUTF(line);

                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }

        }
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",4433);

    }

}
