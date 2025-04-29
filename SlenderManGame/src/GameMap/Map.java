package GameMap;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A pálya osztálya. Minden tereptárgy létrehozása.
 */
public class Map{
    String[] sor = new String[15];
    public static final int meret = 15;
    public Things[][] things;
    Image grassImage;
    Image smallTreeImage;
    Image bigTreeImage;
    Image wallImage;
    Image rockImage;
    Image floorImage;
    Image doorImage;
    Image barrelImage;

    /**
     * Pálya létrehozása Things[] típusú tömb segítségével, képek betöltése.
     */
    public Map(){
        things = new Things[15][15];
        grassImage = new ImageIcon("images/grass.png").getImage();
        smallTreeImage = new ImageIcon("images/smallTree.jpg").getImage();
        bigTreeImage = new ImageIcon("images/bigTree.jpg").getImage();
        wallImage = new ImageIcon("images/wall.jpg").getImage();
        rockImage = new ImageIcon("images/rock.jpg").getImage();
        floorImage = new ImageIcon("images/floor.jpg").getImage();
        doorImage = new ImageIcon("images/door.jpg").getImage();
        barrelImage = new ImageIcon("images/barrel.jpg").getImage();
        mapFill();
    }

    /**
     * Pálya feltöltése tereptárgyakkal fájlbeolvasás segítségével.
     */
    public void mapFill(){
        File mapFile = new File("map/map.txt");
        Scanner myScanner;
        try {
            myScanner = new Scanner(mapFile);
            int k = 0;
            while (myScanner.hasNextLine()){
                sor[k] = myScanner.nextLine();
                k++;
            }
            for(int i = 0; i < 15; i ++){
                for(int j = 0; j < 15; j++){
                    char c = sor[i].charAt(j);
                    things[j][i] = new Things();
                    things[j][i].setCode(c);
                }
            }
            myScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Nem található a map.txt a map mappában");
        }
    }

    /**
     *A pálya "draw" metódusa. Megrajzolja az összes tereptárgyat.
     */
    public void draw(Graphics g){
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                int code = things[j][i].getCode();
                if (code == '0') {
                    g.drawImage(grassImage,j*50,i*50,50,50,null);
                }
                else if (code == '1') {
                    g.drawImage(smallTreeImage,j*50,i*50,50,50,null);
                }
                else if (code == '2' || code == '3' || code == '4') {
                    g.drawImage(bigTreeImage,j*50,i*50,50,50,null);
                }
                else if (code == '5') {
                    g.drawImage(wallImage,j*50,i*50,50,50,null);
                }
                else if (code == '6' || code == '7') {
                    g.setColor(new Color(224, 19, 57));
                    g.fillRect(j * 50, i * 50, 50, 50);
                }
                else if (code == '8') {
                    g.setColor(new Color(42, 45, 45));
                    g.fillRect(j * 50, i * 50, 50, 50);
                }
                else if (code == '9' || code == 'a') {
                    g.drawImage(rockImage,j*50,i*50,50,50,null);
                }
                else if (code == 'b') {
                    g.drawImage(barrelImage,j*50,i*50,50,50,null);
                }
                else if (code == 'c') {
                    g.drawImage(floorImage,j*50,i*50,50,50,null);
                }
                else if (code == 'd') {
                    g.drawImage(doorImage,j*50,i*50,50,50,null);
                }
            }
        }
    }
}

