package de.holube.vac.stream;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
class MapElementsOp<I, O> extends MappingOp<I, O> {

    private final Function<I, O> function;

    @Override
    public void performSequential(I[][] input) {
        O[][] output = downstream.createOutputWithSameDimensions(null);
        for (int i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                output[i][j] = function.apply(input[i][j]);
            }
        }
    }

    @Override
    public void performParallel(I[][] input) {
        O[][] output = downstream.createOutputWithSameDimensions(null);
        Arrays.stream(input)
                .parallel()
                .forEach(row -> {
                    for (int j = 0; j < row.length; j++)
                        output[0][j] = function.apply(row[j]);
                });
    }

}
