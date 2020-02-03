package com.angelod.ind2.nn;

import java.sql.SQLOutput;

public class NeuralNetwork {

    private Layer[] network;

    public NeuralNetwork(int[] layers) {
        network = new Layer[layers.length - 1];
        for (int i = 0; i < layers.length - 1; i++) {
            //One layer's output is next layer's input.
            network[i] = new Layer(layers[i], layers[i + 1]);
        }
    }

    public double[] runNetwork(double[] inputs) {
        double[] out = new double[inputs.length];

        for (int i = 0; i < network.length; i++) {
            System.out.println();
            System.out.println("Layer " + i);
            if (i == 0)
                out = network[i].results(inputs);
            else
                out = network[i].results(out);

        }

        return out;
    }


}
