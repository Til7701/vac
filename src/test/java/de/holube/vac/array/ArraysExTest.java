package de.holube.vac.array;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArraysExTest {

    //##################################################
    // getFirstNonNullElement(T[])
    //##################################################

    @Test
    void getFirstNonNullElementNullTest() {
        assertThrows(NullPointerException.class, () -> ArraysEx.getFirstNonNullElement((Object[]) null));
    }

    @Test
    void getFirstNonNullElementEmptyTest() {
        assertTrue(ArraysEx.getFirstNonNullElement(new Object[0]).isEmpty());
    }

    @Test
    void getFirstNonNullElementTest() {
        Object o = new Object();
        Object[] array = new Object[]{null, o, null, new Object(), null};
        Object[] copy = new Object[array.length];
        System.arraycopy(array, 0, copy, 0, array.length);
        Optional<Object> firstNonNullElement = ArraysEx.getFirstNonNullElement(array);
        assertTrue(firstNonNullElement.isPresent());
        assertEquals(o, firstNonNullElement.get());
        assertArrayEquals(copy, array);
    }

    //##################################################
    // getFirstNonNullElement(T[][])
    //##################################################

    @Test
    void getFirstNonNullElement2DNullTest() {
        assertThrows(NullPointerException.class, () -> ArraysEx.getFirstNonNullElement((Object[][]) null));
    }

    @Test
    void getFirstNonNullElement2DEmptyTest() {
        assertTrue(ArraysEx.getFirstNonNullElement(new Object[0][0]).isEmpty());
    }

    @Test
    void getFirstNonNullElement2DTest() {
        Object o = new Object();
        Object[][] array = new Object[][]{
                {null, o, null},
                {new Object(), null, null},
                {null, null, null}
        };
        Object[][] copy = new Object[array.length][];
        for (int i = 0; i < array.length; i++) {
            copy[i] = new Object[array[i].length];
            System.arraycopy(array[i], 0, copy[i], 0, array[i].length);
        }
        Optional<Object> firstNonNullElement = ArraysEx.getFirstNonNullElement(array);
        assertTrue(firstNonNullElement.isPresent());
        assertEquals(o, firstNonNullElement.get());
        for (int i = 0; i < array.length; i++) {
            assertArrayEquals(copy[i], array[i]);
        }
    }

    @Test
    void getFirstNonNullElement2DNullSubArrayTest() {
        Object o = new Object();
        Object[][] array = new Object[][]{
                {null, null, null},
                null,
                {null, o, null}
        };
        Object[][] copy = new Object[array.length][];
        copy[0] = new Object[array[0].length];
        System.arraycopy(array[0], 0, copy[0], 0, array[0].length);
        copy[1] = null;
        copy[2] = new Object[array[2].length];
        System.arraycopy(array[2], 0, copy[2], 0, array[2].length);

        Optional<Object> firstNonNullElement = ArraysEx.getFirstNonNullElement(array);
        assertTrue(firstNonNullElement.isPresent());
        assertEquals(o, firstNonNullElement.get());
        for (int i = 0; i < array.length; i++) {
            assertArrayEquals(copy[i], array[i]);
        }
    }

}
