package org.Fusion;

import org.Fusion.Compiler.FusionCompiler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length == 1) {
            File file = new File(args[0]);
            if (!file.exists() || !file.isFile()) {
                System.err.println("Error: File not found: " + args[0]);
                return;
            }
            String code = readFile(file);
            if (code == null) {
                System.err.println("Error: Unable to read file.");
                return;
            }
            FusionCompiler compiler = new FusionCompiler();
            compiler.compileAndRun(code);
        } else {
            System.err.println("Usage: java TestClass <path-to-fusion-file>");
        }
    }

    private static String readFile(File file) {
        try {
            return Files.readString(file.toPath());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
