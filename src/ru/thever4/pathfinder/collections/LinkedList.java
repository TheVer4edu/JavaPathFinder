package ru.thever4.pathfinder.collections;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private final E value;
    private LinkedList<E> previous;
    public final int length;

    public LinkedList(E value) {
        this.value = value;
        this.previous = null;
        this.length = 1;
    }

    public LinkedList(E value, LinkedList<E> previous) {
        this.value = value;
        this.previous = previous;
        this.length = previous.length + 1;
    }

    public E peek() {
        return this.value;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator<>();
    }

    public class LinkedListIterator<E> implements Iterator<E> {

        private int iLength;
        private LinkedList<E> iPrevious;
        private E iValue;

        public LinkedListIterator() {
            this.iLength = length;
            this.iPrevious = (LinkedList<E>) previous;
            this.iValue = (E) value;
        }

        @Override
        public boolean hasNext() {
            if(iLength == 1) {
                iLength = 0;
                return true;
            }
            return iLength != 0;
        }

        @Override
        public E next() {
            E result = (E) iValue;
            if(iPrevious != null){
                iLength = iPrevious.length;
                iValue = iPrevious.value;
                iPrevious = iPrevious.previous;
            }
            return result;
        }
    }
}
