package com.angelod.ind2.ai1;

import java.awt.*;

public class Path {

    /**
     * All points on the path.
     */
    //private PathPoint<Integer>[] points;
    boolean[][] pathTiles;
    private int xSegments;
    private int ySegments;
    private int screenWidth;
    private int screenHeight;


    public Path(int xSegments, int ySegments, int screenWidth, int screenHeight) {
        this.xSegments = xSegments;
        this.ySegments = ySegments;
        this.pathTiles = new boolean[xSegments][ySegments];
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public boolean[][] getPathTiles() {
        return pathTiles;
    }

    public void setPathTiles(boolean[][] pathTiles) {
        this.pathTiles = pathTiles;
    }

    /**
     * Draws path to graphics object.
     *
     * @param g Graphics object to draw to.
     */
    public void drawPath(Graphics g) {
        int xIncrement = screenWidth / xSegments;
        int yIncrement = screenHeight / ySegments;

        for (int x = 0; x < xSegments; x++) {
            for (int y = 0; y < ySegments; y++) {
                if (pathTiles[x][y]) {
                    g.setColor(Color.darkGray);
                    g.fillRect((x * xIncrement), (y * yIncrement), xIncrement /*- ((screenWidth % xSegments) / xSegments)*/, yIncrement /*- ((screenHeight % ySegments) / ySegments)*/);
                    //g.setColor(Color.darkGray);
                    //g.drawRect((x*xIncrement)+xOffset,(y*yIncrement)+yOffset,xIncrement,yIncrement);
                }
            }
        }
    }

    public boolean validMove(int newx, int newy) {
        int x = (int) (newx / (screenWidth / xSegments));
        int y = (int) (newy / (screenHeight / ySegments));
        return pathTiles[x][y];
    }

    public void clear() {
        pathTiles = new boolean[xSegments][ySegments];
    }

}
