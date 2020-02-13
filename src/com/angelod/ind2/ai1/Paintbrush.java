package com.angelod.ind2.ai1;

public class Paintbrush extends PaintTool {



    @Override
    public boolean[][] doPaint(double x, double y, boolean[][] canvas, int radius) {

        int xlength = canvas.length;
        int ylength = canvas[0].length;
        x *= 100.0 / 790;
        y *= 100.0 / 790;
        boolean[][] result = canvas;

        for (int r = 1; r <= radius; r++) {
            for (int i = 1; i <= 360; i++) {
                // i acts as an angle in degrees
                double changeX = (r * Math.cos(i * (Math.PI / 180.0)));
                double changeY = (r * Math.sin(i * (Math.PI / 180.0)));

                if ((x + changeX < xlength && x + changeX > 0) && (y + changeY < ylength && y + changeY > 0))
                    result[(int) (x + changeX)][(int) (y + changeY)] = true;
            }
        }


        return result;

    }
}
