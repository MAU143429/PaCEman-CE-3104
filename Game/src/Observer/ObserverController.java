package Observer;

import Game.ViewController;
import Objects.*;
import Socket.Client;
import Socket.Observer_Action;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;

/**
 * ViewController Class
 * Esta clase es la principal, en ella se corre el juego y hereda de JPanel
 * y se implementa el ActionListener para detectar los movimiento del teclado.
 * @author Mauricio C.Yendry B. Gabriel V.
 */
public class ObserverController extends JPanel implements ActionListener {
    private Timer timer;
    private int clientType;
    private Characters blinky, pinky, clyde ,inky;
    private Pacman pacman;
    private ArrayList <Characters> characters;
    private ArrayList <Intersection> intersections;
    private boolean life, stop, panic, success;
    private Maps maps;
    private int score, panicTimer, dots;
    private Integer gameSpeed = 30;
    private Integer houseTimer = 0;
    private Integer totalGhost = 0;
    private Integer appleScore, orangeScore, melonScore, strawberryScore, cherryScore;
    private static ObserverController instance = null;
    private AudioClip eatDot, eatPill, eatGhost, death;

    // Conexion Socket
    private String messageReceived;
    private Client client;
    private int observer;


    /**
     * Contructor de la clase
     * En el se crea el cliente para la conexion y se crea un hilo para la ejecucion del juego
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public ObserverController(){
        //Connection
        Client client = new Client("127.0.0.1", 8888);
        this.client = client; // instantiate a client
        connect(); // client connect
        //send("O/");

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                newGame();
            }
        });
        thread1.start();
    }

    //////////////////////////////////////////////////// Getter and Setter Section ////////////////////////////////////////////////////////////

    public Integer getAppleScore() {
        return appleScore;
    }

    public void setAppleScore(Integer appleScore) {
        this.appleScore = appleScore;
    }

    public Integer getOrangeScore() {
        return orangeScore;
    }

    public void setOrangeScore(Integer orangeScore) {
        this.orangeScore = orangeScore;
    }

    public Integer getMelonScore() {
        return melonScore;
    }

    public void setMelonScore(Integer melonScore) {
        this.melonScore = melonScore;
    }

    public Integer getStrawberryScore() {
        return strawberryScore;
    }

    public void setStrawberryScore(Integer strawberryScore) {
        this.strawberryScore = strawberryScore;
    }

    public Integer getCherryScore() {
        return cherryScore;
    }

    public void setCherryScore(Integer cherryScore) {
        this.cherryScore = cherryScore;
    }

    public void setDots(Integer dots) {
        this.dots += dots;
    }

    public Integer getTotalGhost() {
        return totalGhost;
    }

    public void setTotalGhost(Integer totalGhost) {
        this.totalGhost += totalGhost;
    }

    public ArrayList<Characters> getCharacters() {
        return characters;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score -= score;
    }

    public void setCharacters(ArrayList<Characters> characters) {
        this.characters = characters;
    }

    public int getClientType() {
        return clientType;
    }

    public void setClientType(int clientType) {
        this.clientType = clientType;
    }

    public Integer getGameSpeed() {return gameSpeed;}

    public void setGameSpeed(Integer gameSpeed) {this.gameSpeed = gameSpeed;}

    public int getObserver() {
        return observer;
    }

    public void setObserver(int observer) {
        this.observer = observer;
    }

    public int getPacmanBoxX() {
        return pacman.getBoxX();
    }

    public int getPacmanBoxY() {
        return pacman.getBoxY();
    }

    public void mUp() {pacman.mUp();}

    public void mDown() {pacman.mDown();}

    public void mRight() {pacman.mRight();}

    public void mLeft() {pacman.mLeft();}


    /**
     * Metodo newGame
     * Este metodo es llamado por le contructor ya que en el se inicializan los valores de juego
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public void newGame(){

        //Opciones del JPanel
        setFocusable(true);
        setDoubleBuffered(true);
        setVisible(true);
        setBackground(Color.BLACK);
        //Creamos un timer y añadimos keylistener
        timer = new Timer(150, this);
        timer.start();
        addKeyListener(new TAdapter());
        //Creamos el laberinto
        maps = new Maps();
        dots = maps.totalDots();
        //Creamos el array para las casillas con los cruces
        intersections = new ArrayList <Intersection>();
        createIntersections();
        // Creamos el array para introducir todos los personajes que se mueven en el juego.
        characters = new ArrayList <Characters>();
        //Creamos Pacman
        pacman = new Pacman();
        characters.add(pacman);
        //Inicializamos las variables del juego
        panic =false;
        stop =false;
        life =true;
        success =false;
        score = 0;

        //Genero los audios
        URL url = ViewController.class.getResource("/Resources/eatDot.wav");
        eatDot = Applet.newAudioClip(url);
        URL url1 = ViewController.class.getResource("/Resources/eatPill.wav");
        eatPill = Applet.newAudioClip(url1);
        URL url2= ViewController.class.getResource("/Resources/eatGhost.wav");
        eatGhost = Applet.newAudioClip(url2);
        URL url3 = ViewController.class.getResource("/Resources/death.wav");
        death = Applet.newAudioClip(url3);
    }

    /**
     * Metodo paint
     * Este metodo es una sobreescritura de paint de Java
     * Y nos permite dibujar en el Jpanel
     * @param g tipo Graphics.
     * @Override Redefine el método paint
     */
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        for(int x = 0; x< maps.sizeMapX(); x++){
            for (int y = 0; y< maps.sizeMapY(); y++){
                g2d.drawImage(maps.getImage(x,y), x*60, y*60, this);
            }
        }
        for(Characters character: characters) {
            g2d.drawImage(character.getImage(), character.getX(), character.getY(), this);
        }
        if(stop){
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Resources/pause.png"));
            Image imageStop = imageIcon.getImage();
            g2d.drawImage(imageStop,0,0, this);
        }
        if(!life){
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Resources/game_over.png"));
            Image imageDeath = imageIcon.getImage();
            g2d.drawImage(imageDeath,0,0, this);
        }

        if(success){
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Resources/success.png"));
            Image imageSuccess = imageIcon.getImage();
            g2d.drawImage(imageSuccess,0,0, this);
        }

        g2d.setFont(new Font("SansSerif", Font.BOLD, 35));
        g2d.setColor(Color.green);
        g2d.drawString("Score: "+ score,300,820);
        if(panic){
            // Dibuja el contador del momento en que pacman puede comer fantasmas
            int time = panicTimer *(125)/1000;
            g2d.drawString("Time: "+ time,600,820);
        }

        // Dibuja los corazones que representan la vida de pacman
        for (int i = 0; i < pacman.pacmanLives(); i++) { // cuantas vidas tiene pacman
            ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("/Resources/heart.png"));
            Image imageHeart = imageIcon.getImage();
            g2d.drawImage(imageHeart,i*60+10,maps.sizeMapX()+770, this);
        }

        //Opciones del método paint()
        Toolkit.getDefaultToolkit().sync();
        setDoubleBuffered(true);
        g2d.dispose();
        repaint();
    }

    /**
     * Metodo createIntersections
     * Este metodo recorre la totalidad del mapa en busca de intersecciones las cuales son agregadas
     * a la lista de intersecciones.
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void createIntersections(){
        for(int x = 0; x< maps.sizeMapX(); x++){
            for (int y = 0; y< maps.sizeMapY(); y++){
                if(maps.getValue(x,y) != 0){
                    if( (maps.left(x,y) || maps.right(x,y)) && ( maps.down(x,y) || maps.up(x,y) )){
                        Intersection intersection = new Intersection(x,y);
                        intersections.add(intersection);
                    }
                }
            }
        }
    }

    /**
     * Metodo actionPerformed
     * Contiene la lógica del juego.
     * Llama a los métodos que mueve a los personajes, comprueban las colisiones.
     * @param e tipo ActionEvent.
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void actionPerformed(ActionEvent e){
        if(!stop){
            if(getScore() >= 1000){
                if (pacman.pacmanLives() < 3){
                    setScore(1000);
                    pacman.lives();
                    //send("L" + getClientType() + "+/");  ENVIO DE INFO
                }
            }

            //send("U"+ getClientType() + "," + pacman.getBoxX()+","+pacman.getBoxY()); ENVIO DE INFO

            for(Characters character: characters){
                verifyDirections(character);
                verifyIntersection(character);
                if(character instanceof Ghost){
                    Ghost ghost = (Ghost) character;
                    ghost.artificialIntelligence(pacman.getX(),pacman.getY());
                }
                character.move();
            }
            verifyCollider();//Método que comprueba las diferentes colisiones del juego y toma una decición
            eatDots();//Método que gestiona la ingesta de píldoras por parte de Comecocos
            //Método que gestiona el fin de partida cuando Comecocos acaba con todas las píldoras
            if(dots == 0){
                success=true;
                endGame();
                removeAll();
            }
            //Contador del pánico
            if((panic) && (!stop)){
                panicTimer--;
            }
            //Contador de los fantasmas en casa
            if(!stop){
                houseTimer--;
            }
            //Llamada al método panicoFin() cuando el contador llega a 0
            if(panicTimer == 0){
                endPanic();
            }
            //Llamada al método salirCasa(( cuando el contador llega a 0
            if(houseTimer == 0){
                leaveHouse();
            }
        }
    }


    /**
     * Metodo eatDots
     * Este metodo permite registrar cuando Pacman se come un objeto
     * registra pac-dots pildoras y frutas
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void eatDots(){
        //Sistema para comer las bolas de laberinto pequeñas
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 1){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            // send("C" + getClientType() + "/"); ENVIO INFO
            score += 10;
            eatDot.play();
            dots--;
        }
        //Sistema para comer las bolas de laberinto grandes
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 2){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            // send("M" + getClientType() + "/"); ENVIO INFO
            score += 50;
            eatPill.play();
            panicTimer = 40;
            panic();
            dots--;
        }
        //Sistema para comer frutas

        //Cereza
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 4){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            // send("F" + getClientType() + "C" + getCherry_score() + "/"); ENVIO INFO
            score += getCherryScore();
            dots--;
        }

        //Melon
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 5){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            // send("F" + getClientType() + "W" + getMelon_score() + "/"); ENVIO INFO
            score += getMelonScore();
            dots--;
        }

        //Manzana
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 6){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            // send("F" + getClientType() + "M" + getApple_score() + "/"); ENVIO INFO
            score += getAppleScore();
            dots--;
        }

        //Naranja
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 7){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            // send("F" + getClientType() + "N" + getOrange_score() + "/"); ENVIO INFO
            score += getOrangeScore();
            dots--;
        }

        //Fresa
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 8){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            // send("F" + getClientType() + "F" + getStrawberry_score() + "/"); ENVIO INFO
            score += getStrawberryScore();
            dots--;
        }

    }


    /**
     * Metodo verifyCollider
     * Este metodo permite verificar las colisiones entre pacman y un fantasma
     * al igual que las colisiones entre fantasmas
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void verifyCollider()
    {
        for(Characters character: characters){
            //Se recorre el laberinto con sus diferentes valores, si es 0, que corresponde con el muro, se paran los personajes.
            for(int x =0; x< maps.sizeMapX(); x++){
                for (int y =0; y< maps.sizeMapY(); y++){
                    if (maps.getValue(x,y) == 0){
                        Rectangle characterRect = character.createRectangle();
                        Rectangle wall = new Rectangle(x*60,y*60,60,60);
                        if(characterRect.intersects(wall)){
                            character.stop();
                        }
                    }
                }

                //Colisiones entre fantasmas
                if(character instanceof Ghost){
                    Rectangle blinkyRect = character.createRectangle();
                    Rectangle pinkyRect = character.createRectangle();
                    Rectangle clydeRect = character.createRectangle();
                    //Rectangle inkyRect = character.createRectangle();

                    if (getTotalGhost() >= 2) {
                        if (blinkyRect.intersects(pinkyRect)) {
                            blinky.back();
                            pinky.back();
                        }
                        if (getTotalGhost() >= 3) {
                            if (blinkyRect.intersects(clydeRect)) {
                                blinky.back();
                                clyde.back();
                            }

                            if (clydeRect.intersects(pinkyRect)) {
                                pinky.back();
                                clyde.back();
                            }

                            if (getTotalGhost() >= 4) {

                                /**if(blinkyRect.intersects(inkyRect)){
                                    blinky.back();
                                    inky.back();
                                }
                                if(clydeRect.intersects(inkyRect)){
                                    inky.back();
                                    clyde.back();
                                }
                                if(inkyRect.intersects(pinkyRect)){
                                    pinky.back();
                                    inky.back();
                                }*/

                            }

                        }
                    }

                }

            }
            //Sistema de colision entre pacman y los fantasmas
                if(character instanceof Ghost){
                    Rectangle ghostRect = character.createRectangle();
                    Rectangle pacmanRect = pacman.createRectangle();
                    if(ghostRect.intersects(pacmanRect)){
                        int lives = pacman.pacmanLives();
                        if(!panic){
                            death.play();
                            pacman.pacmanDeath();
                            //send("L" + getClientType() + "-/"); ENVIO DE INFO
                            if(lives == 0) {
                                endGame();
                            }
                        }
                        if(panic){
                            score = score + 500;
                            eatGhost.play();
                            // send("G"+getClientType()+"/"); ENVIO INFO
                            Ghost ghost = (Ghost)character; //Cast al personaje para declararlo de la clase Fantasmas
                            deathGhost(ghost);
                        }
                    }
                }
        }
    }


    /**
     * Metodo verifyIntersection
     * Este metodo permite verificar las intersecciones que tiene un character
     * @param character a quien verificaran la interseccion
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void verifyIntersection(Characters character)
    {
        Rectangle r1 = character.createRectangle();
        for(Intersection intersection: intersections){
            Rectangle r2 = intersection.createRectangle();
            if(r1.contains(r2)){
                character.intersection();
            }
        }

    }


    /**
     * Metodo verifyDirections
     * Este metodo comprueba las direcciones libres de un personaje
     * @param character el personaje
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void verifyDirections(Characters character)
    {
        int x = character.getBoxX();
        int y = character.getBoxY();
        character.availableDirections(maps.up(x,y), maps.down(x,y), maps.right(x,y), maps.left(x,y));
    }


    /**
     * Metodo panic
     * Este metodo inicia el modo panico cuando pacman se comio una pildora
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void panic()
    {
        panic = true;
        for(Characters character: characters){
            if(character instanceof Ghost){
                Ghost ghost = (Ghost) character;
                ghost.panic();
            }
        }
    }


    /**
     * Metodo endPanic
     * Este metodo finaliza el modo panic
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void endPanic()
    {
        panic = false;
        for(Characters character: characters){
            if(character instanceof Ghost){
                Ghost ghost = (Ghost) character;
                ghost.finalPanic();
            }
        }
    }


    /**
     * Metodo deathGhost
     * Este metodo elimina al Ghost cuando este muere
     * @param ghost el fantasma que muere
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void deathGhost(Ghost ghost)
    {
        ghost.death();
        houseTimer=20;
    }


    /**
     * Metodo leaveHouse
     * Este metodo gestiona la salida de los fantasma de la casa
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void leaveHouse(){
        for(Characters character: characters){
            if(character instanceof Ghost){
                Ghost ghost = (Ghost) character;
                if(ghost.isDeath()){
                    ghost.exit();
                }
            }
        }
    }


    /**
     * Metodo pause
     * Este metodo permite gestionar cuando el juego se encuentra en pausa
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public boolean stop(){
        stop = !stop;
        return stop;
    }


    /**
     * Metodo nextGame
     * Este metodo permite crear la siguiente partida
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void nextGame()
    {
        if((success) || (!life)){
            //newGame();
            System.out.println("SE DEBERIA INICIAR UN NUEVO JUEGO");
        }
    }


    /**
     * Metodo endGame
     * Este metodo gestiona el fin de la partida
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void endGame()
    {
        life = false;
        removeAll();
        timer.stop();
    }


    /**
     * Metodo calcX
     * Este metodo permite calcular la posicion en pixeles de una casilla a partir de
     * su valor X
     * @param x el valor en x de la matriz
     * @return el valor en pixeles de la ubicacion de la casilla
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int calcX(int x)
    {
        return x*60 ;
    }

    /**
     * Metodo calcY
     * Este metodo permite calcular la posicion en pixeles de una casilla a partir de
     * su valor Y
     * @param y el valor en y de la matriz
     * @return el valor en pixeles de la ubicacion de la casilla
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int calcY(int y)
    {
        return y*60;
    }


    /**
     * Metodo addPill
     * Este metodo permite agregar una pildora al juego en una fila y columan determinada
     * @param row el valor de la fila
     * @param col el valor de la columna
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public void addPill(Integer row, Integer col){
        maps.addPill(row,col);
    }


    /**
     * Metodo changeSpeed
     * Este metodo permite cambiar la velocidad del juego
     * 1 - Lento
     * 2 - Medio
     * 3 - Rapido
     * @param newSpeed el nuevo valor de la velocidad
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void changeSpeed(Integer newSpeed){

        if(newSpeed == 1){
            setGameSpeed(20);
        }else if(newSpeed == 2){
            setGameSpeed(30);
        }else if(newSpeed == 3){
            setGameSpeed(60);
        }
    }
    /**
     * Metodo pacmanLocation
     * Este metodo permite cambiar la posicion de pacman en el juego
     * @param new_x el nuevo valor de x
     * @param new_y el nuevo valor de y
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void pacmanLocation(Integer new_x, Integer new_y){
        pacman.setX(new_x);
        pacman.setY(new_y);

    }

    /**
     * Metodo addFruit
     * Este metodo permite agregar una fruta al juego con un valor definido y
     * en lugar determinado
     * @param row el valor de la fila
     * @param col el valor de la columna
     * @param value el valor de la fruta
     * @param fruit el tipo de friuta
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void addFruit(Character fruit, Integer row, Integer col, Integer value){


        //fresa
        if(fruit == 'F'){
            maps.addFruit(row,col,8);
            setStrawberryScore(value);
        }
        //naranja
        if(fruit == 'N'){
            maps.addFruit(row,col,7);
            setOrangeScore(value);
        }
        //manzana
        if(fruit == 'M'){
            maps.addFruit(row,col,6);
            setAppleScore(value);
        }
        //melon
        if(fruit == 'W'){
            maps.addFruit(row,col,5);
            setMelonScore(value);
        }

        //cereza
        if(fruit == 'C'){
            maps.addFruit(row,col,4);
            setCherryScore(value);
        }
    }

    /**
     * Metodo getValue
     * Este metodo permite agregar a un fantasma en una posicion determinada
     * @param row el valor de la fila
     * @param col el valor de la columna
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void addGhost(Integer row, Integer col){

        if(maps.verifyBox(row,col)){
            if (getTotalGhost() == 0){
                //Creamos Blinky
                blinky = new Blinky(calcX(row),calcY(col));
                getCharacters().add(blinky);
                setTotalGhost(1);

            }else if(getTotalGhost() == 1){
                //Creamos Pinky
                pinky = new Pinky(calcX(row),calcY(col));
                getCharacters().add(pinky);
                setTotalGhost(1);

            }else if(getTotalGhost() == 2) {

                //Creamos Clyde
                clyde = new Clyde(calcX(row),calcY(col));
                getCharacters().add(clyde);
                setTotalGhost(1);

            }else if(getTotalGhost() == 3){
                /**Creamos Inky
                //inky = new Inky(calcX(col),calcY(row));
                //getCharacters().add(inky);*/
                setTotalGhost(1);
            }



        }else{
            System.out.println("NO SE PUEDE AGREGAR EL FANTASMA EN EL LUGAR SOLICITADO YA QUE ES UN MUERO, INTENTA CON OTRO");
        }

    }

    /**
     * getInstance
     * @return instance
     * Método singleton del ViewController
     * @author Mauricio C Yendry B Gabriel Vargas
     *
     */
    public static ObserverController getInstance(){
        if(instance == null){
            instance = new ObserverController();
        }
        return instance;
    }


    //--------------Funciones para conexion socket---------------//
    /**
     * Metodo connect
     * Este metodo permite conectar al cliente con el servidor
     * @author Mauricio C.Yendry B. Gabriel V.
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
     * Metodo startReading
     * Este metodo permite obtener el mensaje recibido por el Socket
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void startReading() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    messageReceived = client.read();
                    if (messageReceived != "-1") {
                        System.out.println("Recibido: " + messageReceived);
                        Observer_Action.observerRecv(messageReceived);
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
     * Metodo send
     * Este metodo permite enviar mensajes hacia el servidor
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void send(String msg) {
        System.out.println("Enviando: " + msg);
        client.send(msg);
    }

    /**
     * Metodo keypress
     * Este metodo permite detectar los eventos en teclado y ejecuta algun evento
     * dependiendo de la tecla presionada
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void keyPress(KeyEvent e)
    {

    }

    /**
     * TAdapter class
     * Esta clase hereda de KeyAdapter y permite llamar al Kerypressed
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    private class TAdapter extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            keyPress(e);
        }
    }
}
