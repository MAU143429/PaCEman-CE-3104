package Objects;


/**
 * Inky class
 * Esta clase hereda de Ghost y crea las caracteristicas del fantasma Inky
 * @author Mauricio C.Yendry B. Gabriel V.
 */
public class Inky extends Ghost
{
    /**
     * Constructor de Inky
     * @param x el valor en x en pixeles de la ubicacion de Inky
     * @param y el valor en y en pixeles de la ubicacion de Inky
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Inky(int x, int y)
    {
        super("/Resources/inky.gif", x, y);
    }

    /**
     * Metodo death
     * Este metodo mata al fantasma
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void death(){
        super.death();
        x = 420;
        y = 300;
        dx=dy=0;
    }


    /**
     * Metodo finalPanic
     * Este metodo permite terminar el estado de panico y refresca la imagen de Inky
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void finalPanic()
    {
        super.finalPanic();
        updateImage("/Resources/inky.gif");
    }



    /**
     * Metodo artificialintelligence
     * Este metodo se sobreescribe para darle a Inky las caracterisiticas de su movimiento.
     * @param pacmanX el valor en x de pacman
     * @param pacmanY el valor en y de pacman
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void artificialIntelligence(int pacmanX, int pacmanY){
        super.artificialIntelligence(pacmanX, pacmanY);
        int movement = random.nextInt(3);
        if(!death && intersection){
            if(pacmanX - getX() < 0){ //Si Comecocos se encuentra a la izquierda de Pinky
                if(!left || panic){ //Si la casilla de la izquierda de Pinky es un muro
                    switch (movement){
                        case 0:
                            up();
                            break;
                        case 1:
                            down();
                            break;
                        case 2:
                            up();
                            break;
                        case 3:
                            down();
                            break;
                    }
                }
                if(left){ //Si la casilla de la izquierda de Pinky no es un muro
                    if(!panic){left();}
                    if(panic && right){right();}
                }
            }

            if(pacmanX -  getX() > 0){  //Si Comecocos se encuentra a la derecha de Pinky
                if(!right || panic){ //Si la casilla de la derecya de Pinky no es un muro
                    switch (movement){
                        case 0:
                            up();
                            break;
                        case 1:
                            down();
                            break;
                        case 2:
                            up();
                            break;
                        case 3:
                            down();
                            break;
                    }
                }
                if(right){ //Si la casilla de la derecha de Pinky no es un muro
                    if(!panic){right();}
                    if(panic && left){left();}
                }
            }

            if(pacmanY - getY() < 0){ //Si Comecocos se encuentra arriba de Pinky
                if(!up || panic){ //Si la casilla de arriba de Pinky es un muro
                    switch (movement){
                        case 0:
                            right();
                            break;
                        case 1:
                            left();
                            break;
                        case 2:
                            right();
                            break;
                        case 3:
                            left();
                            break;
                    }
                }
                if(up){ //Si la casilla de arriba de Pinky no es un muro
                    if(!panic){up();}
                    if(panic && down){down();}
                }
            }

            if(pacmanY - getY() > 0){ //Si Comecocos se encuentra debajo de Pinky
                if(!down || panic){ //Si la casilla de abajo de Pinky es un muro
                    switch (movement){
                        case 0:
                            right();
                            break;
                        case 1:
                            left();
                            break;
                        case 2:
                            right();
                            break;
                        case 3:
                            left();
                            break;
                    }
                }
                if(down){ //Si la casilla de abajo de Pinky no es un muro
                    if(!panic){down();}
                    if(panic && up){up();}
                }
            }
        }
    }
}
