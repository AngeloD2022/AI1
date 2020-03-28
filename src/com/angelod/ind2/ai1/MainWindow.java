package com.angelod.ind2.ai1;

import com.angelod.ind2.ai1.path.Path2;
import com.angelod.ind2.ai1.nn.NetworkWindow;
import com.angelod.ind2.ai1.nn.NeuralNetwork;
import com.angelod.ind2.ai1.path.Vector2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    AIPanelV2 aipanel;
    Path2 path;
    NetworkWindow netwin;
    private Vector2[] perpendiculars;
    private Vector2[] deltas;
    private Vector2[] vectors;
    double pathLength;

    Timer updateTmr;

    public MainWindow() {

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(779, 779);
//        path = new Path2(100, 100, 779, 779);
        updateTmr = new Timer(20, new UpdateCharacterListener());

        aipanel = new AIPanelV2();
        this.add(aipanel);
        aipanel.getPath().getPathTiles()[10][10] = true;
        JButton button = new JButton("Start AI");
        add(button, BorderLayout.SOUTH);
        button.addActionListener(e -> {
            StartAI();
        });

        addKeyListener(aipanel.new CharacterKeyInput());
    }

    public void StartAI() {
        path = aipanel.getPath();
        vectors = aipanel.getPathVectors();
        perpendiculars = computePerpendicularVectors(vectors);
        deltas = computePathDeltas();
        pathLength = computePathLength();
//        AI character = new AI(path, 10, 10, vectors, perpendiculars, deltas, pathLength);
        netwin = new NetworkWindow();
        netwin.setVisible(true);

        updateTmr.start();
        for (int i = 0; i < 14; i++) {
            aipanel.addCharacter(new AI(path, 10, 10, vectors, perpendiculars, deltas, pathLength));
        }
    }

    class UpdateCharacterListener implements ActionListener {
//        int time = 0;

        @Override
        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < aipanel.getAllCharacters().size(); i++) {

                if (!aipanel.getAllCharacters().get(i).hasFailed()) {
                    aipanel.getAllCharacters().get(i).update(1);
//                    time++;
                }
                //aipanel.getAllCharacters().add(new AI(path, 10, 10, new NeuralNetwork(aipanel.getAllCharacters().get(i).getNetwork(), .01), vectors, perpendiculars, deltas, pathLength));
            }

            boolean allHaveFailed = false;
            for (int i = 0; i < aipanel.getAllCharacters().size(); i++) {
                allHaveFailed = aipanel.getAllCharacters().get(i).hasFailed();
                if (!allHaveFailed) {
                    allHaveFailed = false;
                    break;
                }
            }

            if (allHaveFailed) {
                NeuralNetwork x = getBest();
                netwin.setNetwork(x);
                aipanel.getAllCharacters().clear();
                for (int i = 0; i < 100; i++) {
                    aipanel.addCharacter(new AI(path, 10, 10, new NeuralNetwork(x, 1.75), vectors, perpendiculars, deltas, pathLength));
                }
            }

            aipanel.repaint();
        }

        NeuralNetwork getBest() {
            int mostProgressIndex = 0;
            double mostProgress = 0;

            for (int i = 0; i < aipanel.getAllCharacters().size(); i++) {
                double x = aipanel.getAllCharacters().get(i).computeTotalProgress();
                if (x > mostProgress) {
                    mostProgress = x;
                    mostProgressIndex = i;
                }
            }
            return aipanel.getAllCharacters().get(mostProgressIndex).getNetwork();
        }

    }

    public double computePathLength() {
        double x = 0;
        for (int i = 0; i < deltas.length; i++) {
            x += deltas[i].length();
        }
        return x;
    }

    private Vector2[] computePathDeltas() {
        Vector2[] v = new Vector2[vectors.length - 1];
        for (int i = 1; i < v.length + 1; i++) {
            v[i - 1] = vectors[i].subtractNew(vectors[i - 1]);
        }
        return v;
    }

    private Vector2[] computePerpendicularVectors(Vector2[] vectors) {
        Vector2[] result = new Vector2[vectors.length - 1];
//        Vector2[] result = new Vector2[vectors.length];
        for (int i = 0; i < result.length; i++) {
//            result[i] = new Vector2(
//                    (vectors[i + 1].getY() - vectors[i].getY()) /
//                            new Vector2(vectors[i + 1].getX(), vectors[i + 1].getY())
//                                    .subtractNew(
//                                            new Vector2(vectors[i].getX(),
//                                                    vectors[i].getY()))
//                                    .length()
//                    , (-1 * vectors[i + 1].getX() - vectors[i].getX()) /
//                    new Vector2(vectors[i + 1].getX(), vectors[i + 1].getY())
//                            .subtractNew(new Vector2(vectors[i].getX(),
//                                    vectors[i].getY()))
//                            .length()
//            );
            Vector2 lineDirection = vectors[i + 1].subtractNew(vectors[i]).normalize();
            Vector2 perp = new Vector2(
                    lineDirection.getY(), -lineDirection.getX()
            );
            result[i] = perp;
        }
        return result;
    }


}
