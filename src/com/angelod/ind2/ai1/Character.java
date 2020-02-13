package com.angelod.ind2.ai1;

import com.angelod.ind2.ai1.path.Path2;

import java.awt.*;

public abstract class Character {

    private Path2 path;
    private double x;
    private double y;
    private double rotDegrees;
    private double time;
    private double speedx;
    private double speedy;
    private double speedPerSecond = 1;

    private int[] wasd;


    /**
     * @param path the path for the character to be aware of
     */
    public Character(Path2 path, int x, int y) {
        this.path = path;
        this.x = x * (779 / 100);
        this.y = y * (779 / 100);
        wasd = new int[]{0, 0, 0, 0};

    }


    public void paint(Graphics g) {
        Color c = g.getColor();

        g.setColor(Color.blue);

        g.fillArc((int) x - 5, (int) y - 5, 10, 10, 0, 360);

        g.setColor(Color.red);

        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos((rotDegrees - 90) * (Math.PI / 180)))), (int) (y - (450 * Math.sin((rotDegrees - 90) * (Math.PI / 180)))));
        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos((rotDegrees - 45) * (Math.PI / 180)))), (int) (y - (450 * Math.sin((rotDegrees - 45) * (Math.PI / 180)))));
        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos(rotDegrees * (Math.PI / 180)))), (int) (y - (450 * Math.sin(rotDegrees * (Math.PI / 180)))));
        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos((rotDegrees + 45) * (Math.PI / 180)))), (int) (y - (450 * Math.sin((rotDegrees + 45) * (Math.PI / 180)))));
        g.drawLine((int) x, (int) y, (int) (x + (450 * Math.cos((rotDegrees + 90) * (Math.PI / 180)))), (int) (y - (450 * Math.sin((rotDegrees + 90) * (Math.PI / 180)))));

        g.setColor(c);

    }


    public void pressMove(int w, int a, int s, int d) {
        //double speed = 2*Math.cos(time * (Math.PI/180));
        wasd[0] |= w; // Accel
        wasd[1] |= a; // +rot
        wasd[2] |= s; // -Accel
        wasd[3] |= d; // -rot
    }

    public void releaseMove(int w, int a, int s, int d) {
        //double speed = 2*Math.cos(time * (Math.PI/180));
        wasd[0] &= (~w & 1);
        wasd[1] &= (~a & 1);
        wasd[2] &= (~s & 1);
        wasd[3] &= (~d & 1);
    }

    double maxSpeed = 100; // Pixels per second
    double speed = 0;
    double maxRotSpeed = 50;
    double rotSpeed = 0;

    public void update(int time) {


        int rotAccel = (wasd[1] - wasd[3]);

        int acceleration = wasd[0] - wasd[2];
//        System.out.println(acceleration);

        if (acceleration != 0 && Math.abs(speed + acceleration) < maxSpeed) {
            speed += acceleration;
        }

        if (rotAccel != 0 && Math.abs(rotSpeed + rotAccel) < maxRotSpeed) {
            rotSpeed += rotAccel;
        }

        if (rotAccel == 0) {
            if (rotSpeed > 0) {
                rotSpeed -= 1;
            } else if (rotSpeed < 0) {
                rotSpeed += 1;
            }
        }
        if (acceleration == 0) {
            if (speed > 0) {
                speed -= 1;
            } else if (speed < 0) {
                speed += 1;
            }
        }


        rotDegrees += rotSpeed / 25;
        double newx = x + (speed / 20) * Math.cos(rotDegrees * (Math.PI / 180));
        double newy = y - (speed / 20) * Math.sin(rotDegrees * (Math.PI / 180));
        if (path.validMove((int) newx, (int) newy)) {
            x = newx;
            y = newy;
        }
        this.time = time;

        dump(distanceFromPathWalls());
    }

    void dump(double[] a) {
        for (double x : a) {
            System.out.print(x + " ");
        }
        System.out.println();
    }


    /**
     * Returns five wall distance measurements in pixels from -90 deg to +90 deg @ 45 deg increments relative to character rotation.
     *
     * @return
     */
    public double[] distanceFromPathWalls() {


        double[] results = new double[5];

        int iterations = 0;

        for (int theta = -90; theta <= 90; theta += 45) {

            double distance = 0;
            double radiusx = 0;
            double radiusy = 0;

            // Character's rotation + relative measuring angle increment.
            double directionDegrees = rotDegrees + theta;
            double cosineFunction = Math.cos(directionDegrees * (Math.PI / 180.0));
            double sineFunction = -Math.sin(directionDegrees * (Math.PI / 180.0));

            while (path.validMove(x + radiusx, y + radiusy)) {
                // Increment measuring radius by 3 pixels.
                distance += 1;

                // radiusx and radiusy are the coordinates for the current measuring ray endpoint for directionDegrees.
                radiusx = distance * cosineFunction;
                radiusy = distance * sineFunction;

                if (distance >= 450)
                    break;
            }
            results[iterations] = distance;
            iterations++;
        }

        return results;
    }


}
