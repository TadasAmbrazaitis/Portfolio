package arkanoid;

import static arkanoid.Constants.BALL_RADIUS;
import java.awt.Rectangle;

/**
 *
 * @author Tadas
 */
public class Ball extends Rectangle {

    private int xDirection = 1;
    private int yDirection = 1;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = BALL_RADIUS;
        this.height = BALL_RADIUS;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void invertXDirection() {
        this.xDirection *= -1;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void invertYDirection() {
        this.yDirection *= -1;
    }

}
