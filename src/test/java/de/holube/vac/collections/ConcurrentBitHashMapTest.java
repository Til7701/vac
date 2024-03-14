package de.holube.vac.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConcurrentBitHashMapTest {

    ConcurrentBiHashMap<Key, Value> map;

    Key defaultKey;
    Value defaultValue;

    @BeforeEach
    void init() {
        map = new ConcurrentBiHashMap<>();
        defaultKey = new Key(0);
        defaultValue = new Value(1);
    }

    @Test
    void sizeTest() {
        assertEquals(0, map.size());
    }

    @Test
    void containsKeyTest() {
        map.put(defaultKey, defaultValue);
        assertTrue(map.containsKey(defaultKey));
    }

    private record Key(int id) {
    }

    private record Value(int id) {
    }

}
