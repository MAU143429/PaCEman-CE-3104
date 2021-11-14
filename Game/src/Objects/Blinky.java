package Objects;

/**
 * Write a description of class Ghost Blinky here.
 * @author Mauricio C Yendry B Gabriel Vargas
 *
 */
public class Blinky extends Ghost {
        /**
         * Constructor for objects of class Ghost Blinky
         */
        public Blinky()
        {
            super("/Resources/blinky.gif", 240, 180);
        }

        public void finalPanic()
        {
            super.finalPanic();
            updateImage("/Resources/blinky.gif");
        }

        public void death(){
            super.death();
            x = 300;
            y = 300;
            dx=dy=0;
        }

        public void artificialIntelligence(int pacmanX, int pacmanY){
            super.artificialIntelligence(pacmanX, pacmanY);
            int movement = random.nextInt(3);
            if(!death && intersection){
                if(pacmanY - getY() < 0){ //Si esta arriba de Blinky
                    if(!up || panic){ //Si la casilla de arriba de Blinky es un muro
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
                    if(up){ //Si la casilla de arriba de Blynki no es un muro
                        if(!panic){up();}
                        if(panic && down){down();}
                    }
                }

                if(pacmanY - getY() > 0){ //Si Comecocos se encuentra debajo de Pinky
                    if(!down || panic){ //Si la casilla de abajo de Blinky es un muro
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
                    if(down){ //Si la casilla de abajo de Blinky no es un muro
                        if(!panic){down();}
                        if(panic && up){up();}
                    }
                }

                if(!death && intersection){
                    if(pacmanX - getX() < 0){ //Si Comecocos se encuentra a la izquierda de Blinky
                        if(!left || panic){ //Si la casilla de la izquierda de Blinky es un muro
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
                        if(left){ //Si la casilla de la izquierda de Blinky no es un muro
                            if(!panic){left();}
                            if(panic && right){right();}
                        }
                    }

                    if(pacmanX -  getX() > 0){  //Si Comecocos se encuentra a la derecha de Blinky
                        if(!right || panic){ //Si la casilla de la derecya de Blinky es un muro
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
                        if(right){ //Si la casilla de la derecha de Blinky no es un muro
                            if(!panic){right();}
                            if(panic && left){left();}
                        }
                    }
                }
            }
        }
}

