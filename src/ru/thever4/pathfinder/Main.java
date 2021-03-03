package ru.thever4.pathfinder;

import ru.thever4.pathfinder.collections.MultidimensionalArray;
import ru.thever4.pathfinder.collections.LinkedList;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map map = new Map("#s########",
                          "#.###..###",
                          "#.#.#.####",
                          "#.....####",
                          "###.######",
                          "###.######",
                          "###.######",
                          "###.######",
                          "###.....##",
                          "#######f##"
        );

        map = findRoute(map.copy());
        map.print();

    }

    public static Map findRoute(Map map) {
        map = map.copy();
        MultidimensionalArray<State> states = map.getMap();
        Point start = new Point(), finish = new Point();
        for(int x = 0; x < map.getHeight(); x++) {
            for(int y = 0; y < map.getWidth(); y++) {
                if(states.get(x, y) == State.Start)
                    start = new Point(x, y);
                if(states.get(x, y) == State.Finish)
                    finish = new Point(x, y);
            }
        }
        states.set(State.Empty, start.getX(), start.getY());
        states.set(State.Empty, finish.getX(), finish.getY());

        LinkedList<Point> points = findRoute(states.copy(), start, finish);
        for (Point p : points) {
            if(p.equals(start) || p.equals(finish))
                continue;
            map.goTo(p.getX(), p.getY());
        }
        return map;
    }

    public static LinkedList<Point> findRoute(MultidimensionalArray<State> states, Point start, Point end) {
        Queue<LinkedList<Point>> queue = new java.util.LinkedList<>();
        queue.add(new LinkedList<>(start));
        while (!queue.isEmpty()) {
            LinkedList<Point> current = queue.poll();
            Point point = current.peek();
            if(point.getX() < 0 || point.getX() >= states.getLength(1) || point.getY() < 0 || point.getY() >= states.getLength(0))
                continue;
            if(states.get(point.getX(), point.getY()) != State.Empty)
                continue;
            states.set(State.Visited, point.getX(), point.getY());

            for(int dy = -1; dy <= 1; dy++) {
                for(int dx = -1; dx <= 1; dx++) {
                    if(Math.abs(dx) == Math.abs(dy))
                        continue;
                    Point neighbor = new Point(point.getX() + dx, point.getY() + dy);
                    LinkedList<Point> updated = new LinkedList<>(neighbor, current);
                    queue.add(updated);
                    if(end.equals(neighbor))
                        return updated;
                }
            }
        }
        return null;
    }
}
