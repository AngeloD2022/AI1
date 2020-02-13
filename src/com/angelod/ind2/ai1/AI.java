package com.angelod.ind2.ai1;

import com.angelod.ind2.ai1.path.Path2;
import com.angelod.ind2.nn.NeuralNetwork;

public class AI extends Character {

    private NeuralNetwork nNet;

    /**
     * @param path the path for the character to be aware of
     */
    public AI(Path2 path, int x, int y, NeuralNetwork network) {
        super(path, x, y);
        nNet = network;
    }


}
