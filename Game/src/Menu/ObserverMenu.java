package Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ObserverMenu extends JFrame implements ActionListener {

    public JFrame mFrame;
    public JPanel mPanel;
    private JLabel O_Background;
    private JButton player1_btn, player2_btn;


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

        mFrame = new JFrame("PaCEman");
        mFrame.setBounds(0, 0, 1280, 900);
        mFrame.setTitle("PaCEman");
        mFrame.setLocationRelativeTo(null);
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //############################Panel###############################

        mPanel = new JPanel();
        mPanel.setLayout(null);
        mPanel.setSize(1280, 900);
        mFrame.add(mPanel);

        //############################Botones###############################

        player1_btn = new JButton();
        player2_btn = new JButton();

        player1_btn.setBounds(874, 494, 80, 80);
        // AGREGAR IMAGEN
        //ImageIcon btnurl1 = new ImageIcon(getClass().getResource("/Inicio/2P.png"));
        //play_btn.setIcon(btnurl1);
        player1_btn.addActionListener(this);
        mPanel.add(player1_btn);

        player2_btn.setBounds(874, 632, 80, 80);
        // AGREGAR IMAGEN
        //ImageIcon btnurl2 = new ImageIcon(getClass().getResource("/Inicio/3P.png"));
        //observer_btn.setIcon(btnurl2);
        player2_btn.addActionListener(this);
        mPanel.add(player2_btn);

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
        if (e.getSource() == player1_btn) {
            System.out.println("ABRIENDO EL MODO ESPECTADOR 1");
        }
        if (e.getSource() == player2_btn) {
            System.out.println("ABRIENDO EL MODO ESPECTADOR 2");
        }

    }
}
