package com.angelod.ind2.ai1;

import com.angelod.ind2.ai1.path.Path2;
import com.angelod.ind2.nn.ENMS;
import com.angelod.ind2.nn.NetworkWindow;
import com.angelod.ind2.nn.NeuralNetwork;
import sun.nio.ch.Net;

import javax.swing.*;

public class MainWindow extends JFrame {
    AIPanelV2 aipanel;
    Path2 path;

    public MainWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(779, 779);
        path = new Path2(100, 100, 779, 779);
        AI character = new AI(path, 100, 100);
        ENMS netsys = new ENMS(character, new NeuralNetwork(new int[]{5, 7, 7, 3, 9}));

        NetworkWindow netwin = new NetworkWindow(netsys);
        netwin.setVisible(true);
        netwin.setSize(600, 400);
        netwin.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        aipanel = new AIPanelV2(character);
        this.add(aipanel);
        addKeyListener(aipanel.new CharacterKeyInput());


    }

}
