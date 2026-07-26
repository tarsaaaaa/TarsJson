import elements.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;

public class JsonWriter {
    public JsonWriter() {

    }

    public String write(JsonElement element) {
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
