package org.Fusion.Compiler;

import org.Fusion.Lexer.Lexer;
import org.Fusion.Token.Token;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FusionCompiler {
    private final Lexer lexer;

    public FusionCompiler() {
        this.lexer = new Lexer();
    }

    public String generateJavaCode(String fusionCode) {
        List<Token> tokens = lexer.lex(fusionCode);
        StringBuilder javaCode = new StringBuilder();
        javaCode.append("public class FusionProgram {\n");
        javaCode.append("    public static void main(String[] args) {\n");

        for (Token token : tokens) {
            switch (token.getTokenType()) {
                case KEYWORD:
                    if (token.getValue().equals("print")) {
                        javaCode.append("        System.out.println(");
                    }
                    break;
                case STRING:
                    javaCode.append(token.getValue());
                    javaCode.append(");\n");
                    break;
                case PUNCTUATION:
                    break; // Skip punctuation for now
                case IDENTIFIER:
                    // Handle identifiers if needed (e.g., variables)
                    break;
                case NUMBER:
                    // Handle numbers
                    break;
                case OPERATOR:
                    // Handle operators
                    break;
                default:
                    break;
            }
        }

        javaCode.append("    }\n");
        javaCode.append("}\n");
        return javaCode.toString();
    }

    public void compileAndRun(String fusionCode) {
        try {
            // Generate Java code from Fusion code
            String javaCode = generateJavaCode(fusionCode);

            // Write the generated Java code to a file
            Path javaFile = Paths.get("FusionProgram.java");
            Files.write(javaFile, javaCode.getBytes());

            // Compile the generated Java code
            Process compileProcess = new ProcessBuilder("javac", "FusionProgram.java").start();
            int compileExitCode = compileProcess.waitFor();

            if (compileExitCode != 0) {
                System.err.println("Compilation failed.");
                // Print error stream
                printProcessOutput(compileProcess);
                return;
            }

            // Run the compiled Java code
            Process runProcess = new ProcessBuilder("java", "FusionProgram").start();
            int runExitCode = runProcess.waitFor();
            if (runExitCode != 0) {
                System.err.println("Runtime execution failed.");
                // Print error stream
                printProcessOutput(runProcess);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Helper method to print process output
    private void printProcessOutput(Process process) throws IOException {
        // Print standard output
        String output = new String(process.getInputStream().readAllBytes());
        System.out.println("Output: " + output);

        // Print error output
        String errorOutput = new String(process.getErrorStream().readAllBytes());
        System.err.println("Error: " + errorOutput);
    }
}