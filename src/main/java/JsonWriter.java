import elements.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class JsonWriter {
    public JsonWriter() {

    }

    private String getIndent(int depth) {
        return "    ".repeat(depth);
    }

    public String write(JsonElement element) {
        return writeValue(element, 1);
    }

    private String writeValue(JsonElement element, int depth) {
        return switch (element) {
            case JsonObject object -> writeObject(object, depth);
            case JsonArray array -> writeArray(array, depth);
            case JsonNull _ -> writeNull();
            default -> writePrimitive((JsonPrimitive) element);
        };
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

    public void writeToFile(Path path, JsonElement element) throws IOException {
        Files.writeString(path, write(element));
    }
}
