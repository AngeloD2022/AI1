package com.angelod.ind2.ai1;

import com.angelod.ind2.nn.NeuralNetwork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    //new MainWindow().setVisible(true);
    static int iterations = 0;

    public static void main(String[] args) {
        new MainWindow().setVisible(true);

    }

    public static void doNNTest() {
        int[] sequence = new int[]{4, 8, 8, 3};


        NeuralNetwork nn = new NeuralNetwork(sequence);


        System.out.println("Enter comma-separated inputs.");
        Scanner s = new Scanner(System.in);
        String[] in = s.nextLine().split(",");
        double[] inputs = new double[in.length];
        for (int i = 0; i < in.length; i++) {
            inputs[i] = Double.parseDouble(in[i]);
        }


        double[] results = nn.runNetwork(inputs);
        iterations++;

        ////////
        System.out.println();
        System.out.println("----- Iteration " + iterations + " -----");

        System.out.println("in");
        dumpArray(inputs);
        System.out.println("out");
        dumpArray(results);
        System.out.println("------------------------------------");
        /////////

        while (true) {
            System.out.println("Enter comma-separated inputs.");
            in = s.nextLine().split(",");
            for (int i = 0; i < in.length; i++) {
                inputs[i] = Double.parseDouble(in[i]);
            }

            results = nn.runNetwork(inputs);
            iterations++;

            ////////
            System.out.println();
            System.out.println("----- Iteration " + iterations + " -----");
            System.out.println("in");
            dumpArray(inputs);
            System.out.println("out");
            dumpArray(results);
            System.out.println("------------------------------------");
            System.out.println("\n\n");
            /////////
        }
    }

    public static void dumpArray(double[] in) {
        for (int i = 0; i < in.length; i++) {
            System.out.print(in[i]);
            System.out.print("   ");
        }
        System.out.println();
    }
}
