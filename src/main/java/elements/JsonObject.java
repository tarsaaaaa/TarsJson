package elements;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class JsonObject extends JsonElement{
    private final Map<String, JsonElement> values = new LinkedHashMap<>();

    public void put(String key, JsonElement value) {
        this.values.put(key, value);
    }

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

    public JsonElement get(String key) {
        return this.values.get(key);
    }

    public Set<String> getKeySet() {
        return values.keySet();
    }

    public Set<Map.Entry<String, JsonElement>> entrySet() {
        return values.entrySet();
    }

    public boolean contains(String key) {
        return this.values.containsKey(key);
    }

    public void remove(String key) {
        this.values.remove(key);
    }
}
