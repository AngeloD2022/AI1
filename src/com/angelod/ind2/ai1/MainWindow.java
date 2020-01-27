package com.angelod.ind2.ai1;

import javax.swing.*;

public class MainWindow extends JFrame {
    AIPanelV2 aipanel;
    Path path;

    public MainWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(779, 779);
        path = new Path(100, 100, 779, 779);

        aipanel = new AIPanelV2();
        this.add(aipanel);
        addKeyListener(aipanel.new CharacterKeyInput());


    }

}
