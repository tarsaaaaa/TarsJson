import elements.JsonElement;

import java.io.IOException;
import java.nio.file.Path;

public class Json {
    static void main() throws IOException {

    }

    public static JsonElement parse(String jsonString) {
        JsonLexer lexer = new JsonLexer(jsonString);
        JsonParser parser = new JsonParser(lexer.lex());
        return parser.parse();
    }
    public static JsonElement parse(Path path) throws IOException {
        JsonReader reader = new JsonReader();
        return reader.read(path);
    }
    public static String stringify(JsonElement element) {
        JsonWriter writer = new JsonWriter();
        return writer.write(element);
    }
    public static void write(Path path, JsonElement element) throws IOException {
        JsonWriter writer = new JsonWriter();
        writer.writeToFile(path, element);
    }
}
