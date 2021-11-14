package Game;

import javax.swing.ImageIcon;
import java.awt.Image;
/**
 * @author Mauricio C Yendry B Gabriel Vargas
 */

class Maps
{
    private int [][] level =
                    {{0,0,0,0,0,0,0,0,0,0,0,0,0},
                    {0,2,1,1,1,1,1,1,1,1,1,2,0},
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
                    {0,2,1,1,1,1,1,1,1,1,1,2,0},
                    {0,0,0,0,0,0,0,0,0,0,0,0,0}};

    private Image image;       //La variable imagen donde irá la imagen de la figura, ya sea muro, punto pequeño, punto grando o punto vacío.
    private ImageIcon imageIcon;
    private int dots;


    Maps()
    {
        dots = 0;
    }

    //Metodo que permite recuperar la matriz
    public Image getImage(int x, int y)
    {
        int location = level[x][y];
        if(location == 0)
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/wall.gif"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 1)
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/Pac-Dot.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 2)
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/Pill.png"));
            image = imageIcon.getImage();
            return image;
        }

        if(location == 3)
        {
            imageIcon = new ImageIcon(this.getClass().getResource("/Resources/vacio.gif"));
            image = imageIcon.getImage();
            return image;
        }

        else
        {
            return null;
        }
    }

    public int getValue(int x, int y)
    {
        return level[x][y];
    }

    public void eatDot(int x, int y)
    {
        level[x][y] = 3;
    }

    public int sizeMapX()
    {
        return level.length;
    }

    public int sizeMapY()
    {
        return level[0].length;
    }

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

    public boolean up(int x, int y){
        if(level[x][y-1] == 0){
            return false;
        }
        else{
            return true;}
    }

    public boolean down(int x, int y){
        if(level[x][y+1] == 0){
            return false;
        }
        else{
            return true;}
    }

    public boolean right(int x, int y){
        if(level[x+1][y] == 0){
            return false;
        }
        else{
            return true;}
    }

    public boolean left(int x, int y){
        if(level[x-1][y] == 0){
            return false;
        }
        else{
            return true;}
    }
}
