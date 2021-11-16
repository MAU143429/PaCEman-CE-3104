package Objects;

/**
 * Clyde class
 * Esta clase hereda de Ghost y crea las caracteristicas del fantasma Blinky
 * @author Mauricio C.Yendry B. Gabriel V.
 */
public class Clyde extends Ghost
{
    /**
     * Constructor de Clyde
     * @param x el valor en x en pixeles de la ubicacion de Clyde
     * @param y el valor en y en pixeles de la ubicacion de Clyde
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Clyde(int x, int y)
    {
        super("/Resources/clyde.gif", x, y);
    }

    /**
     * Metodo finalPanic
     * Este metodo permite terminar el estado de panico y refresca la imagen de Clyde
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void finalPanic()
    {
        super.finalPanic();
        updateImage("/Resources/clyde.gif");
    }

    /**
     * Metodo death
     * Este metodo mata al fantasma
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void death(){
        super.death();
        x = 540;
        y = 300;
        dx=dy=0;
    }

    /**
     * Metodo artificialintelligence
     * Este metodo se sobreescribe para darle a Clyde las caracterisiticas de su movimiento.
     * @param pacmanX el valor en x de pacman
     * @param pacmanY el valor en y de pacman
     * @author Mauricio C.Yendry B. Gabriel V.
     */
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