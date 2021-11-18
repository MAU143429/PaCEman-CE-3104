package Menu;

import Game.Game;

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
    private JButton playBtn, observerBtn;
    private JFrame initFrame;
    private JPanel initPanel;
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

        initFrame = new JFrame("PaCEman");
        initFrame.setBounds(0, 0, 920, 900);
        initFrame.setTitle("PaCEman");
        initFrame.setLocationRelativeTo(null);
        initFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initPanel = new JPanel();
        initPanel.setLayout(null);
        initPanel.setSize(920, 900);
        initFrame.add(initPanel);

        ///////////////////////////Creación de los botones///////////////////////////////

        playBtn = new JButton();
        observerBtn = new JButton();

        playBtn.setBounds(162, 350, 238, 250);
        ImageIcon play_url = new ImageIcon(getClass().getResource("/Resources/play_btn.png"));
        playBtn.setIcon(play_url);
        playBtn.addActionListener(this);
        initPanel.add(playBtn);

        observerBtn.setBounds(512, 350, 238, 250);
        ImageIcon btnurl2 = new ImageIcon(getClass().getResource("/Resources/show_btn.png"));
        observerBtn.setIcon(btnurl2);
        observerBtn.addActionListener(this);
        initPanel.add(observerBtn);

        ///////////////////////////Creación del Background///////////////////////////////

        Background = new JLabel();
        Background.setBounds(0, 0, 1280, 900);
        ImageIcon bgurl = new ImageIcon(getClass().getResource("/Resources/init.png"));
        Background.setIcon(bgurl);
        validate();
        initPanel.add(Background);

        initFrame.setVisible(true);

    }

    /**
     * actionPerformed
     * Este metodo abstracto del actionlistener permite darle las funcionalidades a los botones
     * @param e el evento ocurrido
     * @author Mauricio C. Yendry B. Gabriel V.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == playBtn) {
            new Game();
            initFrame.setVisible(false);

        }
        if (e.getSource() == observerBtn) {
            ObserverMenu newObserver = ObserverMenu.getInstance();
            initFrame.setVisible(false);
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
