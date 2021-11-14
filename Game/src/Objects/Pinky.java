package Objects;

/**
 * Write a description of class Ghost Pinky here.
 *
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class Pinky extends Ghost
{
    /**
     * Constructor for objects of class Ghost Pinky
     */
    public Pinky()
    {
        super("/Resources/pinky.gif", 780, 60);
    }

    public void death(){
        super.death();
        x = 420;
        y = 300;
        dx=dy=0;
    }

    public void finalPanic()
    {
        super.finalPanic();
        updateImage("/Resources/pinky.gif");
    }

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
