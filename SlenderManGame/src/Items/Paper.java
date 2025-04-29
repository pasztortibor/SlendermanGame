package Items;

import Runner.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Papír osztálya. Láthatóságát, létrehozását, rajzolását kezeli.
 */
public class Paper extends Rectangle {
    public boolean visible = false;
    public Paper[] papers;
    Random rn;
    Image paperImage;

    public Paper(){
        paperImage = new ImageIcon("images/paper.jpg").getImage();
    }

    /**
     *A papír pozícióját, illetve méreteit beállítja a paraméterekben kapott értékekkel.
     */
    public Paper(int x, int y, int PAPER_WIDTH, int PAPER_HEIGHT){
        this.x = x;
        this.y = y;
        width = PAPER_WIDTH;
        height = PAPER_HEIGHT;

    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     *Visszatér azzal az értékkel, mellyel megegyezik az eddig összegyűjtött papírok száma.
     */
    public int howManyPaperIsVisible(){
        int pcs = 0;
        for(int i = 0; i<8;i++){
            if(papers[i].isVisible())
                pcs++;
        }
        return pcs;
    }

    /**
     * Egy-egy papírt láthatóvá (begyűjtötté) tesz, abban az esetben ha a játékos a papír melletti bármelyik mezőre lép.
     */
    public void checkNewPaper(){
        for(int i = 0; i < 8; i++)
            if((GamePanel.player.y == papers[i].y && GamePanel.player.x == papers[i].x-1) || (GamePanel.player.y == papers[i].y-1 && GamePanel.player.x == papers[i].x) || (GamePanel.player.y == papers[i].y+1 && GamePanel.player.x == papers[i].x) || (GamePanel.player.y == papers[i].y && GamePanel.player.x == papers[i].x+1)){
                papers[i].setVisible(true);
            }
    }

    /**
     * Új 8db papírt képes generálni a lehetséges tereptárgyakra, tereptárgyanként kizárólag egyet.
     */
    public void newPapers() {
        papers = new Paper[8];
        rn = new Random();
        boolean mehet;
        boolean mehet2;
        int counter;
        char[] whichThings;
        whichThings = new char[]{'2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b'};
        char[] randoms = new char[8];
        int counter2 = 0;
        for(int i = 0; i < 8; i++){
            mehet = false;
            randoms[i] = whichThings[rn.nextInt(10)];
            while(!mehet){
                counter = 0;
                for(int j = 0; j < counter2; j++){
                    if(randoms[i] == randoms[j])
                        counter++;
                }
                if(counter == 0) {
                    mehet = true;
                    counter2++;
                }
                else
                    randoms[i] = whichThings[rn.nextInt(10)];
            }
            do{
                mehet = false;
                mehet2 = false;
                papers[i] = new Paper(rn.nextInt(14), rn.nextInt(14),50,50);
                if(randoms[i] == GamePanel.map.things[papers[i].x][papers[i].y].getCode())
                    mehet2 = true;
                if((papers[i].x > 0 && papers[i].x <14) && ('0' == GamePanel.map.things[papers[i].x-1][papers[i].y].getCode() || '0' == GamePanel.map.things[papers[i].x+1][papers[i].y].getCode()))
                    mehet = true;
                if((papers[i].y > 0 && papers[i].y <14) && ('0' == GamePanel.map.things[papers[i].x][papers[i].y-1].getCode() || '0' == GamePanel.map.things[papers[i].x][papers[i].y+1].getCode()))
                    mehet = true;
            } while(!mehet2 || !mehet);
        }
    }

    /**
     *A papírok "draw" metódusa. Azok rajzolásáért felelős.
     */
    public void draw(Graphics g){
        for(int i = 0; i<8; i++) {
            if (papers[i].isVisible())
                g.drawImage(paperImage,papers[i].x*50,papers[i].y*50,50,50,null);
        }
    }
}