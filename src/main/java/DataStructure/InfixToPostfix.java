package DataStructure;

public class InfixToPostfix {
    private static CursorArray<String> stack = new CursorArray<>(512);

    public static String infixToPostfix(String infix) {
        int operator = stack.createList();
        StringBuilder topOperator;
        StringBuilder postfix = new StringBuilder();
        StringBuilder number = new StringBuilder();
        if (infix.trim().charAt(infix.length() - 1) != '0' && infix.charAt(infix.length() - 1) != '1' && infix.charAt(infix.length() - 1) != '2' && infix.charAt(infix.length() - 1) != '3' && infix.charAt(infix.length() - 1) != '4' && infix.charAt(infix.length() - 1) != '5' && infix.charAt(infix.length() - 1) != '6' && infix.charAt(infix.length() - 1) != '7' && infix.charAt(infix.length() - 1) != '8' && infix.charAt(infix.length() - 1) != '9' && infix.trim().charAt(infix.length() - 1) != ')' && infix.trim().charAt(infix.length() - 1) != '!' && infix.trim().charAt(infix.length() - 1) != '^')
            return "ERROR, expression can't end with an operator";
        for (int i = 0; i < infix.length(); i++) {
            Character newCharacter = infix.charAt(i);
            switch (newCharacter) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '.':
                    int dotCounter = 0;
                    number = new StringBuilder();
                    for (; i < infix.length(); i++) {
                        number.append(infix.charAt(i));
                        if (i == infix.length() - 1) break;
                        // to find the end of a number
                        if ((infix.charAt(i + 1) == '*' || infix.charAt(i + 1) == '/' || infix.charAt(i + 1) == '+' || infix.charAt(i + 1) == '—' || infix.charAt(i + 1) == ')' || infix.charAt(i + 1) == ' '))
                            break;
                        if (infix.charAt(i) == '.') {
                            dotCounter++;
                        }
                        if (dotCounter > 1) return "Error";
                    }
                    number.append(" ");
                    postfix.append(number);
                    break;
                case 'g':
                    String s = infix.substring(i);
                    double num = Double.parseDouble(infixToPostfix(findBracket(s)));
                    stack.push("log ", operator);
                    break;
                case 'S':
                    infixToPostfix(findBracket(infix.substring(i + 3)));
                    stack.push("Sin ", operator);
                    break;
                case 'C':
                    infixToPostfix(findBracket(infix.substring(i + 2)));
                    stack.push("Cos ", operator);
                    break;
                case 'T':
                    infixToPostfix(findBracket(infix.substring(i)));
                    stack.push("Tan ", operator);
                    break;
                case 'N':
                    infixToPostfix(findBracket(infix.substring(i)));
                    stack.push("LN ", operator);
                    break;
                case '+', '—', '*', '/', '%', '^':
                    while (!stack.isEmpty(operator) && !stack.peek(operator).equals("(") && hasLowerPrecedence(newCharacter, stack.peek(operator).charAt(0))) {
                        postfix.append(stack.pop(operator));
                    }
                    stack.push(String.valueOf(newCharacter) + " ", operator);
                    break;
                case '!', '(', 'e', '√':
                    String e = String.valueOf(infix.charAt(i));
                    stack.push(e, operator);
                    break;
                case ')':
                    if (stack.peek(operator) != null) {
                        topOperator = new StringBuilder(stack.peek(operator));
                        while (!stack.isEmpty(operator) && topOperator != null) {
                            if (stack.peek(operator).trim().equals("(")) {
                                stack.pop(operator);
                                break;
                            }
                            postfix.append(topOperator);
                            stack.pop(operator);
                            topOperator = new StringBuilder(stack.peek(operator));
                        }
                        break;
                    }
                default:
                    break;
            }
        }
        while (!stack.isEmpty(operator)) {
            topOperator = new StringBuilder(stack.pop(operator));
            postfix.append(topOperator);
        }
        if (stack.isEmpty(operator)) return postfix.toString();
        return "error";
    }

    private static String findBracket(String s) {
        String num = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0' || s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4' || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8' || s.charAt(i) == '9' || s.charAt(i) == '-' || s.charAt(i) == '.') {
                num += s.charAt(i);
                if (s.charAt(i) == ')') return num;
            }
        }
        return num;
    }


    private static boolean hasLowerPrecedence(Character peek, Character newCharacter) {
        if (newCharacter == 'T' || newCharacter == 'S' || newCharacter == 'C') return true;
        else if (newCharacter == '(') {
            return true;
        } else if (newCharacter == '^') {
            return true;
        } else if ((newCharacter == '*' && peek == '—') || (newCharacter == '*' && peek == '+')) {
            return true;
        } else if ((newCharacter == '/' && peek == '—') || (newCharacter == '/' && peek == '+')) {
            return true;
        } else if ((newCharacter == '%' && peek == '—') || (newCharacter == '%' && peek == '+')) {
            return true;
        } else if ((newCharacter == '*' && peek == '/') || (newCharacter == '/' && peek == '*')) {
            return true;
        } else if ((newCharacter == '+' && peek == '—') || (newCharacter == '—' && peek == '+')) {
            return true;
        }
        return false;
    }

    public static String evaluatePostfix(String postfix) {
        int valueStack = stack.createList();
        Double result = null, op1, op2;
        for (int i = 0; i < postfix.length(); i++) {
            String nextCharacter = String.valueOf(postfix.charAt(i));
            switch (nextCharacter) {
                default:
                    String num = "";
                    for (; i < postfix.length(); i++) {
                        num += (postfix.charAt(i));
                        if (i == postfix.length() - 1) break;
                        // to find the end of a number
                        if ((postfix.charAt(i + 1) == '*' || postfix.charAt(i + 1) == '/' || postfix.charAt(i + 1) == '+' || postfix.charAt(i + 1) == '—' || postfix.charAt(i + 1) == ')' || postfix.charAt(i + 1) == ' ' || postfix.charAt(i + 1) == '^'))
                            break;

                    }
                    stack.push(num, valueStack);
                    break;
                case " ":
                    break;
                case "+":
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    op2 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = op2 + op1;
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "—":
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    op2 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = op2 - op1;
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "*":
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    op2 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = op2 * op1;
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "/":
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    op2 = Double.parseDouble(stack.pop(valueStack).trim());
                    if (op1 == 0) return "Error dividing by zero";
                    result = op2 / op1;
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "%":
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    op2 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = op2 % op1;
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "^":
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    op2 = Double.parseDouble(postfix.charAt(i + 1) + "".trim());
                    i = i + 1;
                    stack.pop(valueStack);
                    result = Math.pow(op1, op2);
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "l":
                    i = i + 3;
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = Math.log10(op1);
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "S":
                    i = i + 3;
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = Math.sin(op1);
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "C":
                    i = i + 3;
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = Math.cos(op1);
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "T":
                    i = i + 3;
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = Math.tan(op1);
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "L":
                    i = i + 2;
                    op1 = Double.parseDouble(stack.pop(valueStack).trim());
                    result = Math.log(op1);
                    stack.push(String.valueOf(result), valueStack);
                    break;
                case "!":
                    try {
                        int op = Integer.parseInt(stack.pop(valueStack).trim());
                        if (op > 0) {
                            result = (double) (factorial(op));
                            stack.push(String.valueOf(result), valueStack);
                            break;
                        }
                        return "factorial should be a positive integer";
                    } catch (Exception factEx) {
                        return "factorial can't be a double";
                    }
                case "√":
                    try {
                        op1 = Double.parseDouble(stack.pop(valueStack).trim());
                        result = Math.sqrt(op1);
                        stack.push(String.valueOf(result), valueStack);
                        break;
                    } catch (Exception sqrtE) {
                        return "square root problem";
                    }

            }
        }
        return result + "";
    }

    private static int factorial(int n) {
        int res = 1;
        if (n == 0) return res;
        for (int i = n; i > 1; i--) {
            res *= i;
        }
        return res;
    }

}
