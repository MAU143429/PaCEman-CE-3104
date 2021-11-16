package Socket;
import Game.Fruit;
import Game.ViewController;

public class Classify_Action {

    public static char action,fruit;
    public static int ROW,COL,value,speed;



    public static void Action_recv(String new_sms){
        action = new_sms.charAt(0);
        if (action == 'C'){
            ViewController.getInstance().setClientType(new_sms.charAt(1));
        }
        if (action == 'V'){
            speed = Integer.parseInt(new_sms.substring(new_sms.indexOf(',')+1));
            System.out.println(speed);
            ViewController.getInstance().changeSpeed(speed);
        }else if (action == 'F' || action == 'M' || action == 'G'){
            ROW = Integer.parseInt(new_sms.substring(new_sms.indexOf(',')+1, new_sms.lastIndexOf(',')));
            COL = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',')+1));
            if (action == 'F'){
                fruit = new_sms.charAt(1);
                value = Integer.parseInt(new_sms.substring(2 , new_sms.indexOf(',')));
                ViewController.getInstance().addFruit(fruit,ROW,COL,value);
            }else if (action == 'M'){
                ViewController.getInstance().addPill(ROW,COL);
            }else if (action == 'G') {
                ViewController.getInstance().addGhost(ROW,COL);
            }
        }else{
            System.out.println(new_sms);
        }

    }


}
