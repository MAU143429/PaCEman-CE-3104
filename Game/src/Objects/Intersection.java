package Objects;

import java.awt.*;
/**
 * Clase Cruces, comprueba si el elemento del laberinto es un cruce o codo.
 * Sirve para indicar a los fantasmas donde realizar un nuevo cálculo de distancia hacia
 * Comecocos.
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class Intersection
{
    // instance variables - replace the example below with your own
    private int x,y;
    /**
     * Constructor for objects of class Cruces
     */
    public Intersection()
    {

    }

    public Intersection(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    public Rectangle createRectangle()
    {
        return new Rectangle(x*60,y*60,60,60);
    }
}
