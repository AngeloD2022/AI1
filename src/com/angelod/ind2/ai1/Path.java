package com.angelod.ind2.ai1;

import java.awt.*;

public class Path {

    /**
     * All points on the path.
     */
    //private PathPoint<Integer>[] points;
    boolean[][] pathTiles;
    private int pointsFilled;
    private int xSegments;
    private int ySegments;
    private int screenWidth;
    private int screenHeight;
    int xIncrement;
    int yIncrement;


    public Path(int xSegments, int ySegments) {
        this.xSegments = xSegments;
        this.ySegments = ySegments;
        this.pathTiles = new boolean[xSegments][ySegments];
    }


    public void toggleTile(int xPixels, int yPixels) {
        int x = (xPixels / xIncrement);
        int y = (yPixels / yIncrement);
        pathTiles[x][y] = !pathTiles[x][y];
    }


    /**
     * Draws path to graphics object.
     *
     * @param scaleFromOrigin Scale factor from x = 0, y = 0
     * @param xOffset         X offset from 0
     * @param yOffset         Y offset from 0
     * @param g               Graphics object to draw to.
     */
    public void drawPath(int scaleFromOrigin, int xOffset, int yOffset, Graphics g, int screenw, int screenh) {
        screenWidth = screenw;
        screenHeight = screenh;
        xIncrement = screenw / xSegments;
        yIncrement = screenh / ySegments;

        for (int x = 0; x < xSegments; x++) {
            for (int y = 0; y < ySegments; y++) {
                if (pathTiles[x][y]) {
                    g.setColor(Color.red);
                    g.fillRect((x * xIncrement), (y * yIncrement), xIncrement - ((screenWidth % xSegments) / xSegments), yIncrement - ((screenHeight % ySegments) / ySegments));
                    g.setColor(Color.darkGray);
                    //g.drawRect((x*xIncrement)+xOffset,(y*yIncrement)+yOffset,xIncrement,yIncrement);
                }
            }
        }
    }

    public boolean validMove(int xPixels, int yPixels, int w, int a, int s, int d) {
        int x = xPixels + d - a / xSegments;
        int y = yPixels + s - w / ySegments;
        return pathTiles[x][y];
    }

    public void clear() {
        pathTiles = new boolean[xSegments][ySegments];
    }

}
