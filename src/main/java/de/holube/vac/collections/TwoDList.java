package de.holube.vac.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public interface TwoDList<E> extends Collection<E> {

    Iterator<List<E>> listIterator();

    List<E> get(int row);

    E get(int row, int column);

    E set(int row, int column, E element);

    boolean add(int row, E element);

    E remove(int row, int column);

    int size(int row);

    E[] shortestArray(E[] array);

    List<E> shortestList();

    E[] longestArray(E[] array);

    List<E> longestList();

    boolean addToShortest(E element);

    boolean addToLongest(E element);

}
