package de.holube.vac.stream;

import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public class FilterElementsOp<T> extends NonMappingOp<T> {

    private final Predicate<T> predicate;

    @Override
    public void performSequential(T[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[i].length; j++) {
                if (!predicate.test(input[i][j])) {
                    input[i][j] = null;
                }
            }
        }
    }

    @Override
    public void performParallel(T[][] input) {
        throw new UnsupportedOperationException("Parallel filtering is not supported yet");
    }

}
