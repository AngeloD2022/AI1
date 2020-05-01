package com.angelod.ind2.ai1.path;

import java.awt.*;

public class Path2 {

    /**
     * All points on the path.
     */
    //private PathPoint<Integer>[] points;
    boolean[][] pathTiles;
    private int xSegments;
    private int ySegments;
    private int screenWidth;
    private int screenHeight;

    public Path2(int xSegments, int ySegments, int screenWidth, int screenHeight) {
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
        double xIncrement = screenWidth / (double) xSegments;
        double yIncrement = screenHeight / (double) ySegments;

        for (int x = 0; x < xSegments; x++) {
            for (int y = 0; y < ySegments; y++) {
                if (pathTiles[x][y]) {
                    g.setColor(Color.darkGray);
                    g.fillRect((int) (x * xIncrement), (int) (y * yIncrement), (int) xIncrement + 1 /*- ((screenWidth % xSegments) / xSegments)*/, (int) yIncrement + 1 /*- ((screenHeight % ySegments) / ySegments)*/);
                    //g.setColor(Color.darkGray);
                    //g.drawRect((x*xIncrement)+xOffset,(y*yIncrement)+yOffset,xIncrement,yIncrement);
                }
            }
        }
    }

    public boolean validMove(double newx, double newy) {
        int x = (int) (newx * xSegments / screenWidth);
        int y = (int) (newy * ySegments / screenHeight);
        if ((x < 0 || x >= pathTiles.length) || (y < 0 || y >= pathTiles[0].length)) {
            return false;
        }
        return pathTiles[x][y];
    }


    public void clear() {
        pathTiles = new boolean[xSegments][ySegments];
    }

}
