package com.angelod.ind2.ai1;

import java.awt.*;

public abstract class Character {

    private Path path;
    private int x;
    private int y;
    private int rotDegrees;
    private int speed;


    /**
     * @param path the path for the character to be aware of
     */
    public Character(Path path) {
        this.path = path;
    }


    public void paint(Graphics g) {
        Color c = g.getColor();

        g.setColor(Color.blue);

        g.fillArc(x - 5, y - 5, 10, 10, 0, 360);

        g.setColor(Color.red);

        g.drawLine(x, y, (int) (x + (20 * Math.cos(rotDegrees * (Math.PI / 180)))), (int) (y - (20 * Math.sin(rotDegrees * (Math.PI / 180)))));

        g.setColor(c);

    }

    public void move(int w, int a, int s, int d) {
        if (path.validMove(x, y, w, a, s, d)) {
            x = x + d - a;
            y = y + s - w;
        }
    }


}
