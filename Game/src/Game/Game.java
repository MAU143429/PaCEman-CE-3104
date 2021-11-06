package Game;

import Socket.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Main game classs, it constructs the board game
 */
public class Game extends JPanel implements ActionListener{
    private Dimension dimension;
    private final Font smallFont = new Font("Monospaced", Font.BOLD, 14);
    private boolean inGame = false; //checks if game is running
    private boolean dying = false;

    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 12;
    private final int PACMAN_SPEED = 6;

    private int N_GHOSTS = 6;
    private int lives, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

    private Image heart, ghost;
    private Image up, down, left, right;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;

    private String message_received;
    private Client client;


    public Game(Client client) {
        this.client = client;
        connect();
        send("HOLA SOY UN NUEVO CLIENTE");

    }

    public void connect() {
        if (client.connect()) {
            System.out.println("Conexion exitosa!");
            startReading();
        } else {
            System.out.println("No se pudo establecer conexion con el servidor.");
        }
    }

    // Inicia un nuevo hilo para recibir los mensajes del servidor
    public void startReading() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    message_received = client.read();
                    if (message_received != "-1") {
                        System.out.println("Recibido: " + message_received);
                    } else {
                        break;
                    }
                }
                System.out.println("Cliente desconectado.");
                client.disconnect();
            }
        });
        thread.start();
    }


    public void send(String msg) {
        System.out.println("Enviando: " + msg);
        client.send(msg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
