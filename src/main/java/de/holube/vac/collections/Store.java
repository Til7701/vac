package de.holube.vac.collections;

import lombok.NonNull;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.Callable;

public class Store<V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private final BiMap<UUID, V> map;

    public Store() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public Store(int initialCapacity) {
        this(() -> new BiHashMap<>(initialCapacity));
    }

    public Store(@NonNull Callable<BiMap<UUID, V>> mapFactory) {
        try {
            map = mapFactory.call();
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to create map", e);
        }
    }

    public UUID create(V channel) {
        final UUID id = UUID.randomUUID();
        map.put(id, channel);
        return id;
    }

    public UUID createUnique(V channel) {
        UUID id;
        do {
            id = UUID.randomUUID();
        } while (map.containsKey(id));
        map.put(id, channel);
        return id;
    }

    public Optional<V> get(UUID uuid) {
        return Optional.ofNullable(map.get(uuid));
    }

    public Optional<UUID> get(V value) {
        return Optional.ofNullable(map.getByValue(value));
    }

    public void remove(UUID uuid) {
        map.remove(uuid);
    }

    public void remove(V value) {
        map.removeByValue(value);
    }

    public Collection<V> getAll() {
        return map.values();
    }

    public Collection<V> get(@NonNull Collection<UUID> uuids) {
        return map.getForKeys(uuids);
    }

}
