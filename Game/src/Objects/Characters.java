package Objects;

import Game.ViewController;

import javax.swing.ImageIcon;
import java.awt.*;
/**
 * Abstract class Personajes - Clase con las variables y métodos comunes a los cuatro personajes
 * Clase abstracta Personajes, de ella heredan los personajes que actuan en el juego
 * Pacman y los tres fantasmas, Blinky, Pinky y Clyde.
 * @author Mauricio C Yendry B Gabriel Vargas
 *
 */
public abstract class Characters
{
    // Variables de instancia
    protected int x,y,dx,dy;    //Las variables de las coordenadas x e y. Desplazamiento x e y.
    private Image image;       //La variable imagen donde irá la imagen de la figura
    private ImageIcon imageIcon;
    protected boolean up, down, right, left, intersection; //Las variables que nos indican donde tenemos camino libre, sin muro.
    /**
     * Constructor vacío
     */
    public Characters () {};

    /**
     * Constructor de los personajes, al que se le pasa la ruta de la imagen, y las coordenadas.
     * @param
     */
    public Characters (String path, int x, int y)
    {
        this.x = x;
        this.y = y;
        System.out.println("SET DE X Y Y" + this.x + this.y);
        imageIcon = new ImageIcon(this.getClass().getResource(path));
        image = imageIcon.getImage();
    }

    /**
     * Método que devuelve la posición X del personaje.
     * @return Coordenada "x"
     */
    public int getX()
    {
        return x;
    }

    /**
     * Método que devuelve la posición Y del personaje.
     * @return Coordenada "y"
     */
    public int getY()
    {
        return y;
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Método que devuelve un objeto Image, con la imagen del personaje.
     * Este método se ejecuta por el método paint redefinido en la clase VistaControlador.
     * Se pinta en el frame la imagen del personaje.
     * @return imagen del personaje
     */
    public Image getImage()
    {
        return image;
    }

    /**
     * Método move, para mover los personajes por el JFrame
     *
     */
    public void move()
    {
        x +=dx;
        y +=dy;
        intersection =false;
    }

    /**
     * Método para parar los personajes cuando hay una colisión.
     */
    public void stop()
    {
        x -= dx;
        y -= dy;
    }

    /**
     * Método que cambia de dirección al personaje.
     */
    public void back()
    {
        stop();
        dx = dx*(-1);
        dy = dy*(-1);
    }

    /**
     * Método para mover al personaje hacia arriba, indicando la velocidad en el eje Y.
     */
    public void up()
    {
        dy=-ViewController.getInstance().getGameSpeed();
        dx=0;
    }

    /**
     * Método para mover al personaje hacia abajo, indicando la velocidad en el eje Y.
     */
    public void down()
    {
        dy=ViewController.getInstance().getGameSpeed();
        dx=0;
    }

    /**
     * Método para mover al personaje hacia la derecha, indicando la velocidad en el eje X.
     */
    public void right()
    {
        dx=ViewController.getInstance().getGameSpeed();
        dy=0;
    }

    /**
     * Método para mover al personaje hacia la izquierda, indicando la velocidad en el eje X.
     */
    public void left()
    {
        dx=-ViewController.getInstance().getGameSpeed();
        dy=0;
    }

    /**
     * Método para actualizar la imagen del personaje cuando hay algún evento.
     * Este método recibe un String con la dirección de la nueva imagen.
     */
    public void updateImage(String path)
    {
        imageIcon = new ImageIcon(this.getClass().getResource(path));
        image = imageIcon.getImage();
    }

    /**
     * Método para consultar la casilla del eje X donde se encuentra el personaje
     * @return int
     */
    public int getBoxX()
    {
        int x=this.x/60;
        return x;
    }

    /**
     * Método para consultar la casilla del eje Y donde se encuentra el personaje
     * @return int
     */
    public int getBoxY()
    {
        int y=this.y/60;
        return y;
    }


    /**
     * Crea un rectangulo en la posición que ocupa el personaje, usado para la lógica de detectar colisiones.
     * El método devuevle un objeto rectángulo.
     */
    public Rectangle createRectangle()
    {
        return new Rectangle(this.x,this.y,60,60);
    }

    public void intersection(){
        intersection = true;
    }

    public void availableDirections(boolean new_up, boolean new_down, boolean new_right, boolean new_left)
    {
        this.up = new_up;
        this.down = new_down;
        this.right = new_right;
        this.left = new_left;
    }
}
