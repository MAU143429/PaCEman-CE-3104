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
        //Classify_Action.Action_recv("FC1000,10,10"); // EJEMPLO DE FRUTA
        //Classify_Action.Action_recv("G,4,10"); // EJEMPLO DE FANTASMA
        //Classify_Action.Action_recv("M,5,7"); // EJEMPLO DE PASTILLA
        //Classify_Action.Action_recv("V,1"); // EJEMPLO DE VELOCIDAD

    }
}