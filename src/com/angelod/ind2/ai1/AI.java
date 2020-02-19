package com.angelod.ind2.ai1;

import com.angelod.ind2.ai1.path.Path2;
import com.angelod.ind2.nn.NeuralNetwork;

public class AI extends Character {

    private double pathProgress;

    /**
     * @param path the path for the character to be aware of
     */
    public AI(Path2 path, int x, int y) {
        super(path, x, y);
    }


}
