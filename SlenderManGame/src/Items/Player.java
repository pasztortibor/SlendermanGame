package Items;

import Runner.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A játékos osztálya. Lépéseket számol, léphető mezőket definiál.
 */
public class Player extends Rectangle {
    public int steps;
    Image playerImage;
    public boolean isStep = false;

    /**
     *A játékos pozícióját, illetve méreteit beállítja a paraméterekben kapott értékekkel, valamint képet definiál.
     */
    public Player(int x, int y, int PLAYER_WIDTH, int PLAYER_HEIGHT){
        this.x = x;
        this.y = y;
        width = PLAYER_WIDTH;
        height = PLAYER_HEIGHT;
        playerImage = new ImageIcon("images/iconPerson.png").getImage();
    }

    /**
     *"WASD" gombok érzékelője, a játékos pozícióját változtatja különböző szabályok betartásával, valamint lépést számol.
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'a' && x>0 && GamePanel.map.things[x-1][y].isStepable()) {
            x -= 1;
            steps++;
            isStep = true;
        }
        if(e.getKeyChar() == 'd' && x<14 && GamePanel.map.things[x+1][y].isStepable()) {
            x += 1;
            steps++;
            isStep = true;
        }
        if(e.getKeyChar() == 'w' && y>0 && GamePanel.map.things[x][y-1].isStepable()) {
            y -= 1;
            steps++;
            isStep = true;
        }
        if(e.getKeyChar() == 's' && y<14 && GamePanel.map.things[x][y+1].isStepable()) {
            y += 1;
            steps++;
            isStep = true;
        }
    }

    /**
     *A játékos "draw" metódusa. Annak rajzolásért felelős.
     */
    public void draw(Graphics g){
        g.drawImage(playerImage,x*50,y*50,50,50,null);
    }
}