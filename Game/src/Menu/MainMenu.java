package Menu;

import Game.Game;
import Socket.Client;
import Game.Pacman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * Main
 *Esta clase es la que ejecuta la ventana principal
 *@author Mauricio C.Yendry B. Gabriel V.
 */

public class MainMenu extends JFrame implements ActionListener {

    private JButton play_btn, observer_btn;
    private JFrame init_frame;
    private JPanel init_panel;
    private JLabel Background;

    private static MainMenu instance = null;

    /**
     * Main
     * Este constructor crea un frame, un panel y aloja objetos en ellos
     *
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public MainMenu() {
        init_frame = new JFrame("PaCEman");
        init_frame.setBounds(0, 0, 1294, 937);
        init_frame.setTitle("PaCEman");
        init_frame.setLocationRelativeTo(null);
        init_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init_panel = new JPanel();
        init_panel.setLayout(null);
        init_panel.setSize(1294, 937);
        init_frame.add(init_panel);

        play_btn = new JButton();
        observer_btn = new JButton();

        play_btn.setBounds(320, 350, 250, 250);
        play_btn.setText("PLAY!");
        // AGREGAR IMAGEN
        //ImageIcon play_url = new ImageIcon(getClass().getResource("/Resources/bg.jpg"));
        //play_btn.setIcon(btnurl1);
        play_btn.addActionListener(this);
        init_panel.add(play_btn);

        observer_btn.setBounds(700, 350, 250, 250);
        observer_btn.setText("SHOW A GAME!");
        // AGREGAR IMAGEN
        //ImageIcon btnurl2 = new ImageIcon(getClass().getResource("/Inicio/3P.png"));
        //observer_btn.setIcon(btnurl2);
        observer_btn.addActionListener(this);
        init_panel.add(observer_btn);

        Background = new JLabel();
        Background.setBounds(0, 0, 1280, 900);
        ImageIcon bgurl = new ImageIcon(getClass().getResource("/Resources/bg.jpg"));
        Background.setIcon(bgurl);
        validate();
        init_panel.add(Background);

        init_frame.setVisible(true);

    }

    /**
     * actionPerformed
     * Este metodo abstracto del actionlistener permite darle las funcionalidades a los botones
     *
     * @author Mauricio C. Yendry B. Gabriel V.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == play_btn) {

            Client client = new Client("127.0.0.1", 8888);
            //Pacman.startGame(client);
            Pacman.startGame();
            init_frame.setVisible(false);


        }
        if (e.getSource() == observer_btn) {
            ObserverMenu newObserver = ObserverMenu.getInstance();
            init_frame.setVisible(false);
        }

    }
    /**
     * Metodo del singleton
     * Crea el singleton en el main
     *
     * @author Mauricio C. Yendry B. Gabriel V.
     */
    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

}
