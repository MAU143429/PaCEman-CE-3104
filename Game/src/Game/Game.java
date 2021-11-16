package Game;



import Socket.Client;

import javax.swing.JFrame;
/**
 * Clase principal que genera el juego.
 * Se crea un JFrame, se le asignan ciertos valores y se añade un objeto VistaControlador
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class Game extends JFrame
{
    private int high, breadth; //Variables para las dimensiones del JFrame
    private ViewController game;

    public Game()
    {
        high = 900;
        breadth = 1440;
        setSize(breadth, high);
        setResizable(true);
        setFocusable(false);
        game = ViewController.getInstance();
        add(game);


        setVisible(true);//Hacemos visible el JFrame.
    }





}
