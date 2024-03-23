package de.holube.vac.stream;

import de.holube.vac.collections.TwoDList;
import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public class FilterElementsOp<T> extends NonMappingOp<T> {

    private final Predicate<T> predicate;

    @Override
    public TwoDList<T> performSequential(TwoDList<T> input) {
        for (int i = 0; i < input.size(0); i++) {
            input.get(i).removeIf(predicate.negate());
        }
        return input;
    }

}
