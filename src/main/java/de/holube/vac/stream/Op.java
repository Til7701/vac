package de.holube.vac.stream;

import de.holube.vac.collections.TwoDList;

public sealed interface Op<I, O> permits AbstractOp {

    TwoDList<O> performSequential(TwoDList<I> input);

    TwoDList<O> performParallel(TwoDList<I> input);

}
