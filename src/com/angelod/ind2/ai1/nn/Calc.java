package com.angelod.ind2.ai1.nn;

public class Calc {
    public static double sigmoid(double x) {
        return (1 / (1 + Math.pow(Math.E, (-1 * x))));
    }
}
