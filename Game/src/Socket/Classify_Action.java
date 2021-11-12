package Socket;

public class Classify_Action {

    public static char action,fruit;
    public static String ROW,COL;

    public static void Action_recv(String new_sms){ // FB,34,23
        action = new_sms.charAt(0); // F
        ROW = new_sms.substring(new_sms.indexOf(','), new_sms.lastIndexOf(','));
        COL = new_sms.substring(new_sms.lastIndexOf(','));
        System.out.println(action);
        System.out.println(ROW);
        System.out.println(COL);


        if (action == 'F'){
            fruit = new_sms.charAt(1);


        }else if (action == 'M'){


        }else if (action == 'G'){


        }
    }


}
