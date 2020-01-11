package com.angelod.ind2.ai1;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame frame = new JFrame("AI-1");
        frame.setSize(600, 600);
        frame.setContentPane(new MainWindow().panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
