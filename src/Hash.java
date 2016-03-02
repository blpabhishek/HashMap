public class Hash<K, V> {
    private int capacity;
    private int size = 0;
    private static final int DEFAULT_SIZE = 32;
    private static final int DEFAULT_LOAD = 8;
    private Pair[] table = (Pair<K, V>[]) new Pair[0];
    private int load;

    public Hash() {
        this(DEFAULT_SIZE, DEFAULT_LOAD);
    }

    public Hash(int capacity, int load) {
        if (capacity < 0 || load < 0)
            throw new IllegalArgumentException("Can not initalize the Hash with " + capacity + " and " + load);
        this.capacity = capacity;
        this.load = load;
        table = new Pair[capacity];
    }

    public V put(K key, V value) {
        int hash = hashFunction(key);
        int index = getIndex(hash);
        Pair<K, V> pair = table[index];
        if (pair != null && (pair.key == key || pair.key.equals(key))) {
            V oldValue = pair.value;
            pair.value = value;
            return oldValue;
        }
        setPair(hash, key, value, index);
        return value;
    }

    public V get(K key) {
        Pair<K, V> pair = getPair(key);
        if (pair == null)
            throw new ValueNotFoundException();
        return pair.value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        table = new Pair[capacity];
        size = 0;
    }

    public V remove(Object key){
        int hash = hashFunction(key);
        int index = getIndex(hash);
        Pair<K, V> pair = null;
        for (; index < capacity; index++) {
            pair = table[index];
            if (pair != null && (pair.key == key || pair.key.equals(key)))
                break;
        }
        if (pair == null)
            throw new ValueNotFoundException();
        table[index] = null;
        size--;
        return  pair.value;
    }

    private int hashFunction(Object key) {
        if (key == null)
            return 0;
        String str = key.toString();
        int summation = 0;
        for (int i = 0; i < str.length(); i++)
            summation += str.charAt(i);
        return summation;
    }

    private int getIndex(int hash) {
        return hash % capacity;
    }



    private void setPair(int hash, K key, V value, int index) {
        Pair<K, V> e = table[index];
        while (e != null) {
            index++;
            if (index >= capacity) {
                resizeTable();
                System.out.println("Be careful it is going recursive");
                put(key, value);
                return;
            }
            e = table[index];
        }
        table[index] = new Pair<>(key, value);
        size++;
    }

    private void resizeTable() {
        Pair[] oldTable = table;
        int oldCapacity = oldTable.length;
        int newCapacity = oldCapacity + load;
        Pair[] newTable = new Pair[newCapacity];
        size = 0;
        capacity = newCapacity;
        transfer(oldTable, newTable);
        table = newTable;
    }

    private void transfer(Pair[] oldTable, Pair[] newTable) {
        for (Pair<K, V> e : oldTable) {
            if (e != null) {
                int hash = hashFunction(e.key);
                int index = getIndex(hash);
                newTable[index] = new Pair<>(e.key, e.value);
                size++;
            }
        }
    }

    private Pair<K, V> getPair(Object key) {
        int hash = hashFunction(key);
        for (int index = getIndex(hash); index < capacity; index++) {
            Pair<K, V> pair = table[index];
            if (pair != null && (pair.key == key || pair.key.equals(key)))
                return pair;
        }
        return null;
    }

    @Override
    public String toString() {
        String string = new String("");
        if (size == 0)
            return "{" + string + "}";
        for (Pair o : (Pair[]) table) {
            if (o != null) {
                string += o.toString() + ",";
            }
        }
        return "{" + string.substring(0, string.length() - 1) + "}";
    }
}