package Game;

import Objects.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Write a description of class VistaControlador here.
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class ViewController extends JPanel implements ActionListener {
    private Timer timer;
    private Characters blinky, pinky, clyde;//, inky;
    private Pacman pacman;
    private ArrayList <Characters> characters;
    private ArrayList <Intersection> intersections;
    private boolean life, stop, panic, success;
    private Maps maps;
    private int score, panicTimer, houseTimer, dots;

    /**
     * Constructor que crea un nuevo juego
     */
    public ViewController(){
        newGame();
    }

    /**
     * Método al que llama el constructor
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
        //Creamos Blinky
        blinky = new Blinky();
        characters.add(blinky);
        //Creamos Pinky
        pinky = new Pinky();
        characters.add(pinky);
        //Creamos Clyde
        clyde = new Clyde();
        characters.add(clyde);
        /*//Creamos Inky
        //inky = new Inky();
        //characters.add(inky);*/
        //Inicializamos las variables del juego
        panic =false;
        stop =false;
        life =true;
        success =false;
        score = 0;
    }

    /**
     * Redefinimos el método paint(g)
     * Primero llamamos al método paint de la superclase.
     * Pintamos el laberinto, los personajes y dos imagenes en caso de pausa o muerte.
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
        //Pintamos el marcador con la puntuación
        g2d.setFont(new Font("Arial", Font.PLAIN, 54));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Marcador",1020,180);
        g2d.drawString("Puntos: "+ score,1020,240);
        if(panic){
            int time = panicTimer *(125)/1000;
            g2d.drawString("Tiempo: "+ time,1020,300);
        }
        //Opciones del método paint()
        Toolkit.getDefaultToolkit().sync();
        setDoubleBuffered(true);
        g2d.dispose();
        repaint();
    }

    /**
     * Metodo que comprueba los cruces y codos de un laberinto y los guarda en un array
     *
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
     * Contiene la lógica del juego.
     * Llama a los métodos que mueve a los personajes, comprueban las colisiones.
     * @param e tipo ActionEvent.
     */
    public void actionPerformed(ActionEvent e){
        if(!stop){
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
     * Comecocos come las píldoras del juego
     *
     */
    public void eatDots(){
        //Sistema para comer las bolas de laberinto pequeñas
        System.out.println("Sistema para comer las bolas de laberinto pequeñas");
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 1){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            score += 10;
            dots--;
        }
        //Sistema para comer las bolas de laberinto grandes
        if(maps.getValue(pacman.getBoxX(), pacman.getBoxY()) == 2){
            maps.eatDot(pacman.getBoxX(),pacman.getBoxY());
            score += 20;
            panicTimer = 40;
            panic();
            dots--;
        }
    }

    /**
     * Metodo que comprueba las diferentes colisiones de los personajes
     *
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
                    if(blinkyRect.intersects(pinkyRect)){
                        blinky.back();
                        pinky.back();
                    }
                    if(blinkyRect.intersects(clydeRect)){
                        blinky.back();
                        clyde.back();
                    }
                    if(clydeRect.intersects(pinkyRect)){
                        pinky.back();
                        clyde.back();
                    }

                    /*if(blinkyRect.intersects(inkyRect)){
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
            //Sistema de colision entre Comecocos y los fantasmas
                if(character instanceof Ghost){
                    Rectangle ghostRect = character.createRectangle();
                    Rectangle pacmanRect = pacman.createRectangle();
                    System.out.println("rectangulo fantasma" + ghostRect.getLocation());
                    System.out.println("rectangulo pacman" + pacmanRect.getLocation());
                    if(ghostRect.intersects(pacmanRect)){
                        int lives = pacman.pacmanLives();
                        System.out.println("Vidas actualmente: " + lives);
                        System.out.println("coordenadas de pacman: " + pacman.getBoxX()+ "," + pacman.getBoxY());
                        System.out.println("coordenadas de fantasma: " + character.getBoxX()+ "," + character.getBoxY());
                        if(!panic){
                            pacman.pacmanDeath();
                            System.out.println("Quitandole vida a pacman");
                            if(lives == 0) {
                                System.out.println("Murio pacman");
                                endGame();
                            }
                        }
                        if(panic){
                            score = score + 500;
                            Ghost ghost = (Ghost)character; //Cast al personaje para declararlo de la clase Fantasmas
                            deathGhost(ghost);
                        }
                    }
                }
        }
    }

    /**
     * Metodo que comprueba si un fantasma se encuentra en un cruce
     * @param character tipo Personajes.
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
     * Metodo que comprueba las direcciones libres de los personajes
     * @param character tipo Personajes.
     */
    public void verifyDirections(Characters character)
    {
        int x = character.getBoxX();
        int y = character.getBoxY();
        character.availableDirections(maps.up(x,y), maps.down(x,y), maps.right(x,y), maps.left(x,y));
    }

    /**
     * Metodo que inicia modo pánico
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
     * Metodo que finaliza modo pánico
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
     * Metodo que gestiona la muerte de los fantasmas
     * @param ghost tipo Fantasmas
     */
    public void deathGhost(Ghost ghost)
    {
        ghost.death();
        houseTimer=20;
    }

    /**
     * Metodo que gestiona la salida de los fantasmas de casa
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
     * Metodo que gestiona la pausa del juego
     * @return tipo booleano pausa
     */
    public boolean stop(){
        stop = !stop;
        return stop;
    }

    /**
     * Metodo que crea un nuevo juego
     */
    public void nextGame()
    {
        if((success) || (!life)){
            newGame();
        }
    }

    /**
     * Metodo que gestiona el fin de partida
     */
    public void endGame()
    {
        life = false;
        removeAll();
        timer.stop();
    }

    public void keypress(KeyEvent e)
    {
        int code = e.getKeyCode();
        switch (code)
        {
            case 38:
                pacman.moveUp();
                break;
            case 39:
                pacman.moveRight();
                break;
            case 40:
                pacman.moveDown();
                break;
            case 37:
                pacman.moveLeft();
                break;
            case 80:
                stop();
                break;
            case 78:
                nextGame();
                break;
        }
    }

    private class TAdapter extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            keypress(e);
        }
    }
}
