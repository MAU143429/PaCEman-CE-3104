package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class TAdapter extends KeyAdapter {
    private boolean inGame = false;
    private int req_dx, req_dy;
    private Timer timer;

    public TAdapter(boolean inGame, int req_dx, int req_dy, Timer timer){
        this.inGame = inGame;
        this.req_dx = req_dx;
        this.req_dy = req_dx;
        this.timer = timer;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (inGame) {
            if (key == KeyEvent.VK_LEFT) {
                req_dx = -1;
                req_dy = 0;
            } else if (key == KeyEvent.VK_RIGHT) {
                req_dx = 1;
                req_dy = 0;
            } else if (key == KeyEvent.VK_UP) {
                req_dx = 0;
                req_dy = -1;
            } else if (key == KeyEvent.VK_DOWN) {
                req_dx = 0;
                req_dy = 1;
            } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                inGame = false;
            }
        } else {
            if (key == KeyEvent.VK_SPACE) {
                inGame = true;
                //initGame();
            }
        }
    }
}
