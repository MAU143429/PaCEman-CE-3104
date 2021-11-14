package Objects;

import java.awt.*;
/**
 * Write a description of class Box here.
 *
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class Box

{
    // instance variables - replace the example below with your own
    private int x,y;
    private boolean crossing;
    /**
     * Constructor for objects of class Casilla
     */
    public Box()
    {
        // initialise instance variables
    }

    public Box(int x, int y)
    {
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


    public boolean verifyRoute()
    {
        // put your code here
        return crossing;
    }

    public Rectangle createRectangle()
    {
        return new Rectangle(getX()*60,getY()*60,60,60);
    }
}