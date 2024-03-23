package de.holube.vac.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class TwoDArrayListTest {

    Object[][] defaultArray;
    TwoDArrayList<Object> defaultList;

    @BeforeEach
    void setUp() {
        defaultArray = new Object[][]{
                {new Object(), new Object()},
                {new Object(), new Object(), new Object()},
                {new Object()}
        };
        defaultList = new TwoDArrayList<>(defaultArray);
    }

    //##################################################
    // iterator()
    //##################################################

    @Test
    void iteratorEmptyTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        assertFalse(list.iterator().hasNext());
    }

    @Test
    void iteratorSingleTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        Object o1 = new Object();
        list.add(0, o1);
        int i = 0;
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            assertEquals(o, o1);
            i++;
        }
        assertEquals(1, i);
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorMultipleTest() {
        TwoDArrayList<Object> list = defaultList;
        Object[] oneDimArray = new Object[6];
        System.arraycopy(defaultArray[0], 0, oneDimArray, 0, 2);
        System.arraycopy(defaultArray[1], 0, oneDimArray, 2, 3);
        System.arraycopy(defaultArray[2], 0, oneDimArray, 5, 1);
        int i = 0;
        Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object o = iterator.next();
            assertEquals(oneDimArray[i++], o);
        }
        assertEquals(6, i);
        assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorForTest() {
        TwoDArrayList<Object> list = defaultList;
        Object[] oneDimArray = new Object[6];
        System.arraycopy(defaultArray[0], 0, oneDimArray, 0, 2);
        System.arraycopy(defaultArray[1], 0, oneDimArray, 2, 3);
        System.arraycopy(defaultArray[2], 0, oneDimArray, 5, 1);
        int i = 0;
        for (Object o : list) {
            assertEquals(oneDimArray[i++], o);
        }
        assertEquals(6, i);
    }

    //##################################################
    // TwoDListOfLists()
    //##################################################

    @Test
    void constructorTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        assertEquals(0, list.size());
        assertFalse(list.iterator().hasNext());
        assertFalse(list.listIterator().hasNext());
        assertTrue(list.isEmpty());
    }

    //##################################################
    // TwoDListOfLists(int, int)
    //##################################################

    @Test
    void constructorCapacityTest() {
        assertThrows(IllegalArgumentException.class, () -> new TwoDArrayList<>(-1));
        assertThrows(IllegalArgumentException.class, () -> new TwoDArrayList<>(0, -1));
        TwoDArrayList<Object> list = new TwoDArrayList<>(5, 3);
        assertEquals(0, list.size());
        assertFalse(list.iterator().hasNext());
        assertFalse(list.listIterator().hasNext());
        assertTrue(list.isEmpty());
    }

    //##################################################
    // TwoDListOfLists(TowDListOfLists)
    //##################################################

    @Test
    void constructorNullTest() {
        assertThrows(NullPointerException.class, () -> new TwoDArrayList<>((TwoDArrayList<Object>) null));
    }

    @Test
    void constructorOtherTest() {
        TwoDArrayList<Object> other = new TwoDArrayList<>(5, 3);
        other.add(0, new Object());
        other.add(0, new Object());
        other.add(1, new Object());
        other.add(1, new Object());
        other.add(1, new Object());
        other.add(2, new Object());
        TwoDArrayList<Object> list = new TwoDArrayList<>(other);
        assertEquals(6, list.size());
        assertEquals(2, list.size(0));
        assertEquals(3, list.size(1));
        assertEquals(1, list.size(2));
        assertEquals(other.get(0, 0), list.get(0, 0));
        assertEquals(other.get(0, 1), list.get(0, 1));
        assertEquals(other.get(1, 0), list.get(1, 0));
        assertEquals(other.get(1, 1), list.get(1, 1));
        assertEquals(other.get(1, 2), list.get(1, 2));
        assertEquals(other.get(2, 0), list.get(2, 0));
        assertFalse(list.isEmpty());
    }

    //##################################################
    // TwoDListOfLists(E[][])
    //##################################################

    @Test
    void constructorArrayNullTest() {
        assertThrows(NullPointerException.class, () -> new TwoDArrayList<>((Object[][]) null));
    }

    @Test
    void constructorArrayOtherTest() {
        TwoDArrayList<Object> list = defaultList;
        assertEquals(6, list.size());
        assertEquals(2, list.size(0));
        assertEquals(3, list.size(1));
        assertEquals(1, list.size(2));
        assertEquals(defaultArray[0][0], list.get(0, 0));
        assertEquals(defaultArray[0][1], list.get(0, 1));
        assertEquals(defaultArray[1][0], list.get(1, 0));
        assertEquals(defaultArray[1][1], list.get(1, 1));
        assertEquals(defaultArray[1][2], list.get(1, 2));
        assertEquals(defaultArray[2][0], list.get(2, 0));
        assertFalse(list.isEmpty());
    }

    //##################################################
    // size()
    //##################################################

    @Test
    void sizeTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        assertEquals(0, list.size());
        list.add(0, new Object());
        assertEquals(1, list.size());
        list.add(0, null);
        assertEquals(2, list.size());
        list.add(1, new Object());
        assertEquals(3, list.size());
        list.add(1, new Object());
        assertEquals(4, list.size());
        list.add(2, new Object());
        assertEquals(5, list.size());
        list.add(1, new Object());
        assertEquals(6, list.size());
    }

    //##################################################
    // get(int, int)
    //##################################################

    @Test
    void getTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        list.add(0, o1);
        list.add(0, o2);
        list.add(1, o3);
        assertEquals(o1, list.get(0, 0));
        assertEquals(o2, list.get(0, 1));
        assertEquals(o3, list.get(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(2, 0));
    }

    //##################################################
    // set(int, int, E)
    //##################################################

    @Test
    void setTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        list.add(0, o1);
        list.add(0, o2);
        list.add(1, o3);
        Object o4 = new Object();
        Object o5 = new Object();
        Object o6 = new Object();
        assertEquals(o1, list.set(0, 0, o4));
        assertEquals(o2, list.set(0, 1, o5));
        assertEquals(o3, list.set(1, 0, o6));
        assertEquals(o4, list.get(0, 0));
        assertEquals(o5, list.get(0, 1));
        assertEquals(o6, list.get(1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, 2, o1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(2, 0, o2));
    }

    //##################################################
    // add(int, E)
    //##################################################

    @Test
    void addTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        list.add(0, o1);
        list.add(0, o2);
        list.add(1, o3);
        assertEquals(o1, list.get(0, 0));
        assertEquals(o2, list.get(0, 1));
        assertEquals(o3, list.get(1, 0));
        Object o4 = new Object();
        Object o5 = new Object();
        Object o6 = new Object();
        list.add(0, o4);
        list.add(0, o5);
        list.add(1, o6);
        assertEquals(o1, list.get(0, 0));
        assertEquals(o2, list.get(0, 1));
        assertEquals(o4, list.get(0, 2));
        assertEquals(o5, list.get(0, 3));
        assertEquals(o3, list.get(1, 0));
        assertEquals(o6, list.get(1, 1));
    }

    //##################################################
    // remove(int, int)
    //##################################################

    @Test
    void removeTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        list.add(0, o1);
        list.add(0, o2);
        list.add(1, o3);

        assertEquals(o1, list.remove(0, 0));
        assertEquals(o2, list.get(0, 0));
        assertEquals(o3, list.get(1, 0));

        assertEquals(o3, list.remove(1, 0));
        assertEquals(o2, list.get(0, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1, 0));

        assertEquals(o2, list.remove(0, 0));
        assertTrue(list.isEmpty());
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0, 0));
    }

    //##################################################
    // shortestArray(E[])
    //##################################################

    @Test
    void shortestArrayEmptyTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        assertArrayEquals(new Object[0], list.shortestArray(new Object[0]));
    }

    @Test
    void shortestArrayTest() {
        TwoDArrayList<Object> list = defaultList;
        assertArrayEquals(defaultArray[2], list.shortestArray(new Object[0]));
    }

    //##################################################
    // shortestList()
    //##################################################

    @Test
    void shortestListEmptyTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        assertEquals(0, list.shortestList().size());
    }

    @Test
    void shortestListTest() {
        TwoDArrayList<Object> list = defaultList;
        assertEquals(1, list.shortestList().size());
        assertEquals(defaultArray[2][0], list.shortestList().getFirst());
    }

    //##################################################
    // longestArray(E[])
    //##################################################

    @Test
    void longestArrayEmptyTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        assertArrayEquals(new Object[0], list.longestArray(new Object[0]));
    }

    @Test
    void longestArrayTest() {
        TwoDArrayList<Object> list = defaultList;
        assertArrayEquals(defaultArray[1], list.longestArray(new Object[0]));
    }

    //##################################################
    // longestList()
    //##################################################

    @Test
    void longestListEmptyTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        assertEquals(0, list.longestList().size());
    }

    @Test
    void longestListTest() {
        TwoDArrayList<Object> list = defaultList;
        assertEquals(3, list.longestList().size());
        assertEquals(defaultArray[1][0], list.longestList().getFirst());
        assertEquals(defaultArray[1][1], list.longestList().get(1));
        assertEquals(defaultArray[1][2], list.longestList().get(2));
    }

    //##################################################
    // addToShortest(E)
    //##################################################

    @Test
    void addToShortestEmptyTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        Object o1 = new Object();
        list.addToShortest(o1);
        assertEquals(1, list.size());
        assertEquals(1, list.size(0));
        assertEquals(o1, list.get(0, 0));
    }

    @Test
    void addToShortestTest() {
        TwoDArrayList<Object> list = defaultList;
        Object o1 = new Object();
        list.addToShortest(o1);
        assertEquals(2, list.size(2));
        assertArrayEquals(defaultArray[0], list.get(0).toArray());
        assertArrayEquals(defaultArray[1], list.get(1).toArray());
        assertEquals(defaultArray[2][0], list.get(2, 0));
        assertEquals(o1, list.get(2, 1));
    }

    //##################################################
    // addToLongest(E)
    //##################################################

    @Test
    void addToLongestEmptyTest() {
        TwoDArrayList<Object> list = new TwoDArrayList<>();
        Object o1 = new Object();
        list.addToLongest(o1);
        assertEquals(1, list.size());
        assertEquals(1, list.size(0));
        assertEquals(o1, list.get(0, 0));
    }

    @Test
    void addToLongestTest() {
        TwoDArrayList<Object> list = defaultList;
        Object o1 = new Object();
        list.addToLongest(o1);
        assertEquals(4, list.size(1));
        assertArrayEquals(defaultArray[0], list.get(0).toArray());
        assertArrayEquals(defaultArray[2], list.get(2).toArray());
        assertEquals(defaultArray[1][0], list.get(1, 0));
        assertEquals(defaultArray[1][1], list.get(1, 1));
        assertEquals(defaultArray[1][2], list.get(1, 2));
        assertEquals(o1, list.get(1, 3));
    }

}
