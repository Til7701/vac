package de.holube.vac.stream;

import lombok.NonNull;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface TwoDStream<T> {

    TwoDStream<T> parallel();

    TwoDStream<T> sequential();

    <O> TwoDStream<O> callMappingOp(@NonNull MappingOp<T, O> op);

    TwoDStream<T> callNonMappingOp(@NonNull NonMappingOp<T> op);

    TwoDStream<T> forEachElement(@NonNull Consumer<T> consumer);

    <R> TwoDStream<R> mapElements(@NonNull Function<T, R> function);

    TwoDStream<T> filterElements(@NonNull Predicate<T> predicate);

}
