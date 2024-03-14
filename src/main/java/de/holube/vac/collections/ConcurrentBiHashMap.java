package de.holube.vac.collections;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentBiHashMap<K, V> extends ConcurrentHashMap<K, V> implements BiMap<K, V> {

    public ConcurrentBiHashMap() {
        super();
    }

    public ConcurrentBiHashMap(int initialCapacity) {
        super(initialCapacity);
    }

}
