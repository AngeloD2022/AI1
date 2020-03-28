package com.angelod.ind2.ai1.nn;

import javax.swing.*;
import java.awt.*;

public class NetworkViewPanel extends JPanel {

    NeuralNetwork neuralNetwork;

    public NetworkViewPanel() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.blue);


        double layerSpacing = (this.getWidth()) / (2.0 + neuralNetwork.getNetworkLayerCounts().length);
        for (int i = 0; i < neuralNetwork.getNetworkLayerCounts().length; i++) {
            double neuronPadding = (getHeight() / (2.0 + neuralNetwork.getNetworkLayerCounts()[i]));
            double neuronRadius = neuronPadding / 2.0;

            for (int j = 0; j < neuralNetwork.getNetworkLayerCounts()[i]; j++) {
                if (i > 0) {
                    g.setColor(Color.red);
                    double bias = Math.floor(neuralNetwork.getNetwork()[i - 1].getBiases()[j] * 1000) / 1000;
                    g.drawString("" + bias, (int) ((layerSpacing * (i + 1))), (int) (neuronPadding * (j + 1) + (neuronRadius / 2)));
                }
                g.setColor(Color.blue);
                g.drawArc((int) ((layerSpacing * (i + 1))),
                        (int) (neuronPadding * (j + 1) + (neuronRadius / 2)),
                        (int) neuronRadius, (int) neuronRadius,
                        0, 360);
                if (i + 1 < neuralNetwork.getNetworkLayerCounts().length)
                    for (int n = 0; n < neuralNetwork.getNetworkLayerCounts()[i + 1]; n++) {
                        double nextNeuronPadding = (getHeight() / (2.0 + neuralNetwork.getNetworkLayerCounts()[i + 1]));
                        double nextNeuronRadius = nextNeuronPadding / 2.0;
                        g.drawLine(
                                (int) ((layerSpacing * (i + 1)) + neuronRadius / 2),
                                (int) (neuronPadding * (j + 1) + (neuronRadius)),
                                (int) ((layerSpacing * (i + 2)) + nextNeuronRadius / 2),
                                (int) (nextNeuronPadding * (n + 1) + nextNeuronRadius)
                        );
                    }
            }

        }
    }

    public void setNeuralNetwork(NeuralNetwork neuralNetwork) {
        this.neuralNetwork = neuralNetwork;
        this.repaint();
    }
}
