package com.tsystems.javaschool.tasks.calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Calculator {
    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public static String evaluate(String statement) {
        ArrayList<Double> db = new ArrayList<>();
        ArrayList<Character> op = new ArrayList<>();
        try {
            for (int i = 0; i < statement.length(); i++) {
                char c = statement.charAt(i);
                if (isSpace(c))
                    continue;
                if (c == '(')
                    op.add('(');
                else if (c == ')') {
                    while (op.get(op.size() - 1) != '(')
                        processOperator(db, op.remove(op.size() - 1));
                    op.remove(op.size() - 1);
                } else if (isOperator(c)) {
                    while (!op.isEmpty() && priority(op.get(op.size() - 1)) >= priority(c))
                        processOperator(db, op.remove(op.size() - 1));
                    op.add(c);
                } else {
                    StringBuilder operand = new StringBuilder();
                    while (i < statement.length() && !isSpace(statement.charAt(i)) && !isOperator(statement.charAt(i))
                            && !isParentheses(statement.charAt(i))) {
                        operand.append(statement.charAt(i));
                        i++;
                    }
                    --i;
                    db.add(Double.parseDouble(operand.toString()));
                }
            }
            while (!op.isEmpty())
                processOperator(db, op.remove(op.size() - 1));
            Locale locale = new Locale("en");
            Locale.setDefault(locale);
            DecimalFormat df = new DecimalFormat("###.####");
            df.setRoundingMode(RoundingMode.CEILING);
            return (db.get(0) != null && !db.get(0).isInfinite() && !db.get(0).isNaN()) ? df.format(db.get(0)) : null;
        } catch (Exception e) {
            return null;
        }
    }

    static boolean isSpace(char c) {
        return c == ' ';
    }

    static boolean isParentheses(char c) {
        return c == '(' || c == ')';
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static int priority(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    static void processOperator(ArrayList<Double> numbers, char operator) {
        double r = numbers.remove(numbers.size() - 1);
        double l = numbers.remove(numbers.size() - 1);
        switch (operator) {
            case '+':
                numbers.add(l + r);
                break;
            case '-':
                numbers.add(l - r);
                break;
            case '*':
                numbers.add(l * r);
                break;
            case '/':
                numbers.add(l / r);
                break;
        }
    }
}
