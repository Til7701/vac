package de.holube.vac.array;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArraysEx {

    /**
     * Returns the first non-null element of the array. The search is performed from the first to the last element. If
     * no such element exists, an empty Optional is returned.
     *
     * @param array the array to search
     * @param <T>   the type of the array
     * @return the first non-null element of the array or an empty Optional if no such element exists
     * @throws NullPointerException if the array is null
     */
    public static <T> Optional<T> getFirstNonNullElement(T[] array) throws NullPointerException {
        Objects.requireNonNull(array);
        for (T t : array) {
            if (t != null) return Optional.of(t);
        }
        return Optional.empty();
    }

    /**
     * Returns the first non-null element of the 2D array. The search is performed row by row. If a row is null, it is
     * skipped. If a row is not null, the first non-null element of the row is returned. If no such element exists, the
     * next row is checked. If no such element exists in the entire array, an empty Optional is returned.
     *
     * @param array the 2D array to search
     * @param <T>   the type of the 2D array
     * @return the first non-null element of the 2D array or an empty Optional if no such element exists
     * @throws NullPointerException if the 2D array is null
     */
    public static <T> Optional<T> getFirstNonNullElement(T[][] array) throws NullPointerException {
        Objects.requireNonNull(array);
        for (T[] ts : array) {
            if (ts == null)
                continue;
            for (T t : ts) {
                if (t != null)
                    return Optional.of(t);
            }
        }
        return Optional.empty();
    }

}
