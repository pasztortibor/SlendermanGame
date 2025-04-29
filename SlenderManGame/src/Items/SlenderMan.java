package Items;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Slenderman osztálya. Láthatóságát, mozgását, rajzolását kezeli.
 */
public class SlenderMan extends Rectangle{
    public boolean visible = false;
    Random rn = new Random();
    int just1distance;
    Image slenderManImage;

    /**
     *Slenderman pozícióját, illetve méreteit beállítja a paraméterekben kapott értékekkel, valamint képet definiál.
     */
    public SlenderMan(int x, int y, int SLENDER_WIDTH, int SLENDER_HEIGHT){
        this.x = x;
        this.y = y;
        width = SLENDER_WIDTH;
        height = SLENDER_HEIGHT;
        just1distance = 0;
        slenderManImage = new ImageIcon("images/slenderMan.jpg").getImage();
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(int px, int py, int papers) {
        this.visible = distance(px, py) <= 3 && papers >= 1;
    }

        /**
        *Slenderman mozgása, várja az eddig megszerzett papírok számát, a játékos eddigi lépéseinek számát, 'x' koordinátáját, valamint 'y' koordinátáját
        */
    public void move(int steps, int papers, int px,int py){
        if(distance(px,py) == 0) {
            slenderWin(px, py, 0);
        }
        else if(steps%5==0){
            do {
                randomSlender();
            } while (distance(px,py) == 0 && papers < 2);
        }
        else {
            if (papers < 2) {
                do {
                    randomSlender();
                } while (distance(px,py) == 0 || distance(px,py) <= 5);
            }

            else if (papers < 4) {
                do {
                    randomSlender();
                } while (distance(px,py) >= 5);
            }

            else if (papers < 6) {
                do {
                    randomSlender();
                } while (distance(px,py) >= 4);
            }

            else {
                do {
                    randomSlender();
                } while (distance(px,py) >= 3);
            }
        }
        if(distance(px,py) == 1)
            just1distance++;

        if(just1distance == 3){

            if(papers<4)
                slenderWin(px, py, 1);
            else if(papers<6)
                slenderWin(px, py, 2);
            else
                slenderWin(px, py, 3);


            just1distance = 0;
        }

        if(distance(px,py) > 1)
            just1distance = 0;
    }

    /**
     *Slenderman nyerési esélyeit vizsgálja abban az esetben, ha 3 egymást követő lépésben csak 1 távolság volt a játékos, illetve közötte.
     */
    public void slenderWin(int playerx, int playery, int chance){
        int rn1 = rn.nextInt(3);
        int rn2 = rn.nextInt(2);
        if(chance == 0) {
            x = playerx;
            y = playery;
        }
        else if(chance == 1 && rn1 == 0){
            x = playerx;
            y = playery;
        }
        else if(chance == 2 && rn2 == 0){
            x = playerx;
            y = playery;
        }
        else if((chance == 3) && (rn1 == 0 || rn1 == 1)){
            x = playerx;
            y = playery;
        }
    }

    /**
     *Slenderman, illetve a játékos közötti távolság kiszámításáért felelős.
     */
    public int distance(int playerx, int playery){
        return Math.abs(x - playerx) + Math.abs(y - playery);
    }

    /**
     * A pályán véletlenszerűen elhelyezi Slendermant.
     */
    public void randomSlender(){
        x = rn.nextInt(15);
        y = rn.nextInt(15);
    }

    /**
     *Slenderman "draw" metódusa. Annak rajzolásért felelős.
     */
    public void draw(Graphics g){
        if(this.isVisible()) {
            g.drawImage(slenderManImage,x*50,y*50,50,50,null);
        }
    }
}