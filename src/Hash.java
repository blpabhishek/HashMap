import java.util.Objects;

public class Hash <K,V>{
    private int size;
    private static final int DEFAULT_SIZE=100;
    private Pair [] table;
    private int current_size=0;

    public Hash() {
        this(DEFAULT_SIZE);
    }

    public Hash(int size) {
        this.size = size;
        table = new Pair[size];
    }

    public boolean put(K key,V value){
        int hash = hashFunction(key);
        int index = getIndex(hash);
        System.out.println("hash = " + index);
        setPair(hash,key,value,index);
        return true;
    }

    public V get(K key){
        Pair<K, V> pair = getPair(key);
        return pair.value;
    }

    public int size(){
        return current_size;
    }

    private int hashFunction(Object key){
        return (key.hashCode()) >>> 7;
    }

    private int getIndex(int hash) {
        return hash%size;
    }

     private void setPair(int hash, K key, V value, int index) {
        Pair<K,V> e = table[index];
         if(e!=null)
             throw new RuntimeException("Collision during insertion of "+size()+" record.");
        table[index] = new Pair<>(hash, key, value);
        current_size++;
    }

    private Pair<K,V> getPair(Object key) {
        int hash = hashFunction(key);
        int index = getIndex(hash);
        return table[index];
    }

    @Override
    public String toString() {
        String string = new String("");
        for (V o : (V[])table) {
            if(o !=null)
                string += o.toString()+",";
        }
        return "{"+string.substring(0,string.length()-1)+"}";
    }
    private class Pair<K,V>{
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

        public final int hashCode() {
            return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
        }

        @Override
        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }
}
