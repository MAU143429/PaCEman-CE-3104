package Game;
import Menu.MainMenu;
import Socket.Classify_Action;

/**
 * Metodo main principal.
 * @author Mauricio C. Yendry B. Gabriel V.
 */

public class Main {
    public static void main(String args[]) {
        MainMenu.getInstance();
        Classify_Action.Action_recv("FB,34,23");

    }
}