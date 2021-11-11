package Game;

import Socket.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Main game class, it constructs the board game and have functions to handle socket communication
 */
public class GameFunc extends JPanel implements ActionListener {
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

    /** structure of the level 15x15
     * 0 -> blocks, obstacles inside the board
     * 1 -> left wall
     * 2 -> top wall
     * 4 -> right wall
     * 8 -> bottom wall
     * 16 -> dots to eat
     * 19 -> need 1 + need 2 + need 16 = 19
     * 28 -> need 8 + need 4 + need 16 = 28
     * 22 -> need 2 + need 4 + need 16 = 22
     * 20 -> need 4 + need 16 = 20
     * 25 ->
     * */

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

    /**
     * Class constructor
     * @param //client receives a new client to connect
     */
    public GameFunc() {
        // Connection
        //this.client = client; // instantiate a client
        //connect(); // client connect
        //send("HOLA SOY UN NUEVO CLIENTE");

        // game
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();

    }

    private void loadImages() {
        down = new ImageIcon(getClass().getResource("/Resources/down.gif")).getImage();
        up = new ImageIcon(getClass().getResource("/Resources/up.gif")).getImage();
        left = new ImageIcon(getClass().getResource("/Resources/left.gif")).getImage();
        right = new ImageIcon(getClass().getResource("/Resources/right.gif")).getImage();
        ghost = new ImageIcon(getClass().getResource("/Resources/ghost1.gif")).getImage();
        heart = new ImageIcon(getClass().getResource("/Resources/heart.png")).getImage();

    }
    private void initVariables() {

        screenData = new short[N_BLOCKS * N_BLOCKS];
        dimension = new Dimension(400, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        dx = new int[4];
        dy = new int[4];

        timer = new Timer(40, this);
        timer.start();
    }

    /**
     * Metodo que analiza si pacman esta vivo, llama a las funciones de movimiento y dibujo de
     * los componentes y chequea el mapa
     * @param g2d
     */
    private void playGame(Graphics2D g2d) {

        if (isAlive) {

            death(); // cuando pacman muere, se llama a la funcion death() para saber que hacer en el juego

        } else { // si pacman esta vivo

            movePacman();
            drawPacman(g2d);
            moveGhosts(g2d);
            checkMaze();
        }
    }

    /**
     * Metodo que inicia el juego con todos los valores iniciales para vidas, puntajes
     * numero de fantasmas y velocidad
     */
    public void initGame() {

        lives = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 6;
        currentSpeed = 3;
    }

    /**
     * Metodo que inicia el nivel por medio de un for tomando la informacion del atributo
     * levelData
     */
    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }

        continueLevel();
    }

    /**
     * Define la posicion de Pacman y la de los fantasmas, les asigna una velocidad
     * aleatoria
     */
    private void continueLevel() {

        int dx = 1;
        int random;
        // Se define la posicion de los fantasmas
        for (int i = 0; i < N_GHOSTS; i++) {

            ghost_y[i] = 4 * BLOCK_SIZE; // posicion inicial en y
            ghost_x[i] = 4 * BLOCK_SIZE; // posicion inicial en x
            ghost_dy[i] = 0; //
            ghost_dx[i] = dx;
            dx = -dx;

            // Se define la velocidad de los fantasmas
            random = (int) (Math.random() * (currentSpeed + 1));
            if (random > currentSpeed) {
                random = currentSpeed;
            }

            ghostSpeed[i] = validSpeeds[random]; // velocidades aceptadas
        }
        // Se define la posicion de pacman
        pacman_x = 7 * BLOCK_SIZE;  // posicion inicial en x
        pacman_y = 11 * BLOCK_SIZE; // posicion inicial en y
        pacmand_x = 0;	// reset de direccion de movimiento en x
        pacmand_y = 0; // reset direccion de movimiento en y
        req_dx = 0;	// reset de los controles de las teclas en x
        req_dy = 0; // reset de los controles de las teclas en y
        isAlive = false; // pacman esta vivo
    }



    //------------------Draw and paint the game-------------------//

    /**
     * Dibuja los componentes
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // hereda y construye de la clase padre

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black); // color de fondo
        g2d.fillRect(0, 0, dimension.width, dimension.height); // dibuja los rectangulos que seran los bloques

        drawMaze(g2d);
        drawScore(g2d);

        if (inGame) { // si pacman aun esta vivo en el juego
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    /**
     * Dibuja el texto que explica como iniciar el juego
     * @param g2d
     */

    private void showIntroScreen(Graphics2D g2d) {

        String start = "Press SPACE to start"; // se debe presionar espacio para comenzar el juego
        g2d.setColor(Color.yellow); // color
        g2d.drawString(start, (SCREEN_SIZE)/4, 150); // dibuja el string en el tablero
    }

    /**
     * Dibuja el texto que muestra puntaje del jugador
     * @param g
     */
    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(31, 187, 1));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);

        for (int i = 0; i < lives; i++) { // cuantas vidas tiene pacman
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this); // dibuja las vidas en la pantalla de juego
        }
    }

    /**
     * Dibuja los gantasmas en la posicion x, y que se necesite
     * @param g2d
     * @param x
     * @param y
     */
    private void drawGhost(Graphics2D g2d, int x, int y) {
        g2d.drawImage(ghost, x, y, this);
    }

    /**
     * Dibuja a Pacman en la interfaz, llama a las imagenes cargadas en loadImages() para asociar la direccion
     * con la imagen que corresponde
     * @param g2d
     */
    private void drawPacman(Graphics2D g2d) {

        if (req_dx == -1) { // movimiento izquierda -> imagen a la izquierda
            g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) { // movimiento derecha -> imagen a la derecha
            g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) { // movimiento arriba -> imagen a la arriba
            g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else { // movimiento abajo -> imagen a la abajo
            g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }

    /**
     * Dibuja el tablero
     * @param g2d
     */
    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;
        // recorre el arreglo
        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(new Color(0,72,251)); // color
                g2d.setStroke(new BasicStroke(5)); // grosor del borde

                if ((levelData[i] == 0)) { // si es un bloque, obstaculo
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE); //dibuja el bloque
                }

                if ((screenData[i] & 1) != 0) { // si es una pared o borde izquierdo
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1); //dibuja linea
                }

                if ((screenData[i] & 2) != 0) { // si es una pared o borde arriba
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y); //dibuja linea
                }

                if ((screenData[i] & 4) != 0) { // si es una pared o borde derecho
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1); //dibuja linea
                }

                if ((screenData[i] & 8) != 0) { // si es una pared o borde abajo
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1); //dibuja linea
                }

                if ((screenData[i] & 16) != 0) { // dibuja los puntos pequenos para comer
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }

                i++;
            }
        }
    }


    //------------------------Check status------------------------//

    /**
     * Metodo que analiza constantemente el tablero
     */
    private void checkMaze() {

        int i = 0;
        boolean finished = true;

        while (i < N_BLOCKS * N_BLOCKS && finished) {

            if ((screenData[i]) != 0) {
                finished = false;
            }

            i++;
        }

        if (finished) { // todos los puntos pequenos fueron comidos

            score += 50;
            if (N_GHOSTS < MAX_GHOSTS) {
                N_GHOSTS++;
            }

            if (currentSpeed < maxSpeed) {
                currentSpeed++;
            }

            initLevel(); // se reinicia el nivel
        }
    }

    /**
     * Determina lo que sucede cuando pacman pierde una vida, si ya no tiene vidas pacman muere y termina el juego
     */
    private void death() {

        lives--;

        if (lives == 0) {
            inGame = false;
        }

        continueLevel();
    }

    //--------------------------Movement--------------------------//

    /**
     * Controla el movimiento de los fantasmas
     * @param g2d
     */
    private void moveGhosts(Graphics2D g2d) {

        int pos;
        int count;

        for (int i = 0; i < N_GHOSTS; i++) { //Posicion de todos los fantasmas en el tablero
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);
                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) { // tiene una pared izquierda
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) { // pared arriba
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) { // pared derecha
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) { // pared abajo
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) { //
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else { // posicion, en cual bloque esta del tablero
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    } // lo mueve hacia la izquierda sino tiene obstaculo ni tampoco se estaba moviendo a la derecha

                } else { // no tienes obstaculos ni paredes

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }

            }

            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            drawGhost(g2d, ghost_x[i] + 1, ghost_y[i] + 1); // dibuja los fantasmas

            // Pacman pierde una vida si choca contra un fantasma
            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {

                isAlive = true; // cambia el estado de pacman y cuando se chequee el juego va a llamar la funcion death()
            }
        }
    }

    /**
     * Controla el movimiento de pacman
     */
    private void movePacman() {

        int pos; // posicion de pacman
        short checkData;


        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            checkData = screenData[pos];


            if ((checkData & 16) != 0) { // Posicion donde pacman puede comer un punto
                screenData[pos] = (short) (checkData & 15);
                score++; // si pacman paso por ahi, se suma en el score
            }
            // control de pacman
            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (checkData & 1) != 0)  // si no se mueve izq en x, no se mueve en y y no hay pared a la izq
                        || (req_dx == 1 && req_dy == 0 && (checkData & 4) != 0) // se mueve a la derecha en x, no se mueve en y, y
                        || (req_dx == 0 && req_dy == -1 && (checkData & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (checkData & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }

            // Analiza si para
            if ((pacmand_x == -1 && pacmand_y == 0 && (checkData & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (checkData & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (checkData & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (checkData & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    //--------------Functions for socket connection---------------//

    /**
     * Conecta el cliente con el servidor
     */
    public void connect() {
        if (client.connect()) {
            System.out.println("Conexion exitosa!");
            startReading();
        } else {
            System.out.println("No se pudo establecer conexion con el servidor.");
        }
    }

    /**
     * Inicia un nuevo hilo para recibir los mensajes del servidor
     */
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

    /**
     * Envia mensajes hacia el servidor
     * @param msg
     */
    public void send(String msg) {
        System.out.println("Enviando: " + msg);
        client.send(msg);
    }

    //controls
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (inGame) {
                if (key == KeyEvent.VK_LEFT) {
                    req_dx = -1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    req_dx = 1;
                    req_dy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    req_dx = 0;
                    req_dy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    req_dx = 0;
                    req_dy = 1;
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    inGame = false;
                }
            } else {
                if (key == KeyEvent.VK_SPACE) {
                    System.out.println(inGame);
                    inGame = true;
                    System.out.println(inGame);
                    initGame();
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        System.out.println(inGame);
    }
}
