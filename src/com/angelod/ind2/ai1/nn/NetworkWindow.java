package com.angelod.ind2.ai1.nn;

import javax.swing.*;

public class NetworkWindow extends JFrame {
    NetworkViewPanel panel;

    public NetworkWindow() {
        panel = new NetworkViewPanel();
        add(panel);
    }

    public void setNetwork(NeuralNetwork network) {
        panel.setNeuralNetwork(network);
    }

}
