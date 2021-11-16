package Objects;

import java.util.Random;
/**
 * Abstract class Ghost
 * En esta clase se hereda de Characters y se busca adaptar el personaje con los
 * atributos de un fantasma para luego poder crearlos por independiente.
 * @author Mauricio C Yendry B Gabriel Vargas
 */
public abstract class Ghost extends Characters
{
    protected boolean panic, death;
    protected Random random;
    /**
     * Contructor de la clase hereda de Characters
     * @param path recibe la ruta de imagen del ghost
     * @param x  recibe el valor x de la posicion del ghost
     * @param y recibe el valor y de la posicion del ghost
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Ghost (String path, int x, int y)
    {
        super(path,x,y);
    }

    /**
     * Metodo panic
     * Este metodo permite cambiar la imagen del ghost a modo panica(azul)
     * y con esto tambien poner el modo panico True
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void panic()
    {
        super.updateImage("/Resources/panic_ghost.gif");
        panic = true;
    }

    /**
     * Metodo finalPanic
     * Este metodo permite parar el modo panico cambiando el panic boolean a false
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void finalPanic()
    {
        panic = false;
    }

    /**
     * Metodo death
     * Este metodo permite informar sobre la muerte de un ghost poniendo el boolean death en true
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void death()
    {
        death =true;
    }

    /**
     * Metodo isDeath
     * Este metodo  get permite obtener el valor del booleano de muerte
     * esto para verificar si el fantasma ya esta muerto
     * @return true en caso de que este muerto
     * @return false en caso de que aun viva.
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public boolean isDeath() {
        return death;
    }

    /**
     * Metodo exit
     * Este metodo permite a los ghost salir de la casa luego de que fueran comidos por pacman
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void exit(){
        y=180;
        x=x-60;
        death = false;
    }
    /**
     * Metodo artificialintelligence
     * Este metodo utiliza un random para el movimiento del fantasma basado en los movimiento de pacman
     * @param pacmanX el valor en x de la posicion de pacman
     * @param pacmanY el valor en y de la posicion de pacman
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void artificialIntelligence(int pacmanX, int pacmanY){
        random = new Random();
    }
}

