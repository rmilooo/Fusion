package org.Fusion.Test;

import org.Fusion.Compiler.FusionCompiler;
import org.Fusion.Lexer.Lexer;
import org.Fusion.Token.Token;

import java.util.List;

public class TestClass {

    public static void main(String[] args) {
        String code = "print(\"Hello, world!\"); x = 42; print(x + 10);";

        FusionCompiler compiler = new FusionCompiler();
        compiler.compileAndRun(code);
    }
}
