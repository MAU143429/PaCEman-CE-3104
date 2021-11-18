package Menu;

import Observer.ObserverGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Observer Menu class
 *Esta clase es la que ejecuta la ventana de los observadores
 *@author Mauricio C.Yendry B. Gabriel V.
 */
public class ObserverMenu extends JFrame implements ActionListener {

    /**
     * Botones y etiquetas utilizados en la interfaz
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public JFrame mFrame;
    public JPanel mPanel;
    private JLabel oBackground;
    private JButton obsPlayer1, obsPlayer2, backBtn;


    /**
     * Variable para el singleton de ObserverMenu
     *@author Mauricio C.Yendry B. Gabriel V.
     */
    private static ObserverMenu instance = null;

    /**
     * Observer Menu
     * Este constructor crea un frame, un panel y aloja objetos en ellos
     * @authors Mauricio C. Yendry B. Gabriel V.
     */
    public ObserverMenu() {


        //############################Frame###############################

        mFrame = new JFrame("PaCEman Observer Menu");
        mFrame.setBounds(0, 0, 920, 900);
        mFrame.setTitle("PaCEman Observer Menu");
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //############################Panel###############################

        mPanel = new JPanel();
        mPanel.setLayout(null);
        mPanel.setSize(920, 900);
        mFrame.add(mPanel);

        //############################Botones###############################

        obsPlayer1 = new JButton();
        obsPlayer2 = new JButton();
        backBtn = new JButton();

        obsPlayer1.setBounds(162, 350, 238, 250);
        ImageIcon btnurl1 = new ImageIcon(getClass().getResource("/Resources/p1_btn.png"));
        obsPlayer1.setIcon(btnurl1);
        obsPlayer1.addActionListener(this);
        mPanel.add(obsPlayer1);

        obsPlayer2.setBounds(512, 350, 238, 250);
        ImageIcon btnurl2 = new ImageIcon(getClass().getResource("/Resources/p2_btn.png"));
        obsPlayer2.setIcon(btnurl2);
        obsPlayer2.addActionListener(this);
        mPanel.add(obsPlayer2);

        backBtn.setBounds(10, 800, 100, 50);
        ImageIcon btnurl3 = new ImageIcon(getClass().getResource("/Resources/back_btn.png"));
        backBtn.setIcon(btnurl3);
        backBtn.addActionListener(this);
        mPanel.add(backBtn);

        //###############################-Background-#######################################
        oBackground = new JLabel();
        oBackground.setBounds(0, 0, 1280, 900);
        ImageIcon bgurl = new ImageIcon(getClass().getResource("/Resources/observer_menu.png"));
        oBackground.setIcon(bgurl);
        validate();
        mPanel.add(oBackground);
        mFrame.setVisible(true);


    }


    //////////////////////////////////////Singleton de Observer Menu//////////////////////////////////////////////////////////////////////////////

    /**
     * getInstance
     * @return instance
     * Método singleton del Observer Menu
     * @author Mauricio C.Yendry B. Gabriel V.
     *
     */
    public static ObserverMenu getInstance(){
        if(instance == null){
            instance = new ObserverMenu();
        }
        return instance;
    }
    
    /**
     * actionPerformed
     *Este metodo abstracto del actionlistener permite darle las funcionalidades a los botones
     *@authors Mauricio C.Yendry B. Gabriel V.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == obsPlayer1) {
            new ObserverGame(1);
        }
        if (e.getSource() == obsPlayer2) {
            new ObserverGame(2);
        }
        if (e.getSource() == backBtn) {
            MainMenu.getInstance();
            mFrame.setVisible(false);
            instance = null;
        }

    }
}
