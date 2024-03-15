package de.holube.vac.stream;

public abstract non-sealed class NonMappingOp<T> implements Op<T, T> {

    protected final Downstream<T, T> downstream = new Downstream<>();

    public final Downstream<T, T> getDownstream() {
        return downstream;
    }

}
