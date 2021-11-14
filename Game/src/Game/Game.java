package Game;

import javax.swing.JFrame;
/**
 * Clase principal que general el juego.
 * Se crea un JFrame, se le asignan ciertos valores y se añade un objeto VistaControlador
 * @author Aitor Alcorta
 * @version 1.0.15052014
 */
public class Game extends JFrame
{
    private int high, breadth; //Variables para las dimensiones del JFrame
    private ViewController game;
    public Game()
    {
        high = 1200;
        breadth = 1440;
        setSize(breadth, high);
        setResizable(true);
        setFocusable(false);
        game = new ViewController();
        add(game);
        //add(new VistaControlador());//Añadimos la clase VistaControlador, que extiende JPanel, al JFrame.
        setVisible(true);//Hacemos visible el JFrame.
    }

}
