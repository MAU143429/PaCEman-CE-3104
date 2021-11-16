package Objects;

import java.awt.*;
/**
 * Intersection class
 * Esta clase nos permitira saber si la ubicacion es un cruce o un codo
 * @author Mauricio C.Yendry B. Gabriel V.
 */
public class Intersection
{

    private int x,y;
    /**
     * Constructor de la clase
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Intersection() {}
    /**
     * Metodo Intersection
     * Este metodo permite obtener el valor de la casilla en una posicion especifica de la matriz
     * @param x el valor en x de la interseccion
     * @param y el valor en y de la interseccion
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Intersection(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    /**
     * Metodo createRectangle
     * Este metodo permite crear un rectangulo de las dimensiones de la casilla
     * @return el rectangulo creado
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Rectangle createRectangle()
    {
        return new Rectangle(x*60,y*60,60,60);
    }
}
