package org.Fusion.Compiler;

import org.Fusion.HelperFunctions.GetVarType;
import org.Fusion.Lexer.Lexer;
import org.Fusion.Token.Token;
import org.Fusion.Token.TokenType;

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
        System.out.println("=== Starting Code Generation ===");
        List<Token> tokens = lexer.lex(fusionCode);
        System.out.println("Tokens generated from Fusion code:\n" + tokens + "\n");

        StringBuilder javaCode = new StringBuilder();
        javaCode.append("public class FusionProgram {\n");
        javaCode.append("    public static void main(String[] args) {\n");

        int index = 0;
        for (Token token : tokens) {
            System.out.println("Processing token: " + token);
            switch (token.getTokenType()) {
                case KEYWORD:
                    if ("print".equals(token.getValue())) {
                        javaCode.append("        System.out.println");
                        System.out.println("-> Added print statement.");
                    }
                    else if ("let".equalsIgnoreCase(token.getValue().trim())) {
                        if (index + 2 < tokens.size()
                                && tokens.get(index + 2).getTokenType() == TokenType.OPERATOR
                                && "=".equals(tokens.get(index + 2).getValue())) {
                            String varType = GetVarType.getVariableType(tokens.get(index + 3).getValue());
                            javaCode.append(varType).append(" ");
                        }
                    }else if ("when".equalsIgnoreCase(token.getValue().trim())) {
                        javaCode.append("if");
                    }
                    break;
                case IDENTIFIER:
                    javaCode.append(token.getValue());
                    System.out.println("-> Added identifier: " + token.getValue());
                    break;
                case STRING:
                    javaCode.append("\"").append(token.getValue().replace("\"", "")).append("\"");
                    System.out.println("-> Added string: " + token.getValue());
                    break;
                case NUMBER:
                    javaCode.append(token.getValue());
                    System.out.println("-> Added number: " + token.getValue());
                    break;
                case OPERATOR:
                case PUNCTUATION:
                    javaCode.append(token.getValue());
                    System.out.println("-> Added operator/punctuation: " + token.getValue());
                    break;
                default:
                    javaCode.append(token.getValue());
                    System.out.println("-> Added default token: " + token.getValue());
                    break;
            }
            index++;
        }

        javaCode.append("\n    }\n");
        javaCode.append("}\n");
        System.out.println("=== Code Generation Completed ===\n");
        return javaCode.toString();
    }

    public void compileAndRun(String fusionCode) {
        try {
            System.out.println("=== Starting Compilation Process ===");
            String javaCode = generateJavaCode(fusionCode);

            Path javaFile = Paths.get("FusionProgram.java");
            Files.write(javaFile, javaCode.getBytes());
            System.out.println("-> Java code written to FusionProgram.java.");

            System.out.println("-> Compiling the Java program...");
            Process compileProcess = new ProcessBuilder("javac", "FusionProgram.java").start();
            int compileExitCode = compileProcess.waitFor();

            if (compileExitCode != 0) {
                System.err.println("Compilation failed.");
                printProcessOutput(compileProcess);
                return;
            }
            System.out.println("-> Compilation successful.");

            System.out.println("-> Running the compiled Java program...");
            Process runProcess = new ProcessBuilder("java", "FusionProgram").start();
            printProcessOutput(runProcess);

            // Delete the generated files after execution
            deleteGeneratedFiles();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printProcessOutput(Process process) throws IOException {
        String output = new String(process.getInputStream().readAllBytes());
        if (!output.isEmpty()) {
            System.out.println("Program Output:\n" + output);
        }

        String errorOutput = new String(process.getErrorStream().readAllBytes());
        if (!errorOutput.isEmpty()) {
            System.err.println("Error Output:\n" + errorOutput);
        }
    }
    private void deleteGeneratedFiles() {
        try {
            Path javaFile = Paths.get("FusionProgram.java");
            Path classFile = Paths.get("FusionProgram.class");

            // Delete Java file
            if (Files.exists(javaFile)) {
                Files.delete(javaFile);
                System.out.println("-> Deleted FusionProgram.java.");
            }

            // Delete class file
            if (Files.exists(classFile)) {
                Files.delete(classFile);
                System.out.println("-> Deleted FusionProgram.class.");
            }
        } catch (IOException e) {
            System.err.println("Error deleting generated files: " + e.getMessage());
        }
    }
}
