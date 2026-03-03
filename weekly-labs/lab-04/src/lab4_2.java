public class lab4_2 {

    public static void insertAtBottom(CharStack s, char e) {
        if (s.isEmpty()) {
            s.push(e);
            return;
        }
        char temp = s.pop();
        insertAtBottom(s, e);
        s.push(temp);
    }

    public static void reverseStack(CharStack s) {
        if (s.isEmpty())
            return;

        char temp = s.pop();
        reverseStack(s);
        insertAtBottom(s, temp);
    }

    // for demonstration (prints without extra data structures)
    private static void printStackTopToBottom(CharStack s) {
        if (s.isEmpty()) {
            System.out.println("(empty)");
            return;
        }
        char x = s.pop();
        System.out.print(x + " ");
        printStackTopToBottom(s);
        s.push(x); // restore
    }

    public static void main(String[] args) {
        CharStack s = new CharStack(10);

        s.push('A');
        s.push('B');
        s.push('C');
        s.push('D');

        reverseStack(s);

        // expected stack (top to bottom): A B C D
        printStackTopToBottom(s);
        System.out.println();
    }
}