package Socket;
import Game.Fruit;

public class Classify_Action {

    public static char action,fruit;
    public static int ROW,COL,value,speed;



    public static void Action_recv(String new_sms){
        action = new_sms.charAt(0);
        if (action == 'V'){
            speed = Integer.parseInt(new_sms.substring(new_sms.indexOf(',')+1));
            System.out.println(speed);
            //Game.getInstance().changeVelocity(ROW,COL,speed);
        }else{
            ROW = Integer.parseInt(new_sms.substring(new_sms.indexOf(',')+1, new_sms.lastIndexOf(',')));
            COL = Integer.parseInt(new_sms.substring(new_sms.lastIndexOf(',')+1));
            if (action == 'F'){
                fruit = new_sms.charAt(1);
                value = Integer.parseInt(new_sms.substring(2 , new_sms.indexOf(',')));
                Fruit newFruit = new Fruit(fruit,ROW,COL,value);
                //Game.getInstance().getFruits().add(newFruit);
            }else if (action == 'M'){
                //Game.getInstance().addPill(ROW,COL);
            }else if (action == 'G') {
                //Game.getInstance().addGhost(ROW, COL);
            }
        }
    }


}
