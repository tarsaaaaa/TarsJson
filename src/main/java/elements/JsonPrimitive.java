package elements;

/**
 * Represents a JSON primitive value containing primitive value such as:
 * {@code int}, {@code String}, {@code long}, {@code double}
 */
public class JsonPrimitive extends JsonElement {
    private final Object value;

    /**
     * Initializes a new {@link JsonPrimitive} object with a value
     * @param value initialization value
     */
    public JsonPrimitive(Object value) {
        this.value = value;
    }

    /**
     * @return The value stored in JSON primitive as an {@code Object}
     */
    public Object get() {
        return value;
    }
}
