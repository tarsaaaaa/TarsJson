import elements.JsonElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Reads .json file fron the path and parses into valid {@link JsonElement} tree.
 */
public class JsonReader {
    /**
     * Reads and parses the file from provided path.
     * @param path Path to the .json file
     * @return Root {@link JsonElement} tree parsed from file.
     */
    public JsonElement read(Path path) throws IOException {
        String json = Files.readString(path);
        return Json.parse(json);
    }
}
