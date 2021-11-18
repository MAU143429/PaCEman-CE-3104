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

    public static char action,fruit,numberOfClient;
    public static int row,col,value,speed,client;


    /**
     * Método Action_recv
     * Este metodo recibe el string del mensaje que llega por el socket y procede
     * a separarlo y clasificarlo para detectar la accion que le solicita el
     * administrador y poder ejecutarla.
     * @param new_sms Mensaje recibido por el socket
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public static void actionRecv(String new_sms){

        client = new_sms.charAt(0);
        System.out.println("SOY CLIENT: "+ String.valueOf(client) +" \n");
        if (client == 'C'){
            numberOfClient = new_sms.charAt(1);
            ViewController.getInstance().setClientType(Integer.parseInt(String.valueOf(numberOfClient))); // Nos indica el numero de cliente que somos en el server
            System.out.println("Soy clientType: " + ViewController.getInstance().getClientType());
        }
        System.out.println("No voy a entrar pendejo: "+ ViewController.getInstance().getClientType() +" \n");
        if (client == '1' && (String.valueOf(ViewController.getInstance().getClientType())).equals("1")){
            action = new_sms.charAt(1); // Palabra clave de la accion a ejecutar
            System.out.println("ACTION: " + action + "\n");
            if (action == 'V') {
                speed = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1));
                System.out.println(speed);
                ViewController.getInstance().changeSpeed(speed); // Ejecuta el cambio de velocidad del juego

            } else if (action == 'F' || action == 'M' || action == 'G') {
                row = Integer.parseInt(new_sms.substring(new_sms.indexOf(',') + 1, new_sms.lastIndexOf(',')));
                col = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',') + 1));

                if (action == 'F') {
                    fruit = new_sms.charAt(2);
                    value = Integer.parseInt(new_sms.substring(3, new_sms.indexOf(','))); //1FC1000,
                    ViewController.getInstance().addFruit(fruit, row, col, value); // Agrega una nueva fruta al juego
                } else if (action == 'M') {
                    ViewController.getInstance().addPill(row, col); // Agrega una pildora en el juego
                } else if (action == 'G') {
                    ViewController.getInstance().addGhost(row, col); // Agrega un nuevo fantasma al juego
                }
            } else {
                System.out.println(new_sms);
            }
        }

    }

}
