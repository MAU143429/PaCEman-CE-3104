package Observer;

import Socket.Observer_Action;

import javax.swing.*;


/**
 * Clase principal que genera el juego.
 * Se crea un JFrame, se le asignan ciertos valores y se añade un objeto ViewController
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class ObserverGame extends JFrame
{
    private int high, breadth; //Variables para las dimensiones del JFrame
    private ObserverController game;

    /**
     * Constructor Game
     * Este metodo crea la ventana y llama al juego a traves del ViewController
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public ObserverGame(int oPlayer)
    {
        high = 900;
        breadth = 915;
        setSize(breadth, high);
        setTitle("PaCEman");
        setResizable(true);
        setFocusable(false);
        setLocationRelativeTo(null);
        game = ObserverController.getInstance();
        add(game);
        ObserverController.getInstance().setObserver(oPlayer);

        setVisible(true);//Hacemos visible el JFrame.
    }





}
