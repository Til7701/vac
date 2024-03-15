package de.holube.vac.stream;

public sealed interface Op<I, O> permits MappingOp, NonMappingOp {

    Downstream<I, O> getDownstream();

    void performSequential(I[][] input);

    void performParallel(I[][] input);

}
