package com.angelod.ind2.ai1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AIPanel extends JPanel {

    int xSegs = 100;
    int ySegs = 100;
    int w = this.getWidth();
    int h = this.getHeight();
    int middleAxisLineWeight = 2;
    Path path;
    int x1;
    int y1;
    PaintTool selectedTool;
    PaintTool eraser;
    PaintTool brush;


    public AIPanel() {
        super();
        brush = new Paintbrush("paintbrush");
        selectedTool = brush;
        this.path = new Path(xSegs, ySegs);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //path.toggleTile(e.getX(),e.getY());

                repaint();
                System.out.println("CLICK");

            }

            @Override
            public void mousePressed(MouseEvent e) {
                path.toggleTile(e.getX(), e.getY());
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
    }

    public void repaint() {
        super.repaint();
    }

    public int getHeight() {
        return super.getHeight();
    }

    public int getWidth() {
        return super.getWidth();
    }

    void drawGrid(Graphics g) {
        g.setColor(Color.black);
        int w = this.getWidth();
        int h = this.getHeight();

        for (int i = 0; i < xSegs; i++) {
            int px = (i * w) / xSegs;
            // If this is the middle value.
            if (xSegs % 2 > 0 && i == ((xSegs - 1) / 2)) {
                g.fillRect(px - (middleAxisLineWeight / 2), 0, middleAxisLineWeight, h);
            } else
                g.drawLine(px, 0, px, this.getHeight());
        }

        for (int i = 0; i < ySegs; i++) {
            int py = (i * h) / ySegs;
            if (ySegs % 2 > 0 && i == ((ySegs - 1) / 2)) {
                g.fillRect(0, py - (middleAxisLineWeight / 2), w, middleAxisLineWeight);
            } else
                g.drawLine(0, py, w, py);
        }

    }

    void startDesigner(){
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.red);
        path.drawPath(1, 0, 0, g, this.getWidth(), this.getHeight());
        //drawGrid(g);
        System.out.println("REPAINT");
        g.drawString(this.getWidth() + ", " + this.getHeight(), 50, 50);

    }


}
