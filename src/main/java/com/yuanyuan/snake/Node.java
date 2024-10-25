package com.yuanyuan.snake;

import java.util.Objects;
import java.util.Random;

public class Node {
    private int x;
    private int y;

    public Node() {
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void random() {
        Random r = new Random();
        this.x = r.nextInt(40);
        this.y = r.nextInt(40);
    }

    public final int hashCode() {
        return Objects.hashCode(x) ^ Objects.hashCode(y);
    }

    public final boolean equals(Object o) {
        if (o == this)
            return true;

        return o instanceof Node
                && Objects.equals(x, ((Node) o).getX())
                && Objects.equals(y, ((Node) o).getY());
    }

    public String toString() {
        return "{x=" + x + ", y=" + y + "}";
    }
}
