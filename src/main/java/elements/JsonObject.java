package elements;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a JSON object containing key-value pairs.
 * <p>
 * The insertion order of keys is preserved.
 */
public class JsonObject extends JsonElement{
    private final Map<String, JsonElement> values = new LinkedHashMap<>();

    /**
     * Adds or replaces a value in this object.
     *
     * @param key the property name
     * @param value the value to associate with the key
     */
    public void put(String key, JsonElement value) {
        this.values.put(key, value);
    }

    /**
     * Overloaded methods for different supported primitive types.
     * @param key the property name
     * @param value the value to associate with the key
     */
    public void put(String key, String value) {
        put(key, new JsonPrimitive(value));
    }
    public void put(String key, int value) {
        put(key, new JsonPrimitive(value));
    }
    public void put(String key, long value) {
        put(key, new JsonPrimitive(value));
    }
    public void put(String key, double value) {
        put(key, new JsonPrimitive(value));
    }
    public void put(String key, boolean value) {
        put(key, new JsonPrimitive(value));
    }

    /**
     * Returns the value associated with the specified key.
     *
     * @param key the property name
     * @return the corresponding {@link JsonElement}, or {@code null}
     * if the key does not exist
     */
    public JsonElement get(String key) {
        return this.values.get(key);
    }

    /**
     * @return All keys of the object as a {@code Set<String>}
     */
    public Set<String> getKeySet() {
        return values.keySet();
    }

    /**
     * @return All entry of the object as a {@code Set<Map.Entry<String, JsonElement>>}
     */
    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return values.entrySet();
    }

    /**
     * Checks if the specific key exists in the object.
     * @param key the property name
     * @return {@code true} if key exists in the object otherwise {@code false}
     */
    public boolean contains(String key) {
        return this.values.containsKey(key);
    }

    /**
     * Removes the value associated with the specific key from the object.
     * @param key the property name
     */
    public void remove(String key) {
        this.values.remove(key);
    }
}
