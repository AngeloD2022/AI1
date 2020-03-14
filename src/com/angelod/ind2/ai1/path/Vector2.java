package com.angelod.ind2.ai1.path;

public class Vector2 {

    private double x;
    private double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double dotProduct(Vector2 b) {

        // New Values
        double result;

        // Matrix multiplication
        result = (x * b.x) + (y * b.y);

        return result;
    }

    public double crossProduct(Vector2 b) {
        return x * b.y - y * b.x;
    }

    /*public void add(Vector2 b) {
        x += b.x;
        y += b.y;
    }

    public void subtract(Vector2 b) {
        x -= b.x;
        y -= b.y;
    }*/
    public Vector2 subtractNew(Vector2 b) {
        // x -= b.x;
        //y -= b.y;
        return new Vector2(x - b.x, y - b.y);
    }
/*
    public void add(double b) {
        x += b;
        y += b;
    }

    public void subtract(double b) {
        x -= b;
        y -= b;
    }

    public void multiply(double b) {
        x *= b;
        y *= b;

 */


    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double lengthSquared() {
        return x * x + y * y;
    }

    /*    public void normalize() {
            double l = length();
            x /= l;
            y /= l;
        }

        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }
    */
    public double getX() {
        return x;
    }

    public Vector2 normalize() {
        double l = length();
        return new Vector2(x / l, y / l);
    }

    public double getY() {
        return y;
    }
}
