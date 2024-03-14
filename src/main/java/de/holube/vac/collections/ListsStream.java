package de.holube.vac.collections;

import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

public interface ListsStream<T> {

    ListsStream<T> forEach(@NonNull Consumer<T> consumer);

    ListsStream<T> forEachPair(@NonNull BiConsumer<T, T> consumer);

    ListsStream<T> forEachRest(@NonNull Consumer<T> consumer);

    ListsStream<T> filter(@NonNull BiPredicate<T, T> filter);

    ListsStream<T> filterEach(@NonNull Predicate<T> filter);

    ListsStream<T> toCommonSize();

    <R> ListsStream<R> map(@NonNull Function<Pair<T, T>, Pair<R, R>> mapper);

    <R> ListsStream<R> mapEach(@NonNull Function<T, R> mapper);

    <R> Stream<R> combine(@NonNull BiFunction<T, T, R> combiner);

    Pair<List<T>, List<T>> toLists();

    Pair<List<T>, List<T>> toCommonSizeLists();

    List<T> concat();

    ListsStream<T> concat(@NonNull ListsStream<T> other);

    ListsStream<T> concatEach(@NonNull List<T> list);

    ListsStream<T> concatAlternating(@NonNull List<T> list);

    ListsStream<T> concatToShortest(@NonNull List<T> list);

    Pair<T[], T[]> toArrays();

    Pair<T[], T[]> toCommonSizeArrays();

    T[][] to2DArray();

    List<T> getFirst();

    List<T> getSecond();

    List<T> getRest();

    List<T> getShortest();

    List<T> getLongest();

}
