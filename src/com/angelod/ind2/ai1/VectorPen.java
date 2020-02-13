package com.angelod.ind2.ai1;

import com.angelod.ind2.ai1.path.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class VectorPen extends PaintTool {
    private ArrayList<Vector2> pathVectors;
    private boolean createMode;
    PaintTool brush;

    public VectorPen() {
        this.pathVectors = new ArrayList<Vector2>();
        createMode = true;
        brush = new Paintbrush();
    }


    @Override
    public boolean[][] doPaint(double mousex, double mousey, boolean[][] canvas, int radius) {
        boolean[][] result = new boolean[canvas.length][canvas[0].length];

        pathVectors.add(new Vector2(mousex, mousey));


        for (int i = 1; i < pathVectors.size(); i++) {

            double dx = pathVectors.get(i).getX() - pathVectors.get(i - 1).getX();
            double dy = pathVectors.get(i).getY() - pathVectors.get(i - 1).getY();
            double dt = radius / Math.sqrt(dx * dx + dy * dy);

            double t = 0;
            while (t < 1) {
                result = brush.doPaint(((1 - t) * pathVectors.get(i).getX() + (t) * pathVectors.get(i - 1).getX())
                        , ((1 - t) * pathVectors.get(i).getY() + (t) * pathVectors.get(i - 1).getY()),
                        canvas,
                        radius);
                t += dt;
            }

        }
        return result;
    }

    public ArrayList<Vector2> getPathVectors() {
        return pathVectors;
    }

    public void draw(Graphics g) {

        for (int i = 1; i < pathVectors.size(); i++) {
            g.setColor(Color.magenta);
            g.drawLine((int) pathVectors.get(i - 1).getX(), (int) pathVectors.get(i - 1).getY(), (int) pathVectors.get(i).getX(), (int) pathVectors.get(i).getY());
            g.setColor(Color.green);
            g.drawRect((int) pathVectors.get(i - 1).getX() - 3, (int) pathVectors.get(i - 1).getY() - 3, 6, 6);
        }

    }

    public void setCreateMode(boolean createMode) {
        this.createMode = createMode;
    }
}
