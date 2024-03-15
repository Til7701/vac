package de.holube.vac.stream;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Consumer;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ForEachElementOp<T> extends NonMappingOp<T> {

    private final Consumer<T> consumer;

    @Override
    public void performSequential(T[][] input) {
        for (T[] row : input)
            for (T element : row)
                consumer.accept(element);
    }

    @Override
    public void performParallel(T[][] input) {
        Arrays.stream(input)
                .parallel()
                .forEach(row -> {
                    for (T element : row)
                        consumer.accept(element);
                });
    }

}
