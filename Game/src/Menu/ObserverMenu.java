package Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ObserverMenu extends JFrame implements ActionListener {

    public JFrame mFrame;
    public JPanel mPanel;
    private JLabel O_Background;
    private JButton obs_player1, obs_player2;


    /**
     * Sinleton para MainMenu
     */

    private static ObserverMenu instance = null;

    /**
     * MainMenu
     * Este constructor crea un frame, un panel y aloja objetos en ellos
     *
     * @authors Mauricio C. Yendry B. Gabriel V.
     */

    public ObserverMenu() {


        //############################Frame###############################

        mFrame = new JFrame("PaCEman Observer Menu");
        mFrame.setBounds(0, 0, 1294, 937);
        mFrame.setTitle("PaCEman Observer Menu");
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //############################Panel###############################

        mPanel = new JPanel();
        mPanel.setLayout(null);
        mPanel.setSize(1294, 937);
        mFrame.add(mPanel);

        //############################Botones###############################

        obs_player1 = new JButton();
        obs_player2 = new JButton();

        obs_player1.setBounds(320, 350, 250, 250);
        obs_player1.setText("SHOW PLAYER 1 GAME");
        // AGREGAR IMAGEN
        //ImageIcon btnurl1 = new ImageIcon(getClass().getResource("/Inicio/2P.png"));
        //play_btn.setIcon(btnurl1);
        obs_player1.addActionListener(this);
        mPanel.add(obs_player1);

        obs_player2.setBounds(700, 350, 250, 250);
        obs_player2.setText("SHOW PLAYER 2 GAME");
        // AGREGAR IMAGEN
        //ImageIcon btnurl2 = new ImageIcon(getClass().getResource("/Inicio/3P.png"));
        //observer_btn.setIcon(btnurl2);
        obs_player2.addActionListener(this);
        mPanel.add(obs_player2);

        //###############################-Background-#######################################
        O_Background = new JLabel();
        O_Background.setBounds(0, 0, 1280, 900);
        // agregar imagen
        //ImageIcon bgurl = new ImageIcon(getClass().getResource("/Inicio/Fondo1.png"));
        //Background.setIcon(bgurl);
        validate();
        mPanel.add(O_Background);
        mFrame.setVisible(true);


    }


    //////////////////////////////////////Singleton de Observer Menu//////////////////////////////////////////////////////////////////////////////

    /**
     * getInstance
     * @return instance
     * Método singleton del Jmain
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
        if (e.getSource() == obs_player1) {
            System.out.println("ABRIENDO EL MODO ESPECTADOR 1");
        }
        if (e.getSource() == obs_player2) {
            System.out.println("ABRIENDO EL MODO ESPECTADOR 2");
        }

    }
}
