package arkanoid;

import static arkanoid.Constants.BALL_RADIUS;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Tadas
 */
public class Ball extends Rectangle {

    private int xDirection, yDirection;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = BALL_RADIUS;
        this.height = BALL_RADIUS;
        this.xDirection = 1;
        this.yDirection = 1;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = this.xDirection * xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = this.yDirection * yDirection;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(x, y, BALL_RADIUS, BALL_RADIUS);
    }

}
