class Pair<K,V>{
    final K key;
    V value;
    Pair(K k, V v) {
        value = v;
        key = k;

    }
    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
}
