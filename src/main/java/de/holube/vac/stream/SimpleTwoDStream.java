package de.holube.vac.stream;

import lombok.NonNull;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SimpleTwoDStream<T> implements TwoDStream<T> {

    private T[][] matrix;
    private boolean parallel;

    SimpleTwoDStream(@NonNull T[][] matrix, boolean parallel) {
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
        Downstream<T, O> downstream = op.getDownstream();
        downstream.setInput(matrix);

        if (parallel)
            op.performParallel(matrix);
        else
            op.performSequential(matrix);

        O[][] output = downstream.getOutput();
        Objects.requireNonNull(output, "Output of operation must not be null");
        return new SimpleTwoDStream<>(output, parallel);
    }

    @Override
    public TwoDStream<T> callNonMappingOp(@NonNull NonMappingOp<T> op) {
        op.getDownstream().setInput(matrix);
        if (parallel)
            op.performParallel(matrix);
        else
            op.performSequential(matrix);

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

}
