package Objects;

import java.awt.*;
/**
 *  Clase que contiene la informacion de cada casilla de la matriz.
 *
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class Box

{
    private int x,y;
    private boolean crossing;
    /**
     * Constructor de la clase Box
     */
    public Box() {};

    public Box(int x, int y) {
        this.x=x;
        this.y=y;
        crossing = true;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }


    public boolean verifyRoute(){
        return crossing;
    }

    public Rectangle createRectangle() {
        return new Rectangle(getX()*60,getY()*60,60,60);
    }
}