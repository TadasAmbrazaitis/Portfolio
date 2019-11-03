package arkanoid;

import static arkanoid.Constants.AMOUNT_OF_BRICKS_X;
import static arkanoid.Constants.AMOUNT_OF_BRICKS_Y;
import static arkanoid.Constants.BALL_RADIUS;
import static arkanoid.Constants.DELAY;
import static arkanoid.Constants.PADDLE_WIDTH;
import static arkanoid.Constants.SCREEN_HEIGHT;
import static arkanoid.Constants.SCREEN_WIDTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
public class GameRenderer extends JPanel implements KeyListener, ActionListener {

    boolean play = false;
    Timer Timer;
    Map map;
    Ball ball;
    Paddle paddle;

    public GameRenderer() {
        map = new Map(AMOUNT_OF_BRICKS_Y, AMOUNT_OF_BRICKS_X);
        paddle = new Paddle(SCREEN_WIDTH / 2 - PADDLE_WIDTH / 2);
        ball = new Ball(SCREEN_WIDTH / 2 - BALL_RADIUS / 2, SCREEN_WIDTH - 70);

        addKeyListener(this);
        setFocusable(true);
        Timer = new Timer(DELAY, this);
        Timer.start();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        map.draw((Graphics2D) g);
        paddle.draw((Graphics2D) g);
        ball.draw((Graphics2D) g);

        if (ball.getY() > SCREEN_HEIGHT - 3 * BALL_RADIUS || map.getAmountOfBricks() == 0) {
            play = false;
            ball.setxDirection(0);
            ball.setyDirection(0);
            g.setColor(Color.red);
            g.setFont(new Font("Times New Roman", Font.BOLD, 50));
            g.drawString("   Game Over   ", 250, 400);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Timer.start();
        if (play) {
            Start:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 50;
                        int brickY = i * map.brickHeight + 50;

                        Rectangle brick = new Rectangle(brickX, brickY, map.brickWidth, map.brickHeight);

                        if (ball.intersects(brick)) {
                            map.brickDestroyed(i, j, true);
                            if (ball.getX() + BALL_RADIUS - 1 <= brick.x || ball.getX() + 1 >= brick.x + map.brickWidth) {
                                ball.setxDirection(-1);
                            } else {
                                ball.setyDirection(-1);
                            }
                            break Start;
                        }
                    }

                }
            }

            ball.setX((int) (ball.getX() + ball.getxDirection()));
            ball.setY((int) (ball.getY() + ball.getyDirection()));

            if (ball.getX() < 0) {
                ball.setxDirection(-1);
            }
            if (ball.getY() < 0) {
                ball.setyDirection(-1);
            }
            if (ball.getX() + BALL_RADIUS > SCREEN_WIDTH) {
                ball.setxDirection(-1);
            }
            if (ball.intersects(paddle)) {
                ball.setyDirection(-1);
            }
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
            paddle.moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            play = true;
            paddle.moveLeft();
        }

    }
}
