package com.angelod.ind2.nnlib;

public class Calculus {

    /**
     * Sigmoid "squishification" function
     * @param x
     * @return a value from 0.0 to 1.0
     */
    public static double sigmoid(double x){
        return (1/(1+Math.pow(Math.E, (-1*x))));
    }


    /**
     * Cost function
     * @param garbage
     * @param value
     * @return
     */
    public static double cost(double[] garbage, double[] value){
        double result = 0.00;
        //x are the values that are utter trash
        for(int i = 0; i < garbage.length; i++){
            result += Math.pow((garbage[i] - value[i]), 2);
        }

        return result;

    }
}