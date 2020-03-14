package com.angelod.ind2.ai1.nn;


public class NeuralNetwork {

    private Layer[] network;

    private int[] networkLayerCounts;

    public NeuralNetwork(int[] layers) {
        networkLayerCounts = layers;
        network = new Layer[layers.length - 1];
        for (int i = 0; i < layers.length - 1; i++) {
            //One layer's output is next layer's input.
            network[i] = new Layer(layers[i], layers[i + 1]);
        }
    }

    public NeuralNetwork(NeuralNetwork network, double mutRate) {
        networkLayerCounts = network.getNetworkLayerCounts();
        this.network = new Layer[networkLayerCounts.length - 1];
        for (int i = 0; i < networkLayerCounts.length - 1; i++) {
            //One layer's output is next layer's input.
            this.network[i] = new Layer(network.getNetwork()[i], mutRate);
        }
    }

    public int[] getNetworkLayerCounts() {
        return networkLayerCounts;
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

    public Layer[] getNetwork() {
        return network;
    }
}
