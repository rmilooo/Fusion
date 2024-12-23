package org.Fusion.Token;

// Token Class
public class Token {
    private TokenType tokenType;  // Type of the token (KEYWORD, IDENTIFIER, etc.)
    private String value;         // The actual value of the token (e.g., 'print', '42')

    // Constructor
    public Token(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    // Getter for tokenType
    public TokenType getTokenType() {
        return tokenType;
    }

    // Getter for value
    public String getValue() {
        return value;
    }

    // Method to display the token as a string
    @Override
    public String toString() {
        return "Token(" + tokenType + ", " + value + ")";
    }
}
