package com.angelod.ind2.ai1;

public class Paintbrush extends PaintTool {

    public Paintbrush(String name) {
        super(name);

    }

    @Override
    public boolean[][] doPaint(int x, int y, boolean[][] canvas, int radius) {

        int xlength = canvas.length;
        int ylength = canvas[0].length;
        boolean[][] result = canvas;

        for (int r = 1; r <= radius; r++) {
            for (int i = 1; i <= 360; i++) {
                // i acts as an angle in degrees
                int changeX = (int) (r * Math.cos(i * (Math.PI / 180)));
                int changeY = (int) (r * Math.sin(i * (Math.PI / 180)));

                if ((x + changeX < xlength && x + changeX > 0) && (y + changeY < ylength && y + changeY > 0))
                    result[x + changeX][y + changeY] = true;
            }
        }


        return result;

    }
}
