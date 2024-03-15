package de.holube.vac.stream;

import lombok.*;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode
public class SimpleListsStream<T> implements ListsStream<T> {

    private List<T> first;
    private List<T> second;

    @Override
    public ListsStream<T> forEach(@NonNull Consumer<T> consumer) {
        first.forEach(consumer);
        second.forEach(consumer);
        return this;
    }

    @Override
    public SimpleListsStream<T> forEachPair(@NonNull BiConsumer<T, T> consumer) {
        final int minSize = Math.min(first.size(), second.size());
        for (int i = 0; i < minSize; i++) {
            consumer.accept(first.get(i), second.get(i));
        }
        return this;
    }

    @Override
    public ListsStream<T> forEachRest(@NonNull Consumer<T> consumer) {
        final List<T> bigger = getRest();
        for (T t : bigger) {
            consumer.accept(t);
        }
        return this;
    }

    @Override
    public ListsStream<T> filter(@NonNull BiPredicate<T, T> filter) {
        final int minSize = Math.min(first.size(), second.size());
        int i = 0;
        while (i < minSize) {
            if (filter.test(first.get(i), second.get(i))) {
                first.remove(i);
                second.remove(i);
            } else {
                i++;
            }
        }
        return this;
    }

    @Override
    public ListsStream<T> filterEach(@NonNull Predicate<T> filter) {
        first.removeIf(filter);
        second.removeIf(filter);
        return this;
    }

    @Override
    public ListsStream<T> toCommonSize() {
        final int minSize = Math.min(first.size(), second.size());
        first = first.subList(0, minSize);
        second = second.subList(0, minSize);
        return this;
    }

    @Override
    public <R> ListsStream<R> map(@NonNull Function<Pair<T, T>, Pair<R, R>> mapper) {
        final int minSize = Math.min(first.size(), second.size());
        final List<R> newFirst = new ArrayList<>(minSize);
        final List<R> newSecond = new ArrayList<>(minSize);
        final MutablePair<T, T> pair = new MutablePair<>();
        for (int i = 0; i < minSize; i++) {
            pair.setLeft(first.get(i));
            pair.setRight(second.get(i));
            final Pair<R, R> newPair = mapper.apply(pair);
            newFirst.set(i, newPair.getLeft());
            newSecond.set(i, newPair.getRight());
        }
        return new SimpleListsStream<>(newFirst, newSecond);
    }

    @Override
    public <R> ListsStream<R> mapEach(@NonNull Function<T, R> mapper) {
        final List<R> firstMapped = new ArrayList<>();
        final List<R> secondMapped = new ArrayList<>();
        first.forEach(t -> firstMapped.add(mapper.apply(t)));
        second.forEach(t -> secondMapped.add(mapper.apply(t)));
        return new SimpleListsStream<>(firstMapped, secondMapped);
    }

    @Override
    public <R> Stream<R> combine(@NonNull BiFunction<T, T, R> combiner) {
        final List<R> newList = new ArrayList<>();
        final int minSize = Math.min(first.size(), second.size());
        for (int i = 0; i < minSize; i++) {
            newList.add(combiner.apply(first.get(i), second.get(i)));
        }
        return newList.stream();
    }

    @Override
    public Pair<List<T>, List<T>> toLists() {
        return new MutablePair<>(first, second);
    }

    @Override
    public Pair<List<T>, List<T>> toCommonSizeLists() {
        final int minSize = Math.min(first.size(), second.size());
        return new MutablePair<>(first.subList(0, minSize), second.subList(0, minSize));
    }

    @Override
    public List<T> concat() {
        final List<T> newList = new ArrayList<>(first);
        newList.addAll(second);
        return newList;
    }

    @Override
    public ListsStream<T> concat(@NonNull ListsStream<T> other) {
        first.addAll(other.getFirst());
        second.addAll(other.getSecond());
        return this;
    }

    @Override
    public ListsStream<T> concatEach(@NonNull List<T> list) {
        first.addAll(list);
        second.addAll(list);
        return this;
    }

    @Override
    public ListsStream<T> concatAlternating(@NonNull List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                first.add(list.get(i));
            } else {
                second.add(list.get(i));
            }
        }
        return this;
    }

    @Override
    public ListsStream<T> concatToShortest(@NonNull List<T> list) {
        getShortest().addAll(list);
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pair<T[], T[]> toArrays() {
        return new MutablePair<>((T[]) first.toArray(), (T[]) second.toArray());
    }

    @Override
    public Pair<T[], T[]> toCommonSizeArrays() {
        toCommonSize();
        return toArrays();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[][] to2DArray() {
        return (T[][]) new Object[][]{first.toArray(), second.toArray()};
    }

    @Override
    public List<T> getRest() {
        final List<T> bigger = getLongest();
        final int minSize = Math.min(first.size(), second.size());
        return bigger.subList(minSize, bigger.size());
    }

    @Override
    public List<T> getShortest() {
        return first.size() < second.size() ? first : second;
    }

    @Override
    public List<T> getLongest() {
        return first.size() > second.size() ? first : second;
    }

}
