package de.holube.vac.collections;

import java.util.*;

public class TwoDArrayList<E> extends AbstractCollection<E> implements TwoDList<E>, RandomAccess {

    private static final int DEFAULT_CAPACITY = 10;

    private final List<List<E>> elementData;

    public TwoDArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public TwoDArrayList(int initialNumRows) {
        this(initialNumRows, DEFAULT_CAPACITY);
    }

    public TwoDArrayList(int initialNumRows, int initialNumCols) {
        if (initialNumRows < 0)
            throw new IllegalArgumentException("Illegal number of rows. Must be positive or zero: " + initialNumRows);
        if (initialNumCols < 0)
            throw new IllegalArgumentException("Illegal number of columns. Must be positive or zero: " + initialNumCols);
        this.elementData = new ArrayList<>(initialNumRows);
    }

    public TwoDArrayList(TwoDArrayList<E> other) {
        Objects.requireNonNull(other, "Other list must not be null.");
        this.elementData = new ArrayList<>(other.elementData.size());
        for (int i = 0; i < other.elementData.size(); i++) {
            this.elementData.add(new ArrayList<>(other.elementData.get(i)));
        }
    }

    public TwoDArrayList(E[][] array) {
        Objects.requireNonNull(array, "Array must not be null.");
        this.elementData = new ArrayList<>(array.length);
        for (E[] row : array) {
            List<E> list = new ArrayList<>(row.length);
            Collections.addAll(list, row);
            elementData.add(list);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    @Override
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
    public List<E> get(int row) {
        return elementData.get(row);
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
        if (row >= elementData.size()) {
            for (int i = elementData.size(); i <= row; i++)
                elementData.add(new ArrayList<>());
        }
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
        Objects.requireNonNull(array, "Array must not be null.");
        return shortestList().toArray(array);
    }

    @Override
    public List<E> shortestList() {
        if (elementData.isEmpty()) return new ArrayList<>();
        List<E> shortestList = elementData.getFirst();
        for (int i = 1; i < elementData.size(); i++) {
            if (elementData.get(i).size() < shortestList.size())
                shortestList = elementData.get(i);
        }
        return shortestList;
    }

    @Override
    public E[] longestArray(E[] array) {
        return longestList().toArray(array);
    }

    @Override
    public List<E> longestList() {
        if (elementData.isEmpty()) return new ArrayList<>();
        List<E> longestList = elementData.getFirst();
        for (int i = 1; i < elementData.size(); i++) {
            if (elementData.get(i).size() > longestList.size())
                longestList = elementData.get(i);
        }
        return longestList;
    }

    @Override
    public boolean addToShortest(E element) {
        if (elementData.isEmpty())
            elementData.add(new ArrayList<>());
        return shortestList().add(element);
    }

    @Override
    public boolean addToLongest(E element) {
        if (elementData.isEmpty())
            elementData.add(new ArrayList<>());
        return longestList().add(element);
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
