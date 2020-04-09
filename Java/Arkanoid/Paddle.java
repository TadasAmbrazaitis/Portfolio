package arkanoid;

import static arkanoid.Constants.*;
import java.awt.Rectangle;

/**
 *
 * @author Tadas
 */
public class Paddle extends Rectangle {
    
    public Paddle(int x) {
        this.x = x;
        this.y = PADDLE_Y;
        this.width = PADDLE_WIDTH;
        this.height = PADDLE_HEIGHT;
    }

    public void setX(int x) {
        this.x = x;
    }
}
