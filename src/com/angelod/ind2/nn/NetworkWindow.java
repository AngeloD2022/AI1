package com.angelod.ind2.nn;

import javax.swing.*;

public class NetworkWindow extends JFrame {
    NetworkViewPanel panel;

    public NetworkWindow(ENMS sys) {
        panel = new NetworkViewPanel(sys);
        add(panel);
    }

}
