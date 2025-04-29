package Runner;

import javax.swing.*;
import java.awt.*;

/**
 * A játék frame-jét építi fel, meghívja a GamePanel nevű játékpanelért felelős osztályt.
 */
public class GameFrame extends JFrame {

    GamePanel panel;

    /**
     * GameFrame paraméter nélküli konstruktora, beállításokat eszközöl, ikont ad játéknak.
     */
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Slender Man");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        ImageIcon image = new ImageIcon("images/icon.jpg");
        this.setIconImage(image.getImage());
    }
}