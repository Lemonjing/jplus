package com.ryan.collection;

import com.ryan.util.Parameters;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A {@code ChainMap} is a {@link Map} implementation that groups multiple
 * {@code Map}s together. Lookups sequentially walk trough all the underlying
 * {@code Map}s until the value for the looked up key is found. Writes and
 * updates only modify the first {@code Map}. Deletions are applied on all
 * underlying {@code Map}s. Note that only references to the input {@code Map}s
 * are stored, so, if one of these {@code Map}s gets updated, those changes will
 * also be reflected in the {@code ChainMap}. Not thread safe.
 *
 * @param <K> the type of the {@code Map}'s keys.
 * @param <V> the type of the {@code Map}'s values.
 * @author Osman KOCAK
 */
public final class ChainMap<K, V> extends AbstractMap<K, V> {
    private final List<Map<K, V>> maps;

    /**
     * Creates a new {@code ChainMap} from the given {@code Map}s.
     *
     * @param maps the input {@code Map}s.
     * @throws NullPointerException     if {@code maps} is {@code null} or if it
     *                                  contains a {@code null} reference.
     * @throws IllegalArgumentException if {@code maps} is empty.
     */
    public ChainMap(Map<K, V>... maps) {
        Parameters.checkCondition(maps.length > 0);
        this.maps = ImmutableList.copyOf(maps);
        for (Map<K, V> map : this.maps) {
            Parameters.checkNotNull(map);
        }
    }

    /**
     * Creates a new {@code ChainMap} from the given {@code Map}s.
     *
     * @param maps the input {@code Map}s.
     * @throws NullPointerException     if {@code maps} is {@code null} or if it
     *                                  contains a {@code null} reference.
     * @throws IllegalArgumentException if {@code maps} is empty.
     */
    public ChainMap(Iterable<? extends Map<K, V>> maps) {
        this.maps = ImmutableList.copyOf(maps);
        for (Map<K, V> map : this.maps) {
            Parameters.checkNotNull(map);
        }
        Parameters.checkCondition(!this.maps.isEmpty());
    }

    @Override
    public int size() {
        return keySet().size();
    }

    @Override
    public boolean isEmpty() {
        for (Map<K, V> map : maps) {
            if (!map.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        for (Map<K, V> map : maps) {
            if (map.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Map<K, V> map : maps) {
            if (map.containsValue(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for (Map<K, V> map : maps) {
            if (map.containsKey(key)) {
                return map.get(key);
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        return maps.get(0).put(key, value);
    }

    @Override
    public V remove(Object key) {
        V value = get(key);
        for (Map<K, V> map : maps) {
            map.remove(key);
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        maps.get(0).putAll(m);
    }

    @Override
    public void clear() {
        for (Map<K, V> map : maps) {
            map.clear();
        }
    }

    @Override
    public Set<K> keySet() {
        ImmutableSet.Builder<K> keys = new ImmutableSet.Builder<K>();
        for (Map<K, V> map : maps) {
            keys.add(map.keySet());
        }
        return keys.build();
    }

    @Override
    public Collection<V> values() {
        ImmutableList.Builder<V> values = new ImmutableList.Builder<V>();
        for (K key : keySet()) {
            values.add(get(key));
        }
        return values.build();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        ImmutableSet.Builder<Entry<K, V>> entries =
                new ImmutableSet.Builder<Entry<K, V>>();
        for (K key : keySet()) {
            entries.add(new SimpleEntry<K, V>(key, get(key)));
        }
        return entries.build();
    }
}
