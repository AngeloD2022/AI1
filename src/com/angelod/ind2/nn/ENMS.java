package com.angelod.ind2.nn;

import com.angelod.ind2.ai1.AI;
import com.angelod.ind2.ai1.Character;

public class ENMS {
    // Evolutionary Network Management System
    private AI character;
    private NeuralNetwork[] testingStack;
    private ENMSListener listener;


    public ENMS(AI character) {
        this.character = character;
    }

    public ENMS(AI character, NeuralNetwork init) {
        this.character = character;
        testingStack = new NeuralNetwork[10];
        testingStack[0] = init;

    }

    public int[] getNetworkLayerCounts() {
        return testingStack[0].getNetworkLayerCounts();
    }

    public void moveCharacter() {
        double[] wallMeasurements = character.distanceFromPathWalls();
    }

    public void addENMSListener(ENMSListener l) {
        listener = l;
    }


}
