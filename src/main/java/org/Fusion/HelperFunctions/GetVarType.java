package org.Fusion.HelperFunctions;

public class GetVarType {

    // Method to detect and return the type of value as a String
    public static String getVariableType(String value) {
        if (value == null) {
            return "null";
        }

        // Check if it's a boolean
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return "boolean";
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
