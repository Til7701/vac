package de.holube.vac.collections;

import java.util.*;

public class TwoDListOfLists<E> extends AbstractCollection<E> implements TwoDList<E>, RandomAccess {

    private static final int DEFAULT_CAPACITY = 10;

    private final List<List<E>> elementData;

    public TwoDListOfLists() {
        this(DEFAULT_CAPACITY);
    }

    public TwoDListOfLists(int initialNumRows) {
        this(initialNumRows, DEFAULT_CAPACITY);
    }

    public TwoDListOfLists(int initialNumRows, int initialNumCols) {
        if (initialNumRows < 0)
            throw new IllegalArgumentException("Illegal number of rows. Must be positive or zero: " + initialNumRows);
        if (initialNumCols < 0)
            throw new IllegalArgumentException("Illegal number of columns. Must be positive or zero: " + initialNumCols);
        this.elementData = new ArrayList<>(initialNumRows);
        for (int i = 0; i < initialNumRows; i++) {
            this.elementData.add(new ArrayList<>(initialNumCols));
        }
    }

    public TwoDListOfLists(TwoDListOfLists<E> other) {
        this.elementData = new ArrayList<>(other.elementData.size());
        for (int i = 0; i < other.elementData.size(); i++) {
            this.elementData.add(new ArrayList<>(other.elementData.get(i)));
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    public Iterator<List<E>> listIterator() {
        return elementData.iterator();
    }

    @Override
    public int size() {
        int size = 0;
        for (List<E> list : elementData) {
            size += list.size();
        }
        return size;
    }

    @Override
    public E get(int row, int column) {
        return elementData.get(row).get(column);
    }

    @Override
    public E set(int row, int column, E element) {
        return elementData.get(row).set(column, element);
    }

    @Override
    public boolean add(int row, E element) {
        return elementData.get(row).add(element);
    }

    @Override
    public E remove(int row, int column) {
        return elementData.get(row).remove(column);
    }

    @Override
    public int size(int row) {
        return elementData.get(row).size();
    }

    @Override
    public E[] shortestArray(E[] array) {
        return shortestList().toArray(array);
    }

    @Override
    public List<E> shortestList() {
        int shortest = Integer.MAX_VALUE;
        for (List<E> elementDatum : elementData) {
            if (elementDatum.size() < shortest)
                shortest = elementDatum.size();
        }
        return elementData.get(shortest);
    }

    @Override
    public E[] longestArray(E[] array) {
        return longestList().toArray(array);
    }

    @Override
    public List<E> longestList() {
        int longest = 0;
        for (List<E> elementDatum : elementData) {
            if (elementDatum.size() > longest)
                longest = elementDatum.size();
        }
        return elementData.get(longest);
    }

    @Override
    public boolean addToShortest(E element) {
        return false;
    }

    @Override
    public boolean addToLongest(E element) {
        return false;
    }

    private class ElementIterator implements Iterator<E> {
        private int row;
        private int column;

        public ElementIterator() {
            this.row = 0;
            this.column = 0;
        }

        @Override
        public boolean hasNext() {
            return row < elementData.size() && column < elementData.get(row).size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elementData.get(row).get(column);
            column++;
            if (column >= elementData.get(row).size()) {
                row++;
                column = 0;
            }
            return result;
        }
    }

}
