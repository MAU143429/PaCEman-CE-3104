package Menu;

import Game.Game;
import Socket.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main Menu class
 *Esta clase es la que ejecuta la ventana principal
 *@author Mauricio C.Yendry B. Gabriel V.
 */
public class MainMenu extends JFrame implements ActionListener {

    /**
     * Botones y etiquetas utilizados en la interfaz
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    private JButton play_btn, observer_btn;
    private JFrame init_frame;
    private JPanel init_panel;
    private JLabel Background;


    /**
     * Instancia empleada en el Singleton del MainMenu
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    private static MainMenu instance = null;

    /**
     * Metodo MainMenu
     * Este constructor crea un frame, un panel y aloja objetos en ellos
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public MainMenu() {

        ////////////////////////////Creación del Frame y el Panel///////////////////////

        init_frame = new JFrame("PaCEman");
        init_frame.setBounds(0, 0, 920, 900);
        init_frame.setTitle("PaCEman");
        init_frame.setLocationRelativeTo(null);
        init_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init_panel = new JPanel();
        init_panel.setLayout(null);
        init_panel.setSize(920, 900);
        init_frame.add(init_panel);

        ///////////////////////////Creación de los botones///////////////////////////////

        play_btn = new JButton();
        observer_btn = new JButton();

        play_btn.setBounds(162, 350, 238, 250);
        ImageIcon play_url = new ImageIcon(getClass().getResource("/Resources/play_btn.png"));
        play_btn.setIcon(play_url);
        play_btn.addActionListener(this);
        init_panel.add(play_btn);

        observer_btn.setBounds(512, 350, 238, 250);
        ImageIcon btnurl2 = new ImageIcon(getClass().getResource("/Resources/show_btn.png"));
        observer_btn.setIcon(btnurl2);
        observer_btn.addActionListener(this);
        init_panel.add(observer_btn);

        ///////////////////////////Creación del Background///////////////////////////////

        Background = new JLabel();
        Background.setBounds(0, 0, 1280, 900);
        ImageIcon bgurl = new ImageIcon(getClass().getResource("/Resources/init.png"));
        Background.setIcon(bgurl);
        validate();
        init_panel.add(Background);

        init_frame.setVisible(true);

    }

    /**
     * actionPerformed
     * Este metodo abstracto del actionlistener permite darle las funcionalidades a los botones
     * @param e el evento ocurrido
     * @author Mauricio C. Yendry B. Gabriel V.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == play_btn) {

            new Game();
            init_frame.setVisible(false);

        }
        if (e.getSource() == observer_btn) {
            ObserverMenu newObserver = ObserverMenu.getInstance();
            init_frame.setVisible(false);
            instance = null;
        }

    }
    /**
     * Metodo del singleton
     * Crea el singleton en el MainMenu
     * @return la instancia del MainMenu
     * @author Mauricio C. Yendry B. Gabriel V.
     */
    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

}
