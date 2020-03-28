package com.angelod.ind2.ai1;

import com.angelod.ind2.ai1.path.Path2;
import com.angelod.ind2.ai1.path.Vector2;
import com.angelod.ind2.ai1.nn.NeuralNetwork;

import java.awt.*;

public class AI extends Character {

    private NeuralNetwork network;
    //    private double pathProgress;
    private boolean failed;
    private Vector2[] vectors;
    private Vector2[] perpendiculars;
    //    private double totalPathLength;
    private Vector2[] pathDeltas;


    /**
     * @param path the path for the character to be aware of
     */
    public AI(Path2 path, int x, int y, Vector2[] pathVectors, Vector2[] perpendiculars, Vector2[] pathDeltas, double pathLength) {
        super(path, x, y);
        vectors = pathVectors;
        this.perpendiculars = perpendiculars;
        network = new NeuralNetwork(new int[]{6, 8, 4});
//        this.totalPathLength = pathLength;
        this.pathDeltas = pathDeltas;
    }

    public AI(Path2 path, int x, int y, NeuralNetwork network, Vector2[] pathVectors, Vector2[] perpendiculars, Vector2[] pathDeltas, double pathLength) {
        super(path, x, y);
        this.network = network;
        vectors = pathVectors;
        this.perpendiculars = perpendiculars;
//        this.totalPathLength = pathLength;
        this.pathDeltas = pathDeltas;
    }

    public double computeTotalProgress() {
        int pathIx = getPathVectorIndex(super.x, super.y);

        Vector2 mypath = vectors[pathIx];
        double dist = 0;
        for (int i = 0; i < pathIx; i++) {
            dist += pathDeltas[i].length();
        }

        // (P - S dot E - S)
        Vector2 lineP = new Vector2(super.x, super.y).subtractNew(mypath);
        Vector2 eMinusS = vectors[pathIx + 1].subtractNew(vectors[pathIx]);
        double lengthOnTrl = lineP.dotProduct(eMinusS.normalize());
        double progress = lengthOnTrl + dist;
//        double progress = lengthOnTrl / eMinusS.length();
        return progress;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public NeuralNetwork getNetwork() {
        return network;
    }

    @Override
    public void paint(Graphics g) {

        Color c = g.getColor();

        if (failed) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(Color.blue);
        }

        g.fillArc((int) x - 5, (int) y - 5, 10, 10, 0, 360);

        g.setColor(Color.red);

//        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos((rotDegrees - 90) * (Math.PI / 180)))), (int) (y - (450 * Math.sin((rotDegrees - 90) * (Math.PI / 180)))));
//        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos((rotDegrees - 45) * (Math.PI / 180)))), (int) (y - (450 * Math.sin((rotDegrees - 45) * (Math.PI / 180)))));
        g.drawLine((int) x, (int) y, (int) (x + (50 * Math.cos(rotDegrees * (Math.PI / 180)))), (int) (y - (50 * Math.sin(rotDegrees * (Math.PI / 180)))));
//        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos((rotDegrees + 45) * (Math.PI / 180)))), (int) (y - (450 * Math.sin((rotDegrees + 45) * (Math.PI / 180)))));
//        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos((rotDegrees + 90) * (Math.PI / 180)))), (int) (y - (450 * Math.sin((rotDegrees + 90) * (Math.PI / 180)))));

        g.setColor(c);
    }

    @Override
    public void update(double time) {
        double[] wall = super.distanceFromPathWalls();
        double prog1 = computeTotalProgress();
        for (int i = 0; i < wall.length; i++) {

            wall[i] /= 450.0;
        }
        double[] results = (network.runNetwork(new double[]{wall[0], wall[1], wall[2], wall[3], wall[4], super.speed / super.maxSpeed}));
        forward = results[0] - results[2];
        left = results[1] - results[3];


        //double rotAccel = left;

        double acceleration = forward;
//        System.out.println(acceleration);

        if (acceleration != 0 && Math.abs(speed + acceleration) < maxSpeed) {
            speed += acceleration;
        }

        /*if (rotAccel != 0 && Math.abs(rotSpeed + rotAccel) < maxRotSpeed) {
            rotSpeed += rotAccel;
        }

        if (rotAccel == 0) {
            if (rotSpeed > 0) {
                rotSpeed -= 1;
            } else if (rotSpeed < 0) {
                rotSpeed += 1;
            }
        }*/
        rotSpeed = left;
        if (acceleration == 0) {
            if (speed > 0) {
                speed -= 1;
            } else if (speed < 0) {
                speed += 1;
            }
        }


        rotDegrees += rotSpeed * time * 45;
        double newx = x + (speed) * time * Math.cos(rotDegrees * (Math.PI / 180));
        double newy = y - (speed) * time * Math.sin(rotDegrees * (Math.PI / 180));
        if (path.validMove(newx, newy)) {
            x = newx;
            y = newy;
            if (computeTotalProgress() - prog1 <= 0) {
                setFailed(true);
            }
        } else {
            setFailed(true);
        }

    }

    public int getPathVectorIndex(double x, double y) {

        double closestDist = 0;
        int closestIx = 0;
        for (int i = 0; i < vectors.length - 1; i++) {
            double dist = Math.abs(distanceToSegment(i));
            if (i == 0) {
//                closestIx = 0;
                closestDist = dist;
            } else if (closestDist > dist) {
                closestIx = i;
                closestDist = dist;
            }
        }
        return closestIx;
    }

    public double distanceToSegment(int index) {
        //Progress = player - startPoint dotted with endPoint-startPoint
        Vector2 p = new Vector2(x, y);
        System.out.println("INDEX" + index);
        double n = Math.abs(p.subtractNew(vectors[index]).dotProduct(vectors[index + 1].subtractNew(vectors[index]).normalize()) / vectors[index + 1].subtractNew(vectors[index]).length());

        if (n >= 0 && n <= 1) // If in-between two points
            return p.subtractNew(vectors[index]).dotProduct(perpendiculars[index]);
        else // if the player is at the beginning of a path point
            return p.subtractNew(vectors[index]).length();
    }

    public boolean hasFailed() {
        return failed;
    }
}
