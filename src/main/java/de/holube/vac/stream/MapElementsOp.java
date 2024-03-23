package de.holube.vac.stream;

import de.holube.vac.collections.TwoDArrayList;
import de.holube.vac.collections.TwoDList;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
class MapElementsOp<I, O> extends MappingOp<I, O> {

    private final Function<I, O> function;

    @Override
    public TwoDList<O> performSequential(TwoDList<I> input) {
        TwoDList<O> output = new TwoDArrayList<>();
        Iterator<List<I>> iterator = input.listIterator();
        while (iterator.hasNext()) {
            List<I> row = iterator.next();
            List<O> newRow = row.stream().map(function).toList();
            output.addList(newRow);
        }
        return output;
    }

}
