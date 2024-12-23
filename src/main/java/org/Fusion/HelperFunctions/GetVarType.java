package org.Fusion.HelperFunctions;

public class GetVarType {

    // Method to detect and return the type of a value as a String
    public static String getVariableType(String value) {
        if (value == null) {
            return "null";
        }

        // Check if it's a boolean
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return "boolean";
        }

        // Check if it's a byte
        try {
            Byte.parseByte(value);
            return "byte";  // It's a byte
        } catch (NumberFormatException e) {
            // Not a byte, continue checking
        }

        // Check if it's a short
        try {
            Short.parseShort(value);
            return "short";  // It's a short
        } catch (NumberFormatException e) {
            // Not a short, continue checking
        }

        // Check if it's an integer
        try {
            Integer.parseInt(value);
            return "int";  // It's an integer
        } catch (NumberFormatException e) {
            // Not an integer, continue checking
        }

        // Check if it's a long
        try {
            Long.parseLong(value);
            return "long";  // It's a long
        } catch (NumberFormatException e) {
            // Not a long, continue checking
        }

        // Check if it's a float
        try {
            Float.parseFloat(value);
            return "float";  // It's a float
        } catch (NumberFormatException e) {
            // Not a float, continue checking
        }

        // Check if it's a double
        try {
            Double.parseDouble(value);
            return "double";  // It's a double
        } catch (NumberFormatException e) {
            // Not a double, continue checking
        }

        // If it's none of the above, it's a string
        return "String";
    }
}
