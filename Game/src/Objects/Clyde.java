package Objects;

/**
 * Write a description of class Ghost Clyde here.
 *
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class Clyde extends Ghost
{
    /**
     * Constructor for objects of class Clyde
     */
    public Clyde()
    {
        super("/Resources/clyde.gif", 480, 180);
    }

    public void finalPanic()
    {
        super.finalPanic();
        updateImage("/Resources/clyde.gif");
    }

    public void death(){
        super.death();
        x = 540;
        y = 300;
        dx=dy=0;
    }

    public void artificialIntelligence(int pacmanX, int pacmanY)
    {
        super.artificialIntelligence(pacmanX, pacmanY);
        int movement = random.nextInt(3);
        if(!death && intersection){
            switch(movement){
                case 0:
                    if(up){
                        up();
                        break;}
                case 1:
                    if(down){
                        down();
                        break;}
                case 2:
                    if(right){
                        right();
                        break;}
                case 3:
                    if(left){
                        left();
                        break;
                    }
            }
        }
    }

}