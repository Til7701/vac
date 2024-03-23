package de.holube.vac.stream;

import de.holube.vac.collections.TwoDList;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class ForEachElementOp<T> extends NonMappingOp<T> {

    private final Consumer<T> consumer;

    @Override
    public TwoDList<T> performSequential(TwoDList<T> input) {
        input.forEach(consumer);
        return input;
    }

    @Override
    public TwoDList<T> performParallel(TwoDList<T> input) {
        input.stream()
                .parallel()
                .forEach(consumer);
        return input;
    }

}
