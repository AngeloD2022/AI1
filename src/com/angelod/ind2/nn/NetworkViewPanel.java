package com.angelod.ind2.nn;

import javax.swing.*;
import java.awt.*;

public class NetworkViewPanel extends JPanel {

    ENMS sys;

    public NetworkViewPanel(ENMS sys) {
        this.sys = sys;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.blue);


        double layerSpacing = (this.getWidth()) / (2.0 + sys.getNetworkLayerCounts().length);
        for (int i = 0; i < sys.getNetworkLayerCounts().length; i++) {
            double neuronPadding = (getHeight() / (2.0 + sys.getNetworkLayerCounts()[i]));
            double neuronRadius = neuronPadding / 2.0;

            for (int j = 0; j < sys.getNetworkLayerCounts()[i]; j++) {

                g.drawArc((int) ((layerSpacing * (i + 1))),
                        (int) (neuronPadding * (j + 1) + (neuronRadius / 2)),
                        (int) neuronRadius, (int) neuronRadius,
                        0, 360);
                if (i + 1 < sys.getNetworkLayerCounts().length)
                    for (int n = 0; n < sys.getNetworkLayerCounts()[i + 1]; n++) {
                        double nextNeuronPadding = (getHeight() / (2.0 + sys.getNetworkLayerCounts()[i + 1]));
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
}
