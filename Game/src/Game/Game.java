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
 * Main game class, it constructs the board game and have functions to handle socket communication
 */
public class Game extends JPanel implements ActionListener{
    private Dimension dimension; //width x height
    private final Font smallFont = new Font("Monospaced", Font.BOLD, 14);
    private boolean inGame = false; //checks if game is running
    private boolean isAlive = false; //checks if pacman is alive

    private final int BLOCK_SIZE = 24; // block dimension
    private final int N_BLOCKS = 15; // number of blocks 15x15
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE; // nxn
    private final int MAX_GHOSTS = 12; // maximum amount of ghosts
    private final int PACMAN_SPEED = 6; // pacman's speed

    private int N_GHOSTS = 6; // amount of ghosts at the start of the game
    private int lives; // pacman lives
    private int score; // player score
    private int[] dx, dy; // position of ghosts
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed; // for ghosts

    // Images
    private Image heart;
    private Image ghost;
    private Image up, down, left, right;

    // for pacman
    private int pacman_x, pacman_y; //to change pacman's sprites
    private int pacmand_x, pacmand_y; // horizontal and vertical directions
    private int req_dx, req_dy; // Adapts the keys

    private final int validSpeeds[] = {1, 2, 3, 4, 6, 8}; // permitted speeds in game
    private final int maxSpeed = 6; // maximum speed of game
    private int currentSpeed = 3; // current speed of the game at the start
    private short[] screenData; // get game data to know what to show
    private Timer timer; // for animation

    // Socket connection
    private String message_received;
    private Client client;

    // structure of the level 15x15
    private final short levelData[] = {
            19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            17, 16, 16, 16, 16, 24, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            25, 24, 24, 24, 28, 0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
            0,  0,  0,  0,  0,  0, 17, 16, 16, 16, 16, 16, 16, 16, 20,
            19, 18, 18, 18, 18, 18, 16, 16, 16, 16, 24, 24, 24, 24, 20,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
            17, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
            17, 16, 16, 16, 24, 16, 16, 16, 16, 20, 0,  0,  0,   0, 21,
            17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 18, 18, 18, 18, 20,
            17, 24, 24, 28, 0, 25, 24, 24, 16, 16, 16, 16, 16, 16, 20,
            21, 0,  0,  0,  0,  0,  0,   0, 17, 16, 16, 16, 16, 16, 20,
            17, 18, 18, 22, 0, 19, 18, 18, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            17, 16, 16, 20, 0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 20,
            25, 24, 24, 24, 26, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28
    };


    public Game(Client client) {
        this.client = client;
        connect();
        send("HOLA SOY UN NUEVO CLIENTE");

    }

    //--------------Functions for socket connection---------------//
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
