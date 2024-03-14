package de.holube.vac.collections;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class ListStreams {

    public static <T> SimpleListsStream<T> of(@NonNull List<T> first, @NonNull List<T> second) {
        return new SimpleListsStream<>(new ArrayList<>(first), new ArrayList<>(second));
    }

}
