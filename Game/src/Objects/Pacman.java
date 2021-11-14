package Objects;

/**
 * Write a description of class Pacman here.
 *
 * @author Mauricio Calderon.
 *
 */

public class Pacman extends Characters
{
    int pendingDirection;
    private int lives;
    /**
     * Constructor for objects of class Pacman
     */
    public Pacman()
    {
        super("/Resources/right.gif", 420,660);
        pendingDirection = 0;
        lives = 3;
    }

    /**
     * Redefinimos el método move().
     */
    public void move(){
        verifyRequest();
        super.move();
    }

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

    public void emptyDirection(){
        pendingDirection =0;
    }

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

    public void pacmanDeath()
    {
        lives--;
        x=420;
        y=660;
    }

    public int pacmanLives()
    {
        return lives;
    }

}
