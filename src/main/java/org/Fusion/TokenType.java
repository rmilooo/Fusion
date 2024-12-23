package org.Fusion;

// TokenType Enum
public enum TokenType {
    KEYWORD,     // Reserved words (like print, if, else)
    IDENTIFIER,  // Variable or function names
    STRING,      // String literals (e.g., "Hello, world!")
    NUMBER,      // Numeric values (e.g., 42)
    OPERATOR,    // Operators (e.g., +, =, ==)
    PUNCTUATION, // Delimiters (e.g., ;, (), {})
    EOF          // End of file/input token
}
