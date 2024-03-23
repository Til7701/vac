package de.holube.vac.stream;

import de.holube.vac.collections.TwoDList;

abstract sealed class AbstractOp<I, O> implements Op<I, O> permits NonMappingOp, MappingOp {

    protected final Downstream<I, O> downstream = new Downstream<>();

    final Downstream<I, O> getDownstream() {
        return downstream;
    }

    @Override
    public TwoDList<O> performParallel(TwoDList<I> input) {
        return performSequential(input);
    }

}
