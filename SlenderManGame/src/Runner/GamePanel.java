package Runner;

import Items.Paper;
import Items.Player;
import Items.SlenderMan;
import GameMap.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * A játék panelje. A játék kinézetéért, a lényegi -futtatás közbeni- működéséért felelős.
 */
public class GamePanel extends JPanel {

    static final int UNIT_SIZE = 50;
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 750;
    boolean running = false;
    Image image;
    Image gameOverImage;
    Image winImage;
    Image sideLinePaperImage;
    Image sideLineFootprintImage;
    Graphics graphics;

    public static Map map;
    public static Player player;

    Paper paper;
    SlenderMan slenderMan;

    /**
     * Elindítja a játékot, panel beállításokat eszközöl, képeket hoz létre.
     */
    public GamePanel(){
        startGame();
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        gameOverImage = new ImageIcon("images/gameOver.jpg").getImage();
        winImage = new ImageIcon("images/win.jpg").getImage();
        sideLinePaperImage = new ImageIcon("images/sideLinePaper.jpg").getImage();
        sideLineFootprintImage = new ImageIcon("images/sideLineFootprint.jpg").getImage();
    }

    /**
     * Mindent létrehoz, beállít ami a játék felépítéséért felelős.
     */
    public void startGame(){
        running = true;
        map = new Map();
        player = new Player(14, 14, UNIT_SIZE, UNIT_SIZE);
        slenderMan = new SlenderMan(-14, -14, UNIT_SIZE, UNIT_SIZE);
        paper = new Paper();
        paper.newPapers();
    }

    /**
     *Logikai értéket ad vissza attól függően, hogy megnyertük-e a játékot vagy sem, avagy összegyűjtöttük-e a 8db papírt.
     */
    public boolean checkWin(){
        if(paper.howManyPaperIsVisible()==8) {
            running = false;
            return true;
        }
        else
            return false;
    }

    /**
     * Logikai értéket ad vissza attól függően, hogy elveszítettük-e a játékot vagy sem, avagy Slenderman illetve a játékos pozíciója megegyezik-e.
     */
    public boolean checkGameOver(){
        if(player.x==slenderMan.x&&player.y==slenderMan.y){
            running = false;
            return true;
        }
        else
            return false;
    }

    /**
     *Meghívja az összes "draw" metódust, repaint metódust kezeli.
     */
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);
    }

    /**
     *Játék megrajzolása attól függően, hogy "fut" a játék, vagy sem.
     */
    public void draw(Graphics g) {
        if(running) {
            map.draw(g);
            paper.draw(g);
            player.draw(g);
            slenderMan.draw(g);
            drawPapersLine(g);
            drawStepsLine(g);
            drawSideLine(g);
        }
        if(!running){
            if(checkWin())
                drawWin(g);
            if(checkGameOver())
                drawGameOver(g);
        }
    }

    /**
     *Játék képernyő, illetve információs sáv elválasztása vonallal.
     */
    public void drawSideLine(Graphics g){
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman",Font.BOLD,150));
        g.drawLine(751,750,751,0);
        g.drawLine(752,750,752,0);
        g.drawLine(753,750,753,0);
        g.drawLine(754,750,754,0);
    }

    /**
     *Információs sáv azon része, ami megmutatja, hány papírt gyűjtöttünk össze eddig.
     */
    public void drawPapersLine(Graphics g) {
        g.setFont(new Font("Times New Roman",Font.BOLD,80));
        g.setColor(Color.white);
        g.drawImage(sideLinePaperImage,880,135,80,80,this);
        g.drawString(paper.howManyPaperIsVisible()+ "/8",780,200);
    }
    /**
     *Információs sáv azon része, ami megmutatja, hány lépést tettünk meg eddig.
     */
    public void drawStepsLine(Graphics g) {
        g.setFont(new Font("Times New Roman",Font.BOLD,80));
        g.setColor(Color.white);
        g.drawImage(sideLineFootprintImage,890,510,80,80,this);
        g.drawString(String.valueOf(player.steps),780,575);
    }

    /**
     *Új kép rajzolása, abban az esetben, ha nyernénk.
     */
    public void drawWin(Graphics g){
        g.drawImage(winImage,0,0,this);
        g.setFont(new Font("Times New Roman",Font.BOLD,55));
        g.setColor(Color.white);
        g.drawString("Gratulálok, nyertél " + player.steps + " lépés alatt.",90,610);
    }
    /**
     *Új kép rajzolása, abban az esetben, ha veszítenénk.
     */
    public void drawGameOver(Graphics g){
        g.drawImage(gameOverImage,0,0,this);
        g.setFont(new Font("Times New Roman",Font.BOLD,50));
        g.setColor(Color.red);
        g.drawString("GAME OVER",330,650);
        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman",Font.BOLD,20));
        g.drawString("Press double 'r' to restart...",380,700);
    }

    /**
     * Billentyű-lenyomásokat kezelő osztály.
     */
    public class AL extends KeyAdapter {
        /**
         *Billentyű-lenyomások utáni frissítések, metódushívások.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if(running) {
                player.isStep = false;
                player.keyPressed(e);
                if(paper.howManyPaperIsVisible() > 0 && player.isStep)
                    slenderMan.move(player.steps, paper.howManyPaperIsVisible(),player.x,player.y);
                slenderMan.setVisible(player.x,player.y,paper.howManyPaperIsVisible());
                paper.checkNewPaper();
                checkGameOver();
                checkWin();
                repaint();
            }
            else{
                restartPressed(e);
            }
        }

        /**
         *Restart esetén újraindítja a játékot.
         */
        public void restartPressed(KeyEvent e){
            if(e.getKeyChar() == 'r'){
                startGame();
            }
        }
    }
}