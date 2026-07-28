import elements.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

/**
 * Serializes a {@link JsonElement} tree into valid JSON text.
 * <p>
 * Supports both compact and pretty-printed output.
 */
public class JsonWriter {
    public JsonWriter() {

    }

    private String getIndent(int depth) {
        return "    ".repeat(depth);
    }

    /**
     * Serializes the provided {@link JsonElement} into pretty-printed String.
     * @return The pretty-print formated string serialized from provided {@link JsonElement}
     */
    public String write(JsonElement element) {
        return writeValue(element, 1);
    }
    public String write(JsonElement element, boolean prettyPrint) {
        if (prettyPrint) return writeValue(element, 1);
        return writeValue(element);
    }

    private String writeValue(JsonElement element) {
        return switch (element) {
            case JsonObject object -> writeObject(object);
            case JsonArray array -> writeArray(array);
            case JsonNull _ -> writeNull();
            default -> writePrimitive((JsonPrimitive) element);
        };
    }
    private String writeValue(JsonElement element, int depth) {
        return switch (element) {
            case JsonObject object -> writeObject(object, depth);
            case JsonArray array -> writeArray(array, depth);
            case JsonNull _ -> writeNull();
            default -> writePrimitive((JsonPrimitive) element);
        };
    }

    private String writeObject(JsonObject jsonObject) {
        StringBuilder objString = new StringBuilder();
        objString.append("{");
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        boolean first = true;
        for (Map.Entry<String, JsonElement> entry : entries) {
            if(!first) objString.append(",");
            else first=false;

            objString.append("\"").append(entry.getKey()).append("\"");
            objString.append(":");
            objString.append(writeValue(entry.getValue()));
        }
        objString.append("}");
        return objString.toString();
    }
    private String writeObject(JsonObject jsonObject, int depth) {
        StringBuilder objString = new StringBuilder();
        objString.append("{\n");
        Set<Map.Entry<String, JsonElement>> entries = jsonObject.entrySet();
        boolean first = true;
        for (Map.Entry<String, JsonElement> entry : entries) {
            if(!first) objString.append(",\n");
            else first=false;

            objString.append(getIndent(depth)).append("\"").append(entry.getKey()).append("\" ");
            objString.append(": ");
            objString.append(writeValue(entry.getValue(), depth+1));
        }
        objString.append("\n").append(getIndent(depth-1)).append("}");
        return objString.toString();
    }
    private String writeArray(JsonArray jsonArray) {
        StringBuilder arrayString = new StringBuilder();
        arrayString.append("[");
        boolean first = true;

        for (int i = 0; i < jsonArray.size(); i++) {
            if(!first) arrayString.append(",");
            else first=false;

            arrayString.append(writeValue(jsonArray.get(i)));
        }
        arrayString.append("]");
        return arrayString.toString();
    }
    private String writeArray(JsonArray jsonArray, int depth) {
        StringBuilder arrayString = new StringBuilder();
        arrayString.append("[\n");
        boolean first = true;

        for (int i = 0; i < jsonArray.size(); i++) {
            if(!first) arrayString.append(",\n");
            else first=false;

            arrayString.append(getIndent(depth)).append(writeValue(jsonArray.get(i), depth+1));
        }
        arrayString.append("\n").append(getIndent(depth-1)).append("]");
        return arrayString.toString();
    }
    private String writePrimitive(JsonPrimitive primitive) {
        Object value = primitive.get();

        if (value instanceof String s) {
            return "\"" + s + "\"";
        }
        if (value instanceof Number) {
            return value.toString();
        }
        if (value instanceof Boolean) {
            return value.toString();
        }
        throw new IllegalStateException(
                "Unsupported primitive type: " + value.getClass().getName()
        );
    }
    private String writeNull() {
        return "null";
    }

    /**
     * Serializes and stored the provided {@link JsonElement} to the provided path.
     */
    public void writeToFile(Path path, JsonElement element) throws IOException {
        Files.writeString(path, write(element));
    }
    public void writeToFile(Path path, JsonElement element, boolean prettyPrint) throws IOException {
        Files.writeString(path, write(element, prettyPrint));
    }
}
