public class Hash <K,V>{
    private int size;
    private static final int DEFAULT_SIZE=100;
    private Object [] table;

    public Hash() {
        this(DEFAULT_SIZE);
    }

    public Hash(int size) {
        this.size = size;
        table = new Object[size];
    }

    public int hashFunction(K key){
        return (key.hashCode()*7)%size >>> 7;
    }

    public boolean put(K key,V value){
        int index= hashFunction(key);
        table[index] = value;
        size++;
        return true;
    }

    public V get(K key){
        int index= hashFunction(key);
        return (V)table[index];
    }

    @Override
    public String toString() {
        String string = new String("");
        for (Object o : table) {
            if (o instanceof Integer)
                string += o.toString()+",";
        }
        return "{"+string.substring(0,string.length()-1)+"}";
    }
}

