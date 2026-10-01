# JSON

A lightweight JSON parsing and serialization library written completely from scratch in Java.

This project was built to understand how JSON libraries work internally, implementing every stage of the parsing pipeline—from lexical analysis to serialization—without relying on external JSON libraries.

---

## Features

* 📖 Parse JSON strings into an object tree
* ✍️ Serialize JSON objects back into JSON text
* 📁 Read and write JSON files
* 🌳 Recursive support for nested objects and arrays
* 🎨 Pretty-print JSON with indentation
* ⚡ Lightweight with zero external dependencies

---

## Project Structure

```
JSON Text
    │
    ▼
 Json.JsonLexer
    │
    ▼
   Tokens
    │
    ▼
 Json.JsonParser
    │
    ▼
 JsonElement Tree
    │
    ▼
 Json.JsonWriter
    │
    ▼
 JSON Text
```

The library is composed of several components:

| Component       | Description                                                             |
| --------------- | ----------------------------------------------------------------------- |
| `Json.JsonLexer`     | Converts raw JSON text into a stream of tokens.                         |
| `Json.JsonParser`    | Builds a tree of `JsonElement` objects using recursive descent parsing. |
| `JsonElement`   | Base class for every JSON value.                                        |
| `JsonObject`    | Represents JSON objects.                                                |
| `JsonArray`     | Represents JSON arrays.                                                 |
| `JsonPrimitive` | Represents strings, numbers, and booleans.                              |
| `JsonNull`      | Represents the JSON `null` value.                                       |
| `Json.JsonWriter`    | Serializes a `JsonElement` tree back into JSON text.                    |

---

## Supported JSON Types

* Objects
* Arrays
* Strings
* Numbers
* Booleans
* Null

---

## Usage

### Parsing JSON

```java
String json = """
{
    "name": "Alex",
    "age": 20,
    "admin": true
}
""";

JsonElement element = Json.Json.parse(json);

JsonObject object = (JsonObject) element;

System.out.println(object.get("name"));
```

---

### Creating JSON

```java
JsonObject object = new JsonObject();

object.put("name", new JsonPrimitive("Alex"));
object.put("age", new JsonPrimitive(20));
object.put("admin", new JsonPrimitive(true));
```

---

### Serialize

Compact:

```java
String json = Json.Json.stringify(object);
```

Pretty printed:

```java
String json = Json.Json.stringify(object, true);
```

Example output:

```json
{
    "name": "Alex",
    "age": 20,
    "admin": true
}
```

---

### Reading from a File

```java
JsonElement element = Json.Json.parse(Path.of("config.json"));
```

---

### Writing to a File

```java
Json.Json.write(Path.of("config.json"), object);
```

---

## Example

Input:

```json
{
    "player": {
        "name": "Steve",
        "health": 20,
        "inventory": [
            "Sword",
            "Shield"
        ]
    }
}
```

After parsing:

```
JsonObject
└── player
    └── JsonObject
        ├── name
        ├── health
        └── inventory
            └── JsonArray
```

---

## Why This Project?

Rather than using an existing JSON library, this project implements every stage of JSON processing manually to better understand how parsers and serializers work internally.

It serves as both a usable library and an educational reference for anyone interested in compiler design, recursive descent parsing, or language tooling.

---

## License

This project is licensed under the MIT License.
