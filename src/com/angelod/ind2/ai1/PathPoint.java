package com.angelod.ind2.ai1;

public class PathPoint {
    private int x;
    private int y;
    private int id;

    public PathPoint(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public boolean isAdjacentTo(PathPoint point) {
        int px = point.getX();
        int py = point.getY();
        int x = this.x;
        int y = this.y;

        // If a tile is adjacent to this tile's edges.
        if ((px == x - 1 || px == x + 1) | (py == y - 1 || py == y + 1)) {
            return true;
        }
        return false;
    }
}
