package com.angelod.ind2.ai1;

public class Vector {

    int x1;
    int y1;
    int x2;
    int y2;


    public Vector(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double getLength() {
        int a = Math.abs(x2 - x1);
        int b = Math.abs(y2 - y1);
        double c = Math.sqrt((a * a) + (b * b));
        return c;
    }

}
