package ru.thever4.pathfinder.collections;

import ru.thever4.pathfinder.exceptions.DimensionCountException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;

public class MultidimensionalArray<E> implements Iterable<E>{
    private E[] data;
    private int[] dimensions;

    public MultidimensionalArray(Class<E> classIdentifier, int... dimensions) {
        this.dimensions = dimensions;
        data = (E[]) Array.newInstance(classIdentifier, multiplication(dimensions));
    }

    private MultidimensionalArray(E[] data, int[] dimensions) {
        this.data = data;
        this.dimensions = dimensions;
    }

    public E get(int... index) throws DimensionCountException {
        if(index.length != dimensions.length)
            throw new DimensionCountException();
        return data[getIndex(index)];
    }

    public void set(E value, int... index) throws DimensionCountException {
        if(index.length != dimensions.length)
            throw new DimensionCountException();
        data[getIndex(index)] = value;
    }

    public int getLength(int dimension) {
        if(dimension < 0 || dimension >= dimensions.length)
            throw new DimensionCountException();
        return dimensions[dimension];
    }

    public MultidimensionalArray<E> copy() {
        return new MultidimensionalArray<E>(Arrays.copyOf(data, data.length), dimensions);
    }

    private int multiplication(int[] dimensions) {
        int result = dimensions[0];
        for(int i = 1; i < dimensions.length; i++) {
            result *= dimensions[i];
        }
        return result;
    }

    private int getIndex(int[] indexes) {
        int result = indexes[indexes.length - 1];
        int j = 1;
        for(int i = indexes.length - 2; i >= 0; i--) {
            result += indexes[i]*Math.pow(dimensions[i + 1], j);
        }
        if(result < 0 || result >= data.length)
            throw new ArrayIndexOutOfBoundsException();
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return new AnyDimIterator<E>(this.data);
    }

    public class AnyDimIterator<E> implements Iterator<E> {

        private int iterator = 0;
        private E[] data;

        public AnyDimIterator(E[] data) {
            if(data == null)
                throw new NullPointerException();
        this.data = data;
        }

        @Override
        public boolean hasNext() {
            return iterator < data.length;
        }

        @Override
        public E next() {
            return data[iterator++];
        }
    }
}