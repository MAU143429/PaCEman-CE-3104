package Socket;
import Game.ViewController;

/**
 * Classify action class
 * Esta clase es la encargada de interpretar los mensajes que envia el servidor
 * @author Mauricio C.Yendry B. Gabriel V.
 */
public class Classify_Action {


    /**
     * Variables utilizadas para almacenar los valores enviados por el socket
     */

    public static char action,fruit;
    public static int ROW,COL,value,speed;



    /**
     * Método Action_recv
     * Este metodo recibe el string del mensaje que llega por el socket y procede
     * a separarlo y clasificarlo para detectar la accion que le solicita el
     * administrador y poder ejecutarla.
     * @param new_sms Mensaje recibido por el socket
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public static void Action_recv(String new_sms){
        action = new_sms.charAt(0); // Palabra clave de la accion a ejecutar
        if (action == 'C'){
            ViewController.getInstance().setClientType(new_sms.charAt(1)); // Nos indica el numero de cliente que somos en el server
        }
        if (action == 'V'){
            speed = Integer.parseInt(new_sms.substring(new_sms.indexOf(',')+1));
            System.out.println(speed);
            ViewController.getInstance().changeSpeed(speed); // Ejecuta el cambio de velocidad del juego

        }else if (action == 'F' || action == 'M' || action == 'G'){
            ROW = Integer.parseInt(new_sms.substring(new_sms.indexOf(',')+1, new_sms.lastIndexOf(',')));
            COL = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',')+1));

            if (action == 'F'){
                fruit = new_sms.charAt(1);
                value = Integer.parseInt(new_sms.substring(2 , new_sms.indexOf(',')));
                ViewController.getInstance().addFruit(fruit,ROW,COL,value); // Agrega una nueva fruta al juego
            }else if (action == 'M'){
                ViewController.getInstance().addPill(ROW,COL); // Agrega una pildora en el juego
            }else if (action == 'G') {
                ViewController.getInstance().addGhost(ROW,COL); // Agrega un nuevo fantasma al juego
            }
        }else{
            System.out.println(new_sms);
        }

    }


}
