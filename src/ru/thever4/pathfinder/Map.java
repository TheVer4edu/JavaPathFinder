package ru.thever4.pathfinder;

import ru.thever4.pathfinder.collections.MultidimensionalArray;

import java.util.HashMap;

public class Map {
    private MultidimensionalArray<State> map;
    private HashMap<Character, State> decoding = new HashMap<Character, State>();
    private HashMap<State, Character> encoding = new HashMap<State, Character>();

    public Map(String... map) {
        initializeSynonyms();
        this.map = new MultidimensionalArray<State>(State.class, map.length, map[0].length());
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[i].length(); j++) {
                this.map.set(decoding.get(map[i].charAt(j)), i, j);
            }
        }
    }

    private Map(MultidimensionalArray<State> map) {
        this.map = map;
        initializeSynonyms();
    }

    private void initializeSynonyms() {
        this.decoding.put('#', State.Wall);
        this.decoding.put('*', State.Visited);
        this.decoding.put('.', State.Empty);
        this.decoding.put('s', State.Start);
        this.decoding.put('f', State.Finish);
        this.encoding.put(State.Wall, '#');
        this.encoding.put(State.Visited, '*');
        this.encoding.put(State.Empty, '.');
        this.encoding.put(State.Start, 's');
        this.encoding.put(State.Finish, 'f');

    }

    public void print() {
        for(int i = 0; i < map.getLength(0); i++) {
            for(int j = 0; j < map.getLength(1); j++) {
                System.out.print(encoding.get(map.get(i, j)));
            }
            System.out.println();
        }
    }

    public int getWidth() {
        return map.getLength(1);
    }

    public int getHeight() {
        return map.getLength(0);
    }

    public MultidimensionalArray<State> getMap() {
        return map.copy();
    }

    public Map copy() {
        return new Map(getMap());
    }

    public State get(int x, int y) {
        return this.map.get(x, y);
    }

    public void goTo(int x, int y) {
        map.set(State.Visited, x, y);
    }
}
