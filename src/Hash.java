import java.util.Objects;

public class Hash<K, V> {
    private int capacity;
    private int size = 0;
    private static final int DEFAULT_SIZE = 64;
    private final Pair<?, ?>[] EMPTY_TABLE = new Pair[0];
    private Pair[] table = (Pair<K, V>[]) EMPTY_TABLE;
    private int load;

    public Hash() {
        this(DEFAULT_SIZE, 32);
    }

    public Hash(int capacity, int load) {
        this.capacity = capacity;
        this.load = load;
    }

    public V put(K key, V value) {
        if (table == EMPTY_TABLE)
            initializeTable(capacity);
        int hash = hashFunction(key);
        int index = getIndex(hash);
        Pair<K, V> pair = table[index];
        if (pair != null && pair.hash == hash && pair.key == key) {
            V oldValue = pair.value;
            pair.value = value;
            return oldValue;
        }
        setPair(hash, key, value, index);
        return value;
    }

    public V get(K key) {
        Pair<K, V> pair = getPair(key);
        return pair.value;
    }

    public int size() {
        return size;
    }

    private int hashFunction(Object key) {
        String str = key.toString();
        int summation = 0;
        for (int i = 0; i < str.length(); i++)
            summation += str.charAt(i);
        return summation;
    }

    private int getIndex(int hash) {
        return hash % capacity;
    }

    private void initializeTable(int value) {
        table = new Pair[value];
    }

    private void setPair(int hash, K key, V value, int index) {
        Pair<K, V> e = table[index];
        while (e != null) {
            index++;
            if (index >= capacity) {
                resizeTable();
                System.out.println("Be careful it is going recursive");
                put(key,value);
                return;
            }
            e = table[index];
        }
        table[index] = new Pair<>(hash, key, value);
        size++;
    }

    private void resizeTable() {
        Pair[] oldTable = table;
        int oldCapacity = oldTable.length;
        int newCapacity = oldCapacity + load;
        Pair[] newTable = new Pair[newCapacity];
        size = 0;
        capacity = newCapacity;
        transfer(oldTable,newTable);
        table = newTable;
    }

    private void transfer(Pair[] oldTable, Pair[] newTable) {
        for (Pair<K,V> e : oldTable) {
            if (e != null) {
                int hash = hashFunction(e.key);
                int index = getIndex(hash);
                newTable[index] = new Pair<>(hash, e.key, e.value);
                size++;
            }
        }
    }


    private Pair<K, V> getPair(Object key) {
        int hash = hashFunction(key);
        int index = getIndex(hash);
        return table[index];
    }

    @Override
    public String toString() {
        String string = new String("");
        if(size == 0)
            return "{"+string+"}";
        for (Pair o : (Pair[]) table) {
            if (o != null) {
                string += o.toString() + ",";
            }
        }
        return "{" + string.substring(0, string.length() - 1) + "}";
    }
}
