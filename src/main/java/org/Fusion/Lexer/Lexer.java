package org.Fusion.Lexer;

import org.Fusion.Token.Token;
import org.Fusion.Token.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Lexer Class
public class Lexer {
    // Define patterns for different token types
    private static final String KEYWORDS = "\\b(print|if|else|while|function|let|when)\\b"; // Keywords with word boundaries
    private static final String OPERATORS = "=|\\+|\\-|\\*|/|==|!=";  // Operators
    private static final String PUNCTUATIONS = "\\;|\\{|\\}|\\(|\\)|\\.";  // Punctuations, including '.'
    private static final String IDENTIFIER = "\\b[a-zA-Z_][a-zA-Z0-9_]*\\b";  // Identifiers
    private static final String NUMBER = "\\b\\d+\\b";  // Numbers
    private static final String STRING = "\"[^\"]*\"";  // Strings enclosed in double quotes

    // Combined regex for matching tokens
    private static final Pattern TOKEN_PATTERNS = Pattern.compile(
            String.join("|",
                    "(?<KEYWORD>" + KEYWORDS + ")",
                    "(?<OPERATOR>" + OPERATORS + ")",
                    "(?<PUNCTUATION>" + PUNCTUATIONS + ")",
                    "(?<IDENTIFIER>" + IDENTIFIER + ")",
                    "(?<NUMBER>" + NUMBER + ")",
                    "(?<STRING>" + STRING + ")"
            )
    );

    /**
     * Processes the input code and returns a list of tokens.
     *
     * @param input The source code to tokenize.
     * @return List of tokens extracted from the input code.
     */
    public List<Token> lex(String input) {
        List<Token> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERNS.matcher(input);

        // Match tokens in the input
        while (matcher.find()) {
            if (matcher.group("KEYWORD") != null) {
                tokens.add(new Token(TokenType.KEYWORD, matcher.group("KEYWORD")));
            } else if (matcher.group("OPERATOR") != null) {
                tokens.add(new Token(TokenType.OPERATOR, matcher.group("OPERATOR")));
            } else if (matcher.group("PUNCTUATION") != null) {
                tokens.add(new Token(TokenType.PUNCTUATION, matcher.group("PUNCTUATION")));
            } else if (matcher.group("IDENTIFIER") != null) {
                tokens.add(new Token(TokenType.IDENTIFIER, matcher.group("IDENTIFIER")));
            } else if (matcher.group("NUMBER") != null) {
                tokens.add(new Token(TokenType.NUMBER, matcher.group("NUMBER")));
            } else if (matcher.group("STRING") != null) {
                tokens.add(new Token(TokenType.STRING, matcher.group("STRING")));
            }
        }

        // Add an EOF token at the end of the token list
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
