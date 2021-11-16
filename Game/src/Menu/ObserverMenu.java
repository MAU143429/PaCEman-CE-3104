package Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ObserverMenu extends JFrame implements ActionListener {

    public JFrame mFrame;
    public JPanel mPanel;
    private JLabel O_Background;
    private JButton obs_player1, obs_player2,back_btn;


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

        obs_player1 = new JButton();
        obs_player2 = new JButton();
        back_btn = new JButton();

        obs_player1.setBounds(162, 350, 238, 250);
        ImageIcon btnurl1 = new ImageIcon(getClass().getResource("/Resources/p1_btn.png"));
        obs_player1.setIcon(btnurl1);
        obs_player1.addActionListener(this);
        mPanel.add(obs_player1);

        obs_player2.setBounds(512, 350, 238, 250);
        ImageIcon btnurl2 = new ImageIcon(getClass().getResource("/Resources/p2_btn.png"));
        obs_player2.setIcon(btnurl2);
        obs_player2.addActionListener(this);
        mPanel.add(obs_player2);

        back_btn.setBounds(10, 800, 100, 50);
        ImageIcon btnurl3 = new ImageIcon(getClass().getResource("/Resources/back_btn.png"));
        back_btn.setIcon(btnurl3);
        back_btn.addActionListener(this);
        mPanel.add(back_btn);

        //###############################-Background-#######################################
        O_Background = new JLabel();
        O_Background.setBounds(0, 0, 1280, 900);
        ImageIcon bgurl = new ImageIcon(getClass().getResource("/Resources/observer_menu.png"));
        O_Background.setIcon(bgurl);
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
        if (e.getSource() == back_btn) {
            MainMenu.getInstance();
            mFrame.setVisible(false);
            instance = null;
        }

    }
}
