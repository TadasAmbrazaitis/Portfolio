package arkanoid;

import static arkanoid.Constants.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Tadas Ambrazaitis
 */
public class SwingGameRenderer {

    private Map map;
    private Paddle paddle;
    private Ball ball;

    public SwingGameRenderer(Map map, Paddle paddle, Ball ball) {
        this.map = map;
        this.paddle = paddle;
        this.ball = ball;
    }

    void renderGame(Graphics2D g) {
        drawBackground(g);
        drawMap(g);
        drawPaddle(g);
        drawBall(g);
    }

    void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    void drawMap(Graphics2D g) {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.getMap()[i][j] == 1) {
                    g.setColor(Color.pink);
                    g.fillRect(j * map.getBrickWidth() + 50, i * map.getBrickHeight() + 50, map.getBrickWidth(), map.getBrickHeight());
                    g.setStroke(new BasicStroke(5));
                    g.setColor(Color.black);
                    g.drawRect(j * map.getBrickWidth() + 50, i * map.getBrickHeight() + 50, map.getBrickWidth(), map.getBrickHeight());
                }
            }
        }
    }

    void drawPaddle(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int) paddle.getX(), PADDLE_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    void drawBall(Graphics g) {
        g.setColor(Color.white);
        g.fillOval((int) ball.getX(), (int) ball.getY(), BALL_RADIUS, BALL_RADIUS);
    }

    void drawGameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman", Font.BOLD, 50));
        g.drawString("   Game Over   ", 250, 400);
    }
}
