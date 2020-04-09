package arkanoid;

import static arkanoid.Constants.SCREEN_HEIGHT;
import static arkanoid.Constants.SCREEN_WIDTH;
import javax.swing.JFrame;

/**
 *
 * @author Tadas
 */
public class Arkanoid {

    public static void main(String[] args) {
        JFrame Game = new JFrame();
        SwingGame sg = new SwingGame();
        Game.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        Game.setTitle("Arkanoid - Tadas Ambrazaitis");
        Game.setResizable(false);
        Game.setVisible(true);
        Game.setLocationRelativeTo(null);
        Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game.add(sg);
    }
}
