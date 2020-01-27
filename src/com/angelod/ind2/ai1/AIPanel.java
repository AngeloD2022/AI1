package com.angelod.ind2.ai1;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AIPanel extends JPanel {

    int xSegs = 100;
    int ySegs = 100;
    Path path;
    int x1;
    int y1;
    PaintTool selectedTool;
    PaintTool eraser;
    PaintTool brush;

    Character ai;
    Character training;


    public AIPanel(Path path, Character training, Character AI) {
        super();

        this.path = path;
        brush = new Paintbrush("paintbrush");
        selectedTool = brush;

        this.path = new Path(xSegs, ySegs, getWidth(), getHeight());
        this.training = training;
        this.ai = AI;
        // Events
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //path.toggleTile(e.getX(),e.getY());

                repaint();
                System.out.println("CLICK");

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //path.toggleTile(e.getX(), e.getY());
                x1 = e.getX() / (getWidth() / xSegs);
                y1 = e.getY() / (getWidth() / ySegs);
                System.out.println("PRESS");
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x2 = e.getX() / (getWidth() / xSegs);
                int y2 = e.getY() / (getHeight() / ySegs);
                if (x2 != x1 || y2 != y1) {
//                    path.toggleTile(e.getX(), e.getY());
                    path.setPathTiles(selectedTool.doPaint(x2, y2, path.getPathTiles(), 2));
                    x1 = e.getX() / (getWidth() / xSegs);
                    y1 = e.getY() / (getHeight() / ySegs);
                }
                repaint();
                System.out.println("DRAG:" + e.getX() + "," + e.getXOnScreen());
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        this.setSize(779, 779);

    }





    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        path.drawPath(g);
        System.out.println("REPAINT");
        training.paint(g);
    }


}
