package com.angelod.ind2.nnlib;

public class Calculations {

    /**
     * Sigmoid "squishification" function.
     * @param input
     * @return a value from 0.0 to 1.0
     */
    public static double sigmoid(double input){
        return 1/(1+Math.pow(Math.E, -input));
    }
}
