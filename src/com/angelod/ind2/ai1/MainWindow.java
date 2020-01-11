package com.angelod.ind2.ai1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    public JPanel panel1;
    public JPanel aiPanel;
    private AIPanel AIPanel1;
    private JButton clearbtn;
    private JButton toggletool;

    public MainWindow() {
        clearbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AIPanel1.path.clear();
                AIPanel1.repaint();
            }
        });
        toggletool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        aiPanel = new AIPanel();
    }
}
