package de.holube.vac.stream;

import de.holube.vac.collections.TwoDList;
import lombok.NonNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SimpleTwoDStream<T> implements TwoDStream<T> {

    private final TwoDList<T> matrix;
    private boolean parallel;

    SimpleTwoDStream(@NonNull TwoDList<T> matrix, boolean parallel) {
        this.matrix = matrix;
        this.parallel = parallel;
    }

    @Override
    public TwoDStream<T> parallel() {
        parallel = true;
        return this;
    }

    @Override
    public TwoDStream<T> sequential() {
        parallel = false;
        return this;
    }

    @Override
    public <O> TwoDStream<O> callMappingOp(@NonNull MappingOp<T, O> op) {
        TwoDList<O> output = performOp(op);
        Objects.requireNonNull(output, "Output of operation must not be null");
        return new SimpleTwoDStream<>(output, parallel);
    }

    @Override
    public TwoDStream<T> callNonMappingOp(@NonNull NonMappingOp<T> op) {
        performOp(op);
        return this;
    }

    @Override
    public TwoDStream<T> forEachElement(@NonNull Consumer<T> consumer) {
        ForEachElementOp<T> op = new ForEachElementOp<>(consumer);
        return callNonMappingOp(op);
    }

    @Override
    public <R> TwoDStream<R> mapElements(@NonNull Function<T, R> function) {
        MapElementsOp<T, R> op = new MapElementsOp<>(function);
        return callMappingOp(op);
    }

    @Override
    public TwoDStream<T> filterElements(@NonNull Predicate<T> predicate) {
        FilterElementsOp<T> op = new FilterElementsOp<>(predicate);
        return callNonMappingOp(op);
    }

    private <O> TwoDList<O> performOp(Op<T, O> op) {
        if (parallel)
            return op.performParallel(matrix);
        else
            return op.performSequential(matrix);
    }

}
