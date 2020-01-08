package com.angelod.ind2.ai1;

import javax.swing.*;
import java.awt.*;

public class AIPanel extends JPanel {
    public AIPanel() {
        super();
    }

    int xSegs = 31;
    int ySegs = 31;
    int middleAxisLineWeight = 2;

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

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawGrid(g);
    }


}
