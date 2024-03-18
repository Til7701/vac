package de.holube.vac.array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArraysEx {

    public static <T> T getFirstNonNullElement(T[] array) {
        for (T t : array) {
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    public static <T> T getFirstNonNullElement(T[][] array) {
        for (T[] ts : array) {
            for (T t : ts) {
                if (t != null) {
                    return t;
                }
            }
        }
        return null;
    }

}
