package com.angelod.ind2.nn;

public class ENMSMoveEvent {

    private double[] inputs;
    private double[] outputs;

    public ENMSMoveEvent(double[] inputs, double[] outputs) {
        this.inputs = inputs;
        this.outputs = outputs;
    }

    public double[] getInputs() {
        return inputs;
    }

    public double[] getOutputs() {
        return outputs;
    }
}
