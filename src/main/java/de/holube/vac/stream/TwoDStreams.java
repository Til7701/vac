package de.holube.vac.stream;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TwoDStreams {

    @SuppressWarnings("unchecked")
    public static <T> TwoDStream<T> of(@NonNull T[][] matrix) {
        T[][] copy = (T[][]) new Object[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = (T[]) new Object[matrix[i].length];
            System.arraycopy(matrix[i], 0, copy[i], 0, matrix[i].length);
        }
        return new SimpleTwoDStream<>(copy, false);
    }

}
