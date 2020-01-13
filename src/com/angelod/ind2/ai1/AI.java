package com.angelod.ind2.ai1;

import com.angelod.ind2.nnlib.NeuralNetwork;

public class AI extends Character {

    private NeuralNetwork nNet;

    /**
     * @param path the path for the character to be aware of
     */
    public AI(Path path, NeuralNetwork network) {
        super(path);
        nNet = network;
    }

    
}
