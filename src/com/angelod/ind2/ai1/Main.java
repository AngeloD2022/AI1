package com.angelod.ind2.ai1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main {

    public static void main(String[] args) {
        // w

//        Path p = new Path(100,100);
//        Character training = new TrainingCharacter(p,10,10);
//        AIPanel aipanel = new AIPanel(p,training,null);
//        JFrame frame = new JFrame("AI-1");
//        frame.setSize(779, 779);
//
//        frame.add(aipanel);
////        frame.pack();
//        frame.setVisible(true);
//        frame.setResizable(false);
//
//        frame.addKeyListener(new KeyListener() {
//
//            @Override
//            public void keyTyped(KeyEvent e) {
//
//            }
//
//            @Override
//            public void keyPressed(KeyEvent e) {
//
//                int w = e.getKeyCode() == 87?1:0;
//                int a = e.getKeyCode() == 65?1:0;
//                int s = e.getKeyCode() == 83?1:0;
//                int d = e.getKeyCode() == 68?1:0;
//
//                System.out.println(w+""+a+""+s+""+d);
//                training.move(w,a,s,d);
//
//            }
//
//            @Override
//            public void keyReleased(KeyEvent e) {
//
//            }
//
//        });
        new MainWindow().setVisible(true);


    }
//    void drawGrid(Graphics g) {
//        g.setColor(Color.black);
//        int w = this.getWidth();
//        int h = this.getHeight();
//
//        for (int i = 0; i < xSegs; i++) {
//            int px = (i * w) / xSegs;
//            // If this is the middle value.
//            if (xSegs % 2 > 0 && i == ((xSegs - 1) / 2)) {
//                g.fillRect(px - (middleAxisLineWeight / 2), 0, middleAxisLineWeight, h);
//            } else
//                g.drawLine(px, 0, px, this.getHeight());
//        }
//
//        for (int i = 0; i < ySegs; i++) {
//            int py = (i * h) / ySegs;
//            if (ySegs % 2 > 0 && i == ((ySegs - 1) / 2)) {
//                g.fillRect(0, py - (middleAxisLineWeight / 2), w, middleAxisLineWeight);
//            } else
//                g.drawLine(0, py, w, py);
//        }
//
//    }
}
