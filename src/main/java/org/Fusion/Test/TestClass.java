package org.Fusion.Test;

import org.Fusion.Lexer;
import org.Fusion.Token;

import java.util.List;

public class TestClass {

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
