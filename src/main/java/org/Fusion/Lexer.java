package org.Fusion;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Lexer Class
public class Lexer {
    private static final String KEYWORDS = "print|if|else|while|function";  // Add more keywords as needed
    private static final String OPERATORS = "=|\\+|\\-|\\*|/|==|!=";  // Add more operators as needed
    private static final String PUNCTUATIONS = "\\;|\\{|\\}|\\(|\\)";  // Add more punctuations as needed
    private static final String IDENTIFIER = "[a-zA-Z_][a-zA-Z0-9_]*";  // Identifiers start with a letter or underscore
    private static final String NUMBER = "\\d+";  // Numbers consist of digits
    private static final String STRING = "\"[^\"]*\"";  // Strings are enclosed in double quotes

    private static final Pattern TOKEN_PATTERNS = Pattern.compile(
        String.join("|",
            "(" + KEYWORDS + ")",
            "(" + OPERATORS + ")",
            "(" + PUNCTUATIONS + ")",
            "(" + IDENTIFIER + ")",
            "(" + NUMBER + ")",
            "(" + STRING + ")"
        )
    );

    public List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERNS.matcher(input);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(new Token(TokenType.KEYWORD, matcher.group(1)));
            } else if (matcher.group(2) != null) {
                tokens.add(new Token(TokenType.OPERATOR, matcher.group(2)));
            } else if (matcher.group(3) != null) {
                tokens.add(new Token(TokenType.PUNCTUATION, matcher.group(3)));
            } else if (matcher.group(4) != null) {
                tokens.add(new Token(TokenType.IDENTIFIER, matcher.group(4)));
            } else if (matcher.group(5) != null) {
                tokens.add(new Token(TokenType.NUMBER, matcher.group(5)));
            } else if (matcher.group(6) != null) {
                tokens.add(new Token(TokenType.STRING, matcher.group(6)));
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    public static void main(String[] args) {
        String sourceCode = "print(\"Hello, world!\"); x = 42;";

        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.lex(sourceCode);

        // Print out all the tokens
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}