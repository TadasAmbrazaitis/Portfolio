package arkanoid;

import static arkanoid.Constants.*;
import java.awt.Rectangle;

/**
 *
 * @author Tadas Ambrazaitis
 */
public class GameRules {

    private Map map;
    private Paddle paddle;
    private Ball ball;

    public GameRules(Map map, Paddle paddle, Ball ball) {
        this.map = map;
        this.paddle = paddle;
        this.ball = ball;
    }

    public boolean isGameOver() {
        return ball.getY() > SCREEN_HEIGHT - 2 * BALL_RADIUS || map.getAmountOfBricks() == 0;
    }

    public void movePaddleLeft() {
        if (paddle.getX() > 0) {
            paddle.setX((int) paddle.getX() - PADDLE_SPEED);
        }
        if (paddle.getX() < 0) {
            paddle.setX(0);
        }
    }

    public void movePaddleRight() {
        if (paddle.getX() + PADDLE_WIDTH < SCREEN_WIDTH) {
            paddle.setX((int) paddle.getX() + PADDLE_SPEED);
        }
        if (paddle.getX() + PADDLE_WIDTH > SCREEN_WIDTH) {
            paddle.setX(SCREEN_WIDTH - PADDLE_WIDTH);
        }
    }

    public void checkCollision() {
        for (int i = 0; i < map.getHeight(); i++) {
            for (int j = 0; j < map.getWidth(); j++) {
                if (map.getMap()[i][j] == 1) {
                    int brickX = j * map.getBrickWidth() + 50;
                    int brickY = i * map.getBrickHeight() + 50;
                    Rectangle brick = new Rectangle(brickX, brickY, map.getBrickWidth(), map.getBrickHeight());

                    if (ball.intersects(brick)) {
                        map.brickDestroyed(i, j, true);
                        if (ball.getX() + BALL_RADIUS <= brick.x || ball.getX() >= brick.x + map.getBrickWidth()) {
                            ball.invertXDirection();
                        } else {
                            ball.invertYDirection();
                        }
                    }
                }
            }
        }
    }

    public void moveBall() {
        ball.setX((int) (ball.getX() + ball.getxDirection()));
        ball.setY((int) (ball.getY() + ball.getyDirection()));

        if (ball.getX() <= 0) {
            ball.invertXDirection();
        }
        if (ball.getY() <= 0) {
            ball.invertYDirection();
        }
        
        if (ball.getX() == SCREEN_WIDTH - BALL_RADIUS) {
            ball.invertXDirection();
        }
        if (ball.intersects(paddle)) {
            ball.invertYDirection();
        }
    }
}
