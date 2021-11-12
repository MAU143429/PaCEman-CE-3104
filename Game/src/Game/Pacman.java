package Game;

import Socket.Client;

import javax.swing.JFrame;

public class Pacman extends JFrame{

    public Pacman() {
        add(new Game());
    }



    public static void startGame() {
        Pacman pac = new Pacman();
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(740,790);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);

    }

    /*public class Pacman extends JFrame{

    public Pacman(Client client) {
        add(new Game(client));
    }



    public static void startGame(Client client) {
        Pacman pac = new Pacman(client);
        pac.setVisible(true);
        pac.setTitle("Pacman");
        pac.setSize(380,420);
        pac.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pac.setLocationRelativeTo(null);

    }*/

}