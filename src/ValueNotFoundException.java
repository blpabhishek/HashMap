public class ValueNotFoundException extends RuntimeException {
    public ValueNotFoundException() {
        super("Value Not Fond for the Specified Key");
    }
}
