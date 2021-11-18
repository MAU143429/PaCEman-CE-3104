package Objects;

/**
 * Pacman Class
 * Esta clase es la principal de pacman y hereda de los Characters
 * aqui se le colocan los atributos propios de pacman
 * @author Mauricio C.Yendry B. Gabriel V.
 */
public class Pacman extends Characters
{
    int pendingDirection;
    private int lives;
    /**
     * Constructor de la clase Pacman
     * Se cargan las imagenes de pacmana y se le asignan vidas
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public Pacman()
    {
        super("/Resources/right.gif", 420,660);
        pendingDirection = 0;
        lives = 3;
    }

    /**
     * Metodo move
     * Este metodo se sobreescribe de la clase Characters y permite
     * verficiar si pacman tiene disponible un movimiento y en caso
     * de que si lo mueve.
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void move(){
        verifyRequest();
        super.move();
    }

    /**
     * Metodo moveUp
     * Este metodo permite actualizar la imagen de pacman al moverse hacia arriba
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void moveUp(){
        if(up && (x%60 == 0)){
            up();
            updateImage("/Resources/up.gif");
            emptyDirection();
            right=left=false;
        }
        else{
            pendingDirection =1;}
    }

    /**
     * Metodo moveDown
     * Este metodo permite actualizar la imagen de pacman al moverse hacia abajo
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void moveDown(){
        if(down && (x%60 == 0)){
            down();
            updateImage("/Resources/down.gif");
            emptyDirection();
            right=left=false;
        }
        else{
            pendingDirection =2;}
    }

    /**
     * Metodo moveRight
     * Este metodo permite actualizar la imagen de pacman al moverse hacia la derecha
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void moveRight(){
        if(y%60 == 0){
            right();
            updateImage("/Resources/right.gif");
            emptyDirection();
            down=up=false;
        }
        else{
            pendingDirection =3;}
    }

    /**
     * Metodo moveLeft
     * Este metodo permite actualizar la imagen de pacman al moverse hacia la izquierda
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void moveLeft(){
        if(y%60 == 0){
            left();
            updateImage("/Resources/left.gif");
            emptyDirection();
            down=up=false;
        }
        else{
            pendingDirection =4;}
    }

    /**
     * Metodo emptyDirection
     * Este metodo permite cambiar el status de las direcciones pendientes a 0
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void emptyDirection(){
        pendingDirection =0;
    }

    /**
     * Metodo verifyRequest
     * Este metodo permite verificar hacia donde se movera
     * basandose en las direcciones pendientes y si el pacman
     * se encuentra en una interseccion
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void verifyRequest()
    {
        if(intersection){
            if(pendingDirection == 1){
                moveUp();}
            if(pendingDirection == 2){
                moveDown();}
            if(pendingDirection == 3){
                moveRight();}
            if(pendingDirection == 4){
                moveLeft();}
        }
    }
    /**
     * Metodo pacmanDeath
     * Este metodo le quita una vida a pacman luego de que este muero en el juego
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void pacmanDeath()
    {
        lives--;
        x=420;
        y=660;
    }

    /**
     * Metodo Lives
     * Este metodo permite sumarle una vida a pacman
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public void lives()
    {
        lives++;
    }

    /**
     * Metodo pacmanLives
     * Este metodo nos permite obtener la cantidad de vidas que tiene pacman
     * @return cantidad de vidas de pacman
     * @author Mauricio C.Yendry B. Gabriel V.
     */
    public int pacmanLives()
    {
        return lives;
    }

}
