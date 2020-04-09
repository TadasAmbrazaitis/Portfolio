package arkanoid;

import static arkanoid.Constants.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Tadas
 */
public class SwingGame extends JPanel implements KeyListener, ActionListener {

    boolean play = false;
    Map map = new Map(AMOUNT_OF_BRICKS_Y, AMOUNT_OF_BRICKS_X);
    Ball ball = new Ball(SCREEN_WIDTH / 2 - BALL_RADIUS / 2, SCREEN_WIDTH - 70);
    Paddle paddle = new Paddle(SCREEN_WIDTH / 2 - PADDLE_WIDTH / 2);
    SwingGameRenderer gameRenderer = new SwingGameRenderer(map, paddle, ball);
    GameRules gameRules = new GameRules(map, paddle, ball);

    public SwingGame() {
        super.addKeyListener(this);
        super.setFocusable(true);
        new Timer(DELAY, this).start();
    }

    @Override
    public void paint(Graphics g) {
        gameRenderer.renderGame((Graphics2D) g);
        if (gameRules.isGameOver() == true) {
            gameRenderer.drawGameOver(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (play) {
            gameRules.moveBall();
            gameRules.checkCollision();
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            play = true;
            gameRules.movePaddleRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            play = true;
            gameRules.movePaddleLeft();
        }

    }
}
