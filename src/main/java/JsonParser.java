import elements.*;
import exceptions.JsonParseException;
import token.Token;
import token.TokenType;

import java.util.List;

public class JsonParser {
    private final List<Token> tokens;
    private int index;

    public JsonParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public JsonElement parse() {
        JsonElement element = parseValue();
        consume(TokenType.EOF);
        return element;
    }

    private JsonElement parseObject() {
        consume(TokenType.LEFT_BRACE);

        JsonObject obj = new JsonObject();

        while (!check(TokenType.RIGHT_BRACE)) {
            consume(TokenType.STRING);
            String key = previous().getLexeme();
            consume(TokenType.COLON);
            JsonElement value = parseValue();
            obj.put(key, value);

            if (!match(TokenType.COMMA)) break;
        }
        consume(TokenType.RIGHT_BRACE);
        return obj;
    }
    private JsonElement parseValue() {
        JsonElement value;
        if (check(TokenType.LEFT_SQUARE_BRACE)) value = parseArray();
        else if (check(TokenType.NULL)) value = parseNull();
        else if (check(TokenType.LEFT_BRACE)) value = parseObject();
        else value = parsePrimitive();

        return value;
    }
    private JsonArray parseArray() {
        JsonArray array = new JsonArray();
        consume(TokenType.LEFT_SQUARE_BRACE);
        while (!check(TokenType.RIGHT_SQUARE_BRACE)) {
            array.add(parseValue());

            if (!match(TokenType.COMMA)) break;
        }
        consume(TokenType.RIGHT_SQUARE_BRACE);
        return array;
    }
    private JsonPrimitive parsePrimitive() {
        Token token = advance();

        return switch (token.getType()) {
            case STRING -> new JsonPrimitive(token.getLexeme());
            case NUMBER -> parseNumber(token);
            case TRUE -> new JsonPrimitive(true);
            case FALSE -> new JsonPrimitive(false);
            default -> throw new JsonParseException(
                    "Unexpected token: " + token.getType()
            );
        };

    }
    private JsonPrimitive parseNumber(Token token) {
        String number = token.getLexeme();
        if (number.contains(".") || number.contains("e") || number.contains("E")) {
            return new JsonPrimitive(Double.parseDouble(number));
        }

        try {
            return new JsonPrimitive(Integer.parseInt(number));
        } catch (NumberFormatException _) {
            return new JsonPrimitive(Long.parseLong(number));
        }
    }
    private JsonElement parseNull() {
        consume(TokenType.NULL);
        return new JsonNull();
    }

    private Token peek() {
        return tokens.get(index);
    }
    private Token advance() {
        Token t = tokens.get(index);
        index++;
        return t;
    }
    private Token previous() {
        if (index == 0) return new Token(TokenType.NULL, "", 0, 0);
        return tokens.get(index-1);
    }
    private boolean isAtEnd() {
        return tokens.get(index).getType().equals(TokenType.EOF);
    }
    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return tokens.get(index).getType().equals(type);
    }
    private boolean match(TokenType type) {
        if (tokens.get(index).getType().equals(type)) {
            index++;
            return true;
        }
        return false;
    }
    private void consume(TokenType type) {
        if (tokens.get(index).getType().equals(type)) {
            index++;
        } else throw new JsonParseException(
               "Expected token: " + type + " but got: " + peek().getType()
        );
    }
}
