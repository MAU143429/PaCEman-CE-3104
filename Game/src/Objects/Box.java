package Objects;

import java.awt.*;
/**
 * Box Class
 *  Clase que contiene la informacion de cada casilla de la matriz.
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public class Box
{
    private int x,y;
    private boolean crossing;
    /**
     * Constructor Box
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Box() {};

    /**
     * Metodo Box
     * Este metodo permite guardar el valor x y y de una casilla
     * @param x el valor en x de la matriz para la casila
     * @param y el valor en y de la matriz para la casilla
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Box(int x, int y) {
        this.x=x;
        this.y=y;
        crossing = true;
    }

    /**
     * Metodo getX
     * Este metodo permite obtener el valor X de una casilla
     * @return el valor X de la casilla
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Metodo getY
     * Este metodo permite obtener el valor Y de una casilla
     * @return el valor Y de la casilla
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int getY()
    {
        return y;
    }

}