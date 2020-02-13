package com.angelod.ind2.ai1;

import com.angelod.ind2.ai1.path.Path2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AIPanelV2 extends JPanel {

    private Character training;
    private int xSegments = 100;
    private int ySegments = 100;
    private int screenWidth;
    private int screenHeight;
    private Timer updateCharacterTimer;
    VectorPen selectedTool;
    Path2 path;


    public AIPanelV2() {
        screenWidth = 779;
        screenHeight = 779;

        updateCharacterTimer = new Timer(10, new UpdateCharacterListener());
        addMouseListener(new MousePressedListener());
        addMouseMotionListener(new MouseDragListener());
        selectedTool = new VectorPen();
        path = new Path2(xSegments, ySegments, screenWidth, screenHeight);
        training = new TrainingCharacter(path, 10, 10);
        updateCharacterTimer.start();

    }

    class CharacterKeyInput implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            int w = e.getKeyCode() == 87 ? 1 : 0;
            int a = e.getKeyCode() == 65 ? 1 : 0;
            int s = e.getKeyCode() == 83 ? 1 : 0;
            int d = e.getKeyCode() == 68 ? 1 : 0;

            System.out.println(w + "" + a + "" + s + "" + d + "||" + e.getKeyCode());
            training.pressMove(w, a, s, d);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int w = e.getKeyCode() == 87 ? 1 : 0;
            int a = e.getKeyCode() == 65 ? 1 : 0;
            int s = e.getKeyCode() == 83 ? 1 : 0;
            int d = e.getKeyCode() == 68 ? 1 : 0;

            System.out.println(w + "" + a + "" + s + "" + d + "||" + e.getKeyCode());
            training.releaseMove(w, a, s, d);
        }
    }

    class UpdateCharacterListener implements ActionListener {
        int time = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            training.update(time);
            repaint();
            time++;
        }

    }

    class MousePressedListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

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

        @Override
        public void mousePressed(MouseEvent e) {
//            int x = e.getX() / (screenWidth / xSegments);
//            int y = e.getY() / (screenHeight / ySegments);

            useTool(e.getX(), e.getY());
        }


    }

    class MouseDragListener implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {
//            int x = e.getX() / (screenWidth / xSegments);
//            int y = e.getY() / (screenHeight / ySegments);

            useTool(e.getX(), e.getY());
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

    void useTool(int x, int y) {
        path.setPathTiles(selectedTool.doPaint(x, y, path.getPathTiles(), 2));
        repaint();
    }

    void drawGrid(Graphics g) {
        g.setColor(Color.black);
        int middleAxisLineWeight = 2;
        int w = this.getWidth();
        int h = this.getHeight();

        for (int i = 0; i < xSegments; i++) {
            int px = (i * w) / xSegments;
            // If this is the middle value.
            if (xSegments % 2 > 0 && i == ((xSegments - 1) / 2)) {
                g.fillRect(px - (middleAxisLineWeight / 2), 0, middleAxisLineWeight, h);
            } else
                g.drawLine(px, 0, px, this.getHeight());
        }

        for (int i = 0; i < ySegments; i++) {
            int py = (i * h) / ySegments;
            if (ySegments % 2 > 0 && i == ((ySegments - 1) / 2)) {
                g.fillRect(0, py - (middleAxisLineWeight / 2), w, middleAxisLineWeight);
            } else
                g.drawLine(0, py, w, py);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        path.drawPath(g);
        training.paint(g);
        selectedTool.draw(g);
//        drawGrid(g);
    }
}
