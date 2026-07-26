package elements;

import java.util.ArrayList;
import java.util.List;

public class JsonArray extends JsonElement {
    private final List<JsonElement> elements = new ArrayList<>();

    public JsonArray(List<JsonElement> elements) {
        this.elements.addAll(elements);
    }

    public JsonArray() {

    }

    public void add(JsonElement element) {
        this.elements.add(element);
    }

    public JsonElement get(int index) {
        return this.elements.get(index);
    }

    public int size() {
        return this.elements.size();
    }
}
