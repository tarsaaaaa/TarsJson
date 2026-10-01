package Json;

import exceptions.JsonParseException;
import token.Token;
import token.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Converts raw JSON text into a sequence of lexical tokens.
 * <p>
 * The lexer recognizes JSON punctuation, strings, numbers, booleans,
 * and null literals while tracking line and column numbers for
 * error reporting.
 */
public class JsonLexer {
    private final String source;
    private int index;
    private int line;
    private int column;

    /**
     * Initializes a new {@link JsonLexer} Object.
     */
    public JsonLexer(String source) {
        this.source = source;
        this.index = 0;
        this.line = 1;
        this.column = 1;
    }

    /**
     * Lex-es the JSON string.
     * @return The list of tokens {@code List<Token>} lex-ed from source JSON string
     */
    public List<Token> lex() {
        List<Token> tokens = new ArrayList<>();

        while (!isAtEnd()) {
            char ch = advance();
            switch (ch) {
                case ' ':
                case '\t':
                case '\r':
                case '\n':
                    break;

                case '{':
                    tokens.add(new Token(TokenType.LEFT_BRACE, "{", line, column));
                    break;
                case '}':
                    tokens.add(new Token(TokenType.RIGHT_BRACE, "}", line, column));
                    break;
                case '[':
                    tokens.add(new Token(TokenType.LEFT_SQUARE_BRACE, "[", line, column));
                    break;
                case ']':
                    tokens.add(new Token(TokenType.RIGHT_SQUARE_BRACE, "]", line, column));
                    break;
                case ':':
                    tokens.add(new Token(TokenType.COLON, ":", line, column));
                    break;
                case ',':
                    tokens.add(new Token(TokenType.COMMA, ",", line, column));
                    break;

                case '"':
                    tokens.add(readString());
                    break;

                default:
                    if (Character.isDigit(ch) || ch == '-') {
                        tokens.add(readNumber(ch));
                    }

                    else if (Character.isLetter(ch)) {
                        tokens.add(readKeyword(ch));
                    }

                    else {
                        throw new JsonParseException(
                                "Unable to identify token: " + ch + " @ln: " + line + " col: " + column
                        );
                    }

                    break;
            }
        }

        tokens.add(new Token(TokenType.EOF, "", line, column));
        return tokens;
    }

    private char peek() {
        if (index >= source.length()) return '\0';
        return source.charAt(this.index);
    }
    private char advance() {
        char c = source.charAt(index);
        index++;
        if (c == '\n') {
            line++;
            column = 1;
        } else column++;
        return c;
    }
    private boolean match(char c) {
        return c == this.source.charAt(index);
    }
    private boolean isAtEnd() {
        return index >= source.length();
    }
    private Token readString() {
        StringBuilder builder = new StringBuilder();
        while(peek() != '"') {
            builder.append(advance());
        }
        advance();
        return new Token(TokenType.STRING, builder.toString(), line, column);
    }
    private Token readNumber(char c) {
        StringBuilder builder = new StringBuilder();
        builder.append(c);
        while(Character.isDigit(peek())) {
            builder.append(advance());
        }
        return new Token(TokenType.NUMBER, builder.toString(), line, column);
    }
    private Token readKeyword(char ch) {
        StringBuilder builder = new StringBuilder();
        builder.append(ch);
        while(peek() != ',') {
            builder.append(advance());
        }
        String s = builder.toString();
        TokenType type = s.equals("true") ? TokenType.TRUE : s.equals("false") ? TokenType.FALSE : TokenType.NULL;
        return new Token(type, s, line, column);
    }
}
