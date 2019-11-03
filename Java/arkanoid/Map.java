package arkanoid;

import static arkanoid.Constants.AMOUNT_OF_BRICKS_X;
import static arkanoid.Constants.AMOUNT_OF_BRICKS_Y;
import static arkanoid.Constants.SCREEN_HEIGHT;
import static arkanoid.Constants.SCREEN_WIDTH;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Tadas
 */
public class Map {

    int map[][];
    int brickWidth;
    int brickHeight;
    int amountOfBricks = AMOUNT_OF_BRICKS_X * AMOUNT_OF_BRICKS_Y;

    public Map(int row, int col) {
        map = new int[row][col];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 1;
            }
        }
        brickWidth = (SCREEN_WIDTH - 100) / col;
        brickHeight = (SCREEN_HEIGHT / 4) / row;
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    g.setColor(Color.pink);
                    g.fillRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth, brickHeight);

                    g.setStroke(new BasicStroke(5));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 50, i * brickHeight + 50, brickWidth, brickHeight);

                }
            }

        }
    }

    public void brickDestroyed(int row, int col, boolean destroyed) {
        if (destroyed == true) {
            map[row][col] = 0;
            amountOfBricks--;
        }
    }

    public int getAmountOfBricks() {
        return amountOfBricks;
    }

}
