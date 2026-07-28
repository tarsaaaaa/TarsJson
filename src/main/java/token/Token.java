package token;

/**
 * Represents a Token Object containing:
 * <p>
 * {@code Type of the token}
 * <p>
 * {@code Lexme}
 * <p>
 * {@code Line and Column of the token}
 */
public class Token {
    private final TokenType type;
    private final String lexeme;
    private final int line;
    private final int column;

    /**
     * Initializes a new {@link Token} Object.
     */
    public Token(TokenType type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    /**
     * @return The type of {@link Token} Object
     */
    public TokenType getType() {
        return this.type;
    }
    /**
     * @return The String lexeme of {@link Token} Object
     */
    public String getLexeme() {
        return this.lexeme;
    }
    /**
     * @return The line at which {@link Token} lies
     */
    public int getLine() {
        return this.line;
    }
    /**
     * @return The column at which {@link Token} lies
     */
    public int getCol() {
        return this.column;
    }
}
