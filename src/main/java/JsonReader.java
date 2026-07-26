import elements.JsonElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonReader {
    public JsonElement read(Path path) throws IOException {
        String json = Files.readString(path);
        return Json.parse(json);
    }
}
