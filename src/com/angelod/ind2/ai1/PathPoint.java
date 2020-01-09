package com.angelod.ind2.ai1;

public class PathPoint<ntype> {
    private ntype x;
    private ntype y;
    private int id;

    public PathPoint(ntype x, ntype y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ntype getX() {
        return x;
    }

    public ntype getY() {
        return y;
    }

    public int getId() {
        return id;
    }

    public boolean isAdjacentTo(PathPoint<ntype> point) {
        double px = (double) point.getX();
        double py = (double) point.getY();
        double x = (double) this.x;
        double y = (double) this.y;

        // If a tile is adjacent to this tile's edges.
        if ((px == x - 1 || px == x + 1) | (py == y - 1 || py == y + 1)) {
            return true;
        }
        return false;
    }
}
