package de.holube.vac.stream;

public abstract non-sealed class MappingOp<I, O> implements Op<I, O> {

    protected final Downstream<I, O> downstream = new Downstream<>();

    public final Downstream<I, O> getDownstream() {
        return downstream;
    }

}
