package com.angelod.ind2.ai1;

import java.awt.*;

public class Path {

    /**
     * All points on the path.
     */
    private PathPoint<Integer>[] points;
    private int pointsFilled;

    public Path(int pointCount) {
        points = new PathPoint[pointCount];
        pointsFilled = 0;
    }


    /**
     * Appends point with given coordinates to the path.
     *
     * @param x
     * @param y
     */
    public void appendPoint(int x, int y) {

        if (pointsFilled < points.length) {

            // Create new point with top ID
            PathPoint newPoint = new PathPoint<Integer>(x, y, pointsFilled + 1);

            // Add point to array.
            points[pointsFilled] = newPoint;

            // Increment filled point count
            pointsFilled++;
        }
    }


    /**
     * Draws path to graphics object.
     *
     * @param scaleFromOrigin Scale factor from x = 0, y = 0
     * @param xOffset         X offset from 0
     * @param yOffset         Y offset from 0
     * @param g               Graphics object to draw to.
     */
    public void drawPath(int scaleFromOrigin, int xOffset, int yOffset, Graphics g) {


    }

}
