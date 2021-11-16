package Game;

import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Maps class
 * Esta clase es la que se encarga de almacenar la logica del mapa
 * contiene metodos para obtener posiciones y conocer sobre el valor
 * de alguna casilla.
 * @author Mauricio C.Yendry B. Gabriel V.
 */

class Maps
{

    /**
     * Intancias del mapa y imagenes que se utilizan para poder dibujar el mapa.
     *@author Mauricio C.Yendry B. Gabriel V.
     */
    private int [][] level =
                    {{0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,1,1,1,1,1,1,1,1,1,1,1,0},
                    {0,1,0,0,1,0,0,0,0,1,0,1,0},
                    {0,1,0,1,1,1,1,1,1,1,1,1,0},
                    {0,1,1,1,0,0,0,1,0,1,0,1,0},
                    {0,1,0,1,0,3,0,1,0,1,0,1,0},
                    {0,1,1,1,0,3,0,1,0,1,0,1,0},
                    {0,1,0,1,0,3,0,1,0,1,0,3,0},
                    {0,1,1,1,0,3,0,1,0,1,0,1,0},
                    {0,1,0,1,0,3,0,1,0,1,0,1,0},
                    {0,1,1,1,0,0,0,1,0,1,0,1,0},
                    {0,1,0,1,1,1,1,1,1,1,1,1,0},
                    {0,1,0,0,1,0,0,0,0,1,0,1,0},
                    {0,1,1,1,1,1,1,1,1,1,1,1,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0}};

    private Image image;       //La variable imagen donde irá la imagen de la figura, ya sea muro, punto pequeño, punto grando o punto vacío.
    private ImageIcon imageIcon;
    private int dots;


    /**
     * Maps
     * Constructor de la clase que establece el valor inicial de puntos en 0
     *@author Mauricio C.Yendry B. Gabriel V.
     */
    Maps() { dots = 0;}

    /**
     * Metodo getImage
     * Este metodo recibe dos pisciones y devuelve el la imagen correspondiente dependiendo
     * del valor encontrado en esa posicion del mapa
     * @param x posicion en x de la matriz
     * @param y posicion en y de la matriz
     * @return la imagen del sprite a dibujar o null en caso de no encontrarla
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public Image getImage(int x, int y)
    {
        int location = level[x][y];
        if(location == 0)// MURO
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/wall.gif"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 1)// PUNTO
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/Pac-Dot.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 2)// PILDORA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/pill.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 3)// VACIO
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/empty.gif"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 4) // CEREZA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/cherry.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 5)// MELON
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/melon.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 6)// MANZANA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/apple.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 7)// NARANJA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/orange.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 8)// FRESA
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/strawberry.png"));
            image = imageIcon.getImage();
            return image;
        }

        else
        {
            return null;
        }
    }
    /**
     * Metodo getValue
     * Este metodo permite obtener el valor de la casilla en una posicion especifica de la matriz
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @return el valor de la matriz en esa posicion
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int getValue(int x, int y)
    {
        return level[x][y];
    }

    /**
     * Metodo eatDot
     * Este metodo permite cambiar el valor de una casilla cuando pacman ya se comio el punto ubicado en ella
     * el valor de la casilla pasara a ser 3 que significa vacio.
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void eatDot(int x, int y)
    {
        level[x][y] = 3;
    }

    /**
     * Metodo sizeMapX
     * Este metodo permite obtener el largo de la matriz en X
     * @return el largo de la matriz en x
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int sizeMapX()
    {
        return level.length;
    }
    /**
     * Metodo getValue
     * Este metodo permite obtener el largo de la matriz en y
     * @return el largo de la matriz en y
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int sizeMapY()
    {
        return level[0].length;
    }

    /**
     * Metodo verifyBox
     * Este metodo permite verificar si una ubicacion de la matriz es un muro o no lo es
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @return true en caso de que la casilla no sea un muro
     * @return false en caso de que la casilla sea un muro
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public boolean verifyBox(java.lang.Integer x,java.lang.Integer y){
        if (level[x][y] == 0){
            System.out.println("NO SE PUEDE AGREGAR UN FANTASMA EN EL LUGAR SOLICITADO YA QUE ES UN MURO, INTENTA CON OTRO");
            return false;
        }
        return true;

    }

    /**
     * Metodo addPill
     * Este metodo permite cambiar el valor en una posicion determinada para agregar una pildora
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void addPill(java.lang.Integer x,java.lang.Integer y){
        if (level[x][y] == 3){
            level[x][y] = 2;
            ViewController.getInstance().setDots(1);
        }else if (level[x][y] == 0){
            System.out.println("NO SE PUEDE AGREGAR LA PILDORA EN EL LUGAR SOLICITADO YA QUE ES UN MUERO, INTENTA CON OTRO");
        }else{
            level[x][y] = 2;
        }
    }

    /**
     * Metodo addFruit
     * Este metodo permite cambiar el valor en una posicion determinada para agregar una fruta
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void addFruit(java.lang.Integer x,java.lang.Integer y, java.lang.Integer fruit){
        if (level[x][y] == 3){
            level[x][y] = fruit;
            ViewController.getInstance().setDots(1);
        }else if (level[x][y] == 0){
            System.out.println("NO SE PUEDE AGREGAR LA FRUTA EN EL LUGAR SOLICITADO YA QUE ES UN MUERO, INTENTA CON OTRO");
        }else{
            level[x][y] = fruit;
        }

    }

    /**
     * Metodo totalDots
     * Este metodo recorre la matriz y contabiliza la cantidad de puntos en ellaz
     * @return la cantidad de puntos encontrados
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int totalDots(){
        for(int x = 0; x< sizeMapX(); x++){
            for (int y = 0; y< sizeMapY(); y++){
                int value = getValue(x,y);
                if((value == 1) || (value == 2)){
                    dots++;
                }
            }
        }
        return dots;
    }

    /**
     * Metodo up
     * Este metodo nos indica si existe un movimiento libre hacia arriba
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @return true en caso de que si exista moviemiento
     * @return false en caso no haber movimiento posible
     * @author Mauricio C.Yendry B. Gabriel V.
     */

    public boolean up(int x, int y){
        if(level[x][y-1] == 0){
            return false;
        }
        else{
            return true;}
    }

    /**
     * Metodo down
     * Este metodo nos indica si existe un movimiento libre hacia abajo
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @return true en caso de que si exista moviemiento
     * @return false en caso no haber movimiento posible
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public boolean down(int x, int y){
        if(level[x][y+1] == 0){
            return false;
        }
        else{
            return true;}
    }

    /**
     * Metodo right
     * Este metodo nos indica si existe un movimiento libre hacia la derecha
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @return true en caso de que si exista moviemiento
     * @return false en caso no haber movimiento posible
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public boolean right(int x, int y){
        if(level[x+1][y] == 0){
            return false;
        }
        else{
            return true;}
    }

    /**
     * Metodo left
     * Este metodo nos indica si existe un movimiento libre hacia la izquierda
     * @param x el valor en x de la matriz
     * @param y el valor en y de la matriz
     * @return true en caso de que si exista moviemiento
     * @return false en caso no haber movimiento posible
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public boolean left(int x, int y){
        if(level[x-1][y] == 0){
            return false;
        }
        else{
            return true;}
    }
}
