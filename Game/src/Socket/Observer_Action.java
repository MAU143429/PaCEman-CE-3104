package Socket;

import Game.ViewController;
import Observer.ObserverController;

/**
 * Observer action class
 * Esta clase es la encargada de interpretar los de tipo observador
 * @author Mauricio C.Yendry B. Gabriel V.
 */

public class Observer_Action {

    /**
     * Variables utilizadas para almacenar los valores enviados por el socket
     */

    public static char action,fruit;
    public static int ROW,COL,value,speed;



    /**
     * Método Observer_recv
     * Este metodo recibe el string del mensaje que llega por el socket y procede
     * a separarlo y clasificarlo para detectar la accion que le solicita el
     * administrador y poder ejecutarla.
     * @param new_sms Mensaje recibido por el socket
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public static void Observer_recv(String new_sms){
        action = new_sms.charAt(0); // Palabra clave de la accion a ejecutar

        if (action == 'V'){
            speed = Integer.parseInt(new_sms.substring(new_sms.indexOf(',')+1));
            System.out.println(speed);
            ObserverController.getInstance().changeSpeed(speed); // Ejecuta el cambio de velocidad del juego

        }else if (action == 'F' || action == 'M' || action == 'G'|| action == 'U'){
            ROW = Integer.parseInt(new_sms.substring(new_sms.indexOf(',')+1, new_sms.lastIndexOf(',')));
            COL = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',')+1));

            if (action == 'U'){
                ObserverController.getInstance().pacmanLocation(ROW,COL); // Nos indica la ubicacion de pacman
            }
            if (action == 'F'){
                fruit = new_sms.charAt(1);
                value = Integer.parseInt(new_sms.substring(2 , new_sms.indexOf(',')));
                ObserverController.getInstance().addFruit(fruit,ROW,COL,value); // Agrega una nueva fruta al juego
            }else if (action == 'M'){
                ObserverController.getInstance().addPill(ROW,COL); // Agrega una pildora en el juego
            }else if (action == 'G') {
                ObserverController.getInstance().addGhost(ROW,COL); // Agrega un nuevo fantasma al juego
            }
        }else{
            System.out.println(new_sms);
        }

    }






}
