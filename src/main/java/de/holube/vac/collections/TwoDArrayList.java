package de.holube.vac.collections;

import java.util.*;

public class TwoDArrayList<E> implements TwoDList<E>, Cloneable, RandomAccess {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[][] elementData;
    private int[] sizes;

    public TwoDArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public TwoDArrayList(int initialNumRows) {
        this(initialNumRows, DEFAULT_CAPACITY);
    }

    public TwoDArrayList(int initialNumRows, int initialNumCols) {
        if (initialNumRows < 0)
            throw new IllegalArgumentException("Illegal Capacity must be positive or zero: " + initialNumRows);
        if (initialNumCols < 0)
            throw new IllegalArgumentException("Illegal SubCapacity must be positive or zero: " + initialNumCols);

        this.elementData = new Object[initialNumRows][];
        this.sizes = new int[initialNumRows];
        for (int i = 0; i < initialNumRows; i++) {
            this.elementData[i] = new Object[initialNumCols];
        }
    }

    public TwoDArrayList(TwoDArrayList<E> other) {
        this.elementData = new Object[other.elementData.length][];
        this.sizes = new int[other.elementData.length];
        for (int i = 0; i < other.elementData.length; i++) {
            this.elementData[i] = new Object[other.elementData[i].length];
            this.sizes[i] = other.sizes[i];
            elementData[i] = Arrays.copyOf(other.elementData[i], other.elementData[i].length);
        }
    }

    @SuppressWarnings("unchecked")
    private E elementData(int i, int j) {
        return (E) elementData[i][j];
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity * 2);
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void grow(int row, int minCapacity) {
        int oldCapacity = elementData[row].length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity + (oldCapacity * 2);
            if (newCapacity < minCapacity)
                newCapacity = minCapacity;
            elementData[row] = Arrays.copyOf(elementData[row], newCapacity);
        }
    }

    private void checkIndices(int row, int column) {
        Objects.checkIndex(row, sizes.length);
        Objects.checkIndex(column, sizes[row]);
    }

    @Override
    public E get(int row, int column) {
        checkIndices(row, column);
        return elementData(row, column);
    }

    @Override
    public E set(int row, int column, E element) {
        checkIndices(row, column);
        E oldValue = elementData(row, column);
        elementData[row][column] = element;
        return oldValue;
    }

    @Override
    public boolean add(int row, E element) {
        Objects.checkIndex(row, sizes.length);
        grow(row, sizes[row] + 1);
        elementData[row][sizes[row]] = element;
        sizes[row] = sizes[row] + 1;
        return true;
    }

    @Override
    public E remove(int row, int column) {
        return null;
    }

    @Override
    public int size(int row) {
        return 0;
    }

    @Override
    public E[] shortestArray(E[] array) {
        return null;
    }

    @Override
    public List<E> shortestList() {
        return null;
    }

    @Override
    public E[] longestArray(E[] array) {
        return null;
    }

    @Override
    public List<E> longestList() {
        return null;
    }

    @Override
    public boolean addToShortest(E element) {
        return false;
    }

    @Override
    public boolean addToLongest(E element) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public TwoDArrayList<E> clone() {
        try {
            TwoDArrayList clone = (TwoDArrayList) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

}
