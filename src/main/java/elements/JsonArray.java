package elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a JSON array containing multiple values.
 * <p>
 * Allows random access via index.
 */
public class JsonArray extends JsonElement {
    private final List<JsonElement> elements = new ArrayList<>();

    /**
     * Initializes an empty {@link JsonArray}
     */
    public JsonArray() {

    }

    /**
     * Inserts a {@link JsonElement} to the next array index.
     * @param element input {@link JsonElement}
     */
    public void add(JsonElement element) {
        this.elements.add(element);
    }

    /**
     * @param index
     * @return The array value at that specific index
     */
    public JsonElement get(int index) {
        return this.elements.get(index);
    }

    /**
     * @return The size of JSON array
     */
    public int size() {
        return this.elements.size();
    }
}
