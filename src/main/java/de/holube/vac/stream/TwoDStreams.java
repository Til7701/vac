package de.holube.vac.stream;

import de.holube.vac.collections.TwoDArrayList;
import de.holube.vac.collections.TwoDList;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TwoDStreams {

    public static <T> TwoDStream<T> of(@NonNull TwoDList<T> matrix) {
        return new SimpleTwoDStream<>(new TwoDArrayList<>(matrix), false);
    }

}
