package token;

public class Token {
    private final TokenType type;
    private final String lexeme;
    private final int line;
    private final int column;

    public Token(TokenType type, String lexeme, int line, int column) {
        this.type = type;
        this.lexeme = lexeme;
        this.line = line;
        this.column = column;
    }

    public TokenType getType() {
        return this.type;
    }
    public String getLexeme() {
        return this.lexeme;
    }
    public int getLine() {
        return this.line;
    }
    public int getCol() {
        return this.column;
    }
}
