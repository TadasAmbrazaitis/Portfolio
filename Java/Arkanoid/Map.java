package arkanoid;

import static arkanoid.Constants.*;

/**
 *
 * @author Tadas
 */
public class Map {

    private int map[][];
    private int brickWidth;
    private int brickHeight;
    private int amountOfBricks = AMOUNT_OF_BRICKS_X * AMOUNT_OF_BRICKS_Y;

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

    public void brickDestroyed(int row, int col, boolean destroyed) {
        if (destroyed == true) {
            map[row][col] = 0;
            amountOfBricks--;
        }
    }

    public int getAmountOfBricks() {
        return amountOfBricks;
    }

    public int getHeight() {
        return map.length;
    }

    public int getWidth() {
        return map[0].length;
    }

    public int[][] getMap() {
        return map;
    }

    public int getBrickWidth() {
        return brickWidth;
    }

    public int getBrickHeight() {
        return brickHeight;
    }

}
