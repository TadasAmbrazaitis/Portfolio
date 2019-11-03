package arkanoid;

import static arkanoid.Constants.PADDLE_HEIGHT;
import static arkanoid.Constants.PADDLE_SPEED;
import static arkanoid.Constants.PADDLE_WIDTH;
import static arkanoid.Constants.SCREEN_HEIGHT;
import static arkanoid.Constants.SCREEN_WIDTH;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tadas
 */
public class Paddle extends Rectangle {

    public Paddle(int x) {
        this.x = x;
        this.y = SCREEN_HEIGHT - 50;
        this.width = PADDLE_WIDTH;
        this.height = PADDLE_HEIGHT;
    }

    public void moveLeft() {
        if (x > 0) {
            x = x - PADDLE_SPEED;
        }
    }

    public void moveRight() {
        if (x + PADDLE_WIDTH < SCREEN_WIDTH) {
            x = x + PADDLE_SPEED;
        }
    }

    public double getX() {
        return x;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.pink);
        g.fillRect(x, y, width, height);
    }

}
