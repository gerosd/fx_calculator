package com.example.gui_calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    static double calculate(String expression) {
        // Create two lists to hold the numbers and operators
        List<Double> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        // Define a regular expression for matching numbers and operators
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?|[-+*/]");
        Matcher matcher = pattern.matcher(expression);

        // Parse the expression and extract numbers and operators
        while (matcher.find()) {
            String match = matcher.group();
            if (match.matches("-?\\d+(\\.\\d+)?")) {
                double num = Double.parseDouble(match);
                numbers.add(num);
            } else {
                char op = match.charAt(0);
                operators.add(op);
            }
        }

        // Perform multiplication and division first, then addition and subtraction
        performOperation(numbers, operators, '*', '/');
        performOperation(numbers, operators, '+', '-');

        // Return the final result, which should be the only element left in the 'numbers' list
        return numbers.get(0);
    }

    // Helper function to perform an operation on two numbers
    private static double performOperation(double num1, double num2, char op) {
        return switch (op) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> num1 / num2;
            default -> throw new IllegalArgumentException("Invalid operator: " + op);
        };
    }

    // Helper function to perform all operations of a given type (e.g. multiplication and division)
    // This is called twice, once for multiplication/division and once for addition/subtraction
    private static void performOperation(List<Double> numbers, List<Character> operators, char op1, char op2) {
        for (int i = 0; i < operators.size(); i++) {
            char op = operators.get(i);
            if (op == op1 || op == op2) {
                // If the current operator is of the correct type, perform the operation on the corresponding numbers
                double num1 = numbers.get(i);
                double num2 = numbers.get(i + 1);
                double result = performOperation(num1, num2, op);
                // Replace the first number with the result and remove the second number and operator from their lists
                numbers.set(i, result);
                numbers.remove(i + 1);
                operators.remove(i);
                i--; // We need to decrement i since we just removed an element from the 'operators' list
            }
        }
    }
}
