class Pair<K,V>{
    final K key;
    V value;
    int hash;
    Pair(int h, K k, V v) {
        value = v;
        key = k;
        hash = h;
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
