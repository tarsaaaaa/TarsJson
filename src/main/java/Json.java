import elements.JsonElement;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Main api gateway class.
 */
public class Json {
    static void main() {
    }

    /**
     * Parses the provided JSON string.
     *
     * @param jsonString the JSON text to parse
     * @return the root {@link JsonElement}
     */
    public static JsonElement parse(String jsonString) {
        JsonLexer lexer = new JsonLexer(jsonString);
        JsonParser parser = new JsonParser(lexer.lex());
        return parser.parse();
    }

    /**
     * Parses the .json file from provided {@code path}.
     *
     * @param path path to the .json file
     * @return the root {@link JsonElement}
     */
    public static JsonElement parse(Path path) throws IOException {
        JsonReader reader = new JsonReader();
        return reader.read(path);
    }

    /**
     * Serializes the provided {@link JsonElement} tree into valid JSON text.
     * @param element the {@link JsonElement} to serialize
     * @return Pretty-printed JSON text
     */
    public static String stringify(JsonElement element) {
        JsonWriter writer = new JsonWriter();
        return writer.write(element);
    }

    /**
     * Serializes the provided {@link JsonElement} tree into valid JSON text.
     * Allows disabling the pretty-printed output formatting.
     * @param element the {@link JsonElement} to serialize
     * @param prettyPrint {@code enabling/disabling} pretty-printed output formatting
     * @return JSON text with selected formatting option
     */
    public static String stringify(JsonElement element, boolean prettyPrint) {
        JsonWriter writer = new JsonWriter();
        return writer.write(element, prettyPrint);
    }

    /**
     * Creates and writes the serialized {@link JsonElement} tree to a file.
     * With pretty-printed formatting.
     * @param path path where the file is created and stored
     * @param element the {@link JsonElement} to serialize and store
     */
    public static void write(Path path, JsonElement element) throws IOException {
        JsonWriter writer = new JsonWriter();
        writer.writeToFile(path, element);
    }

    /**
     * Creates and writes the serialized {@link JsonElement} tree to a file.
     * Allows disabling pretty-printed formatting.
     * @param path path where the file is created and stored
     * @param element the {@link JsonElement} to serialize and store
     * @param prettyPrint {@code enabling/disabling} pretty-printed output formatting
     */
    public static void write(Path path, JsonElement element, boolean prettyPrint) throws IOException {
        JsonWriter writer = new JsonWriter();
        writer.writeToFile(path, element, prettyPrint);
    }
}
