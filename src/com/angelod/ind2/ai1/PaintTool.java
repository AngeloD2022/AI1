package com.angelod.ind2.ai1;

public abstract class PaintTool {

    private String name;

    public PaintTool(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    abstract public boolean[][] doPaint(int x, int y, boolean[][] canvas, int radius);


}
