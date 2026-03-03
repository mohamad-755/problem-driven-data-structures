
class CharStack {

    private final char[] data;
    private int top; // number of elements currently in the stack

    public CharStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        data = new char[capacity];
        top = 0;
    }

    public void push(char x) {
        if (top == data.length) {
            throw new IllegalStateException("Stack overflow (fixed capacity).");
        }
        data[top] = x;
        top++;
    }

    public char pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot pop from an empty stack.");
        }
        top--;
        return data[top];
    }

    public char peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot peek from an empty stack.");
        }
        return data[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int size() {
        return top;
    }
}

class BalancedExpressions {

    private static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    public static boolean isBalanced(String expr) {
        if (expr == null)
            return true; // or false, up to you

        // worst-case capacity
        CharStack st = new CharStack(expr.length());

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (isOpening(c)) {
                st.push(c);
            } else if (isClosing(c)) {
                if (st.isEmpty())
                    return false;
                char open = st.pop();
                if (!matches(open, c))
                    return false;
            }
        }
        return st.isEmpty();
    }
}

public class lab4_1 {

    public static void main(String[] args) {

        CharStack s = new CharStack(5);

        s.push('X');
        s.push('Y');

        System.out.println("peek = " + s.peek()); // Y
        System.out.println("pop = " + s.pop()); // Y
        System.out.println("size = " + s.size()); // 1

        System.out.println(BalancedExpressions.isBalanced("(a+[b*c]-{d/e})")); // true
        System.out.println(BalancedExpressions.isBalanced("(a+b]*c)")); // false
        System.out.println(BalancedExpressions.isBalanced("(((")); // false
    }
}