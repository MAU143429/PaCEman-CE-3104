package Socket;
import java.io.*;
import java.net.*;

/**
 * Client class
 * Esta clase es la encargada de establecer una conexion con el servidor
 * @author Mauricio C.Yendry B. Gabriel V.
 */
public class Client{

    /**
     * Variables utilizadas para la conexion
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    private Socket socket;
    private String hostname;
    private int port;
    private InputStream input;
    private DataOutputStream output;
    private InputStreamReader reader;

    /**
     * Metodo del cliente
     * Este metodo recibe el numero de puerto y la direccion ip
     * en donde se desea realizar la conexion y almacena estos datos.
     * @param hostname direccion ip
     * @param port puerto de conexion
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Metodo connect
     * Este metodo crea el socket utilizando los parametros de ip y port
     * realiza la conexion del socket
     * @return true en caso de establecer conexion
     * @return false en caso de no conectarse
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public boolean connect() {
        try {
            socket = new Socket(hostname, port);
            input = socket.getInputStream();
            output = new DataOutputStream(socket.getOutputStream());

            return true;
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        return false;
    }

    /**
     * Metodo disconnect
     * Este metodo desconecta el socket del servidor
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void disconnect() {
        try {
            socket.close();
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    /**
     * Metodo read
     * Este metodo permite leer los mensajes que llegan a traves del socket
     * @return el mensaje recibido
     * @return -1 en caso de no existir mensaje
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public String read() {
        try {
            reader = new InputStreamReader(input);
            int character;
            StringBuilder buffer = new StringBuilder();

            while ((character = reader.read()) != -1 && character != '/') {
                buffer.append((char) character);
            }
            if (character == -1) {
                return "-1";
            }
            return buffer.toString();
        } catch (UnknownHostException ex) {
            System.out.println("Servidor no encontrado al intentar leer: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        return "-1";
    }

    /**
     * Metodo send
     * Este metodo permite enviar un string a traves del socket
     * @param msg mensaje que se desea enviar
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void send(String msg) {
        try {
            byte[] data = msg.getBytes();
            output.write(data, 0, data.length);
            output.flush();
        } catch (UnknownHostException ex) {
            System.out.println("Servidor no encontrado al intentar leer: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

}