package elements;

public class JsonPrimitive extends JsonElement {
    private final Object value;

    public JsonPrimitive(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }
}
