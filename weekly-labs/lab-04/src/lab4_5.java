public class lab4_5 {

    private static class Entry {
        char ch;
        int idx;

        Entry(char ch, int idx) {
            this.ch = ch;
            this.idx = idx;
        }
    }

    private static class EntryStack {
        private final Entry[] data;
        private int top;

        EntryStack(int capacity) {
            data = new Entry[capacity];
            top = 0;
        }

        void push(Entry e) {
            if (top == data.length) {
                throw new IllegalStateException("Overflow");
            }
            data[top++] = e;
        }

        Entry pop() {
            if (top == 0) {
                throw new IllegalStateException("Underflow");
            }
            return data[--top];
        }

        boolean isEmpty() {
            return top == 0;
        }

        // for "earliest unmatched opening", we need the bottom-most entry (oldest)
        Entry bottom() {
            if (top == 0) {
                throw new IllegalStateException("Empty");
            }
            return data[0];
        }
    }

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

    public static int firstBalanceError(String expr) {
        if (expr == null)
            return -1;

        EntryStack st = new EntryStack(expr.length());

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (isOpening(c)) {
                st.push(new Entry(c, i));
            } else if (isClosing(c)) {
                if (st.isEmpty())
                    return i; // closing with empty stack
                Entry open = st.pop();
                if (!matches(open.ch, c))
                    return i; // mismatch
            }
        }

        if (!st.isEmpty()) {
            // earliest unmatched opening symbol
            return st.bottom().idx;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstBalanceError("(a+[b*c]-{d/e})")); // -1
        System.out.println(firstBalanceError("(a+b]*c)")); // index of ]
        System.out.println(firstBalanceError("((a+b)")); // index of first unmatched (
    }
}