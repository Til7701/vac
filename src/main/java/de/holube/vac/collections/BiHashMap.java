package de.holube.vac.collections;

import java.util.HashMap;

public class BiHashMap<K, V> extends HashMap<K, V> implements BiMap<K, V> {

    public BiHashMap() {
        super();
    }

    public BiHashMap(int initialCapacity) {
        super(initialCapacity);
    }

}
