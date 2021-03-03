package ru.thever4.pathfinder;

import java.util.Objects;

public class Point {

    private int X, Y;

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public Point() {}

    public int getX() {
        return X;
    }

    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    @Override
    public String toString() {
        return "{" +
                "X=" + X +
                ", Y=" + Y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return X == point.X &&
                Y == point.Y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}
