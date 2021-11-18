package Objects;

import Game.ViewController;

import javax.swing.ImageIcon;
import java.awt.*;
/**
 * Abstract class Personajes - Clase con las variables y métodos comunes a los cuatro personajes
 * Clase abstracta Personajes, de ella heredan los personajes que actuan en el juego
 * Pacman y los cuatro fantasmas, Blinky, Pinky, Inky y Clyde.
 * @author Mauricio C Yendry B Gabriel Vargas
 *
 */
public abstract class Characters
{
    protected int x,y,dx,dy;    //Las variables de las coordenadas x e y. Desplazamiento x e y.
    private Image image;       //La variable imagen donde irá la imagen de la figura
    private ImageIcon imageIcon;
    protected boolean up, down, right, left, intersection; //Las variables que nos indican donde tenemos camino libre, sin muro.

    /**
     * Constructor de la clase
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Characters () {};

    /**
     * Contructor de los caracteres
     * Este metodo permite crear un nuevo caracter con la imagen y su posicion
     * @param x el valor en x en pixeles de la ubicacion
     * @param y el valor en y en pixeles de la ubicacion
     * @param path ruta de la imagen
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Characters (String path, int x, int y)
    {
        this.x = x;
        this.y = y;
        imageIcon = new ImageIcon(this.getClass().getResource(path));
        image = imageIcon.getImage();
    }

    /**
     * Metodo getX
     * Este metodo devuelve el valor en X del personaje
     * @return el valor X del personaje
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int getX()
    {
        return x;
    }

    /**
     * Metodo getY
     * Este metodo devuelve el valor en Y del personaje
     * @return el valor en Y del personaje
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int getY()
    {
        return y;
    }

    /**
     * Metodo setX
     * Este metodo permite cambiar el valor de x de un personaje
     * @param x el valor en x del personaje en pixeles
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Metodo setY
     * Este metodo permite cambiar el valor de y de un personaje
     * @param y el valor en y del personaje en pixeles
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Metodo getImage
     * Este metodo devuelve un objeto imagen con la imagen del objeto
     * @return objeto imagen
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Metodo move
     * Este metodo permite mover a los personajes por el JFrame
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void move()
    {
        x +=dx;
        y +=dy;
        intersection =false;
    }

    /**
     * Metodo stop
     * Este metodo permite detener a los personajes en caso de que se crucen
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void stop()
    {
        x -= dx;
        y -= dy;
    }

    /**
     * Metodo back
     * Este metodo permite cambiar la dirrecion del persona hacia atras
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void back()
    {
        stop();
        dx = dx*(-1);
        dy = dy*(-1);
    }

    /**
     * Metodo up
     * Este metodo permite mover al caracter hacia arriba
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void up()
    {
        dy=-ViewController.getInstance().getGameSpeed();
        dx=0;
    }

    /**
     * Metodo down
     * Este metodo permite mover al caracter hacia abajo
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void down()
    {
        dy=ViewController.getInstance().getGameSpeed();
        dx=0;
    }

    /**
     * Metodo right
     * Este metodo permite mover al caracter hacia la derecha
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void right()
    {
        dx=ViewController.getInstance().getGameSpeed();
        dy=0;
    }

    /**
     * Metodo left
     * Este metodo permite mover al caracter hacia la izquierda
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void left()
    {
        dx=-ViewController.getInstance().getGameSpeed();
        dy=0;
    }

    /**
     * Metodo updateImage
     * Este metodo permite actualizar la imagen del jugador durante o despues de un evento.
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void updateImage(String path)
    {
        imageIcon = new ImageIcon(this.getClass().getResource(path));
        image = imageIcon.getImage();
    }

    /**
     * Metodo getBoxX
     * Este metodo permite obtener el valor de la x de la matriz de un personaje
     * @return el valor x de la posicion de la matriz
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int getBoxX()
    {
        int x=this.x/60;
        return x;
    }

    /**
     * Metodo getBoxY
     * Este metodo permite obtener el valor de la y de la matriz de un personaje
     * @return el valor y de la posicion de la matriz
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int getBoxY()
    {
        int y=this.y/60;
        return y;
    }


    /**
     * Metodo createRectangle
     * Este metodo permite crear un rectangulo en la posicion del personaje
     * esto sirve para detectar las colisiones
     * @return el rectangulo creado
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Rectangle createRectangle()
    {
        return new Rectangle(this.x,this.y,60,60);
    }

    /**
     * Metodo intersection
     * Este metodo cambia el estado de interseccion a verdadero, esto para
     * saber que el personaje se encuentra en una interseccion
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void intersection(){
        intersection = true;
    }

    /**
     * Metodo availableDirections
     * Este metodo permite hacer update de los booleanos que nos determinan la disponibilidad de
     * movimiento en un sentido en concreto
     * @param newUp boolean de disponibilidad hacia arriba
     * @param newDown boolean de disponibilidad hacia abajo
     * @param newLeft boolean de disponibilidad hacia la izquierda
     * @param newRight boolean de disponibilidad hacia la derecha
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void availableDirections(boolean newUp, boolean newDown, boolean newRight, boolean newLeft)
    {
        this.up = newUp;
        this.down = newDown;
        this.right = newRight;
        this.left = newLeft;
    }

}
