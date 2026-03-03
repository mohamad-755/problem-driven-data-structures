import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Deque;

public class lab4_3 {

    public static void evenOddSort(Queue<Integer> q) {
        if (q == null)
            return;

        Queue<Integer> evens = new ArrayDeque<>();
        Deque<Integer> odds = new ArrayDeque<>(); // stack behavior using push/pop

        int n = q.size();
        for (int i = 0; i < n; i++) {
            int x = q.remove();
            if (x % 2 == 0) {
                evens.add(x);
            } else {
                odds.push(x);
            }
        }

        // rebuild q
        while (!evens.isEmpty()) {
            q.add(evens.remove());
        }
        while (!odds.isEmpty()) {
            q.add(odds.pop());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] input = { 2, 5, 8, 3, 9, 6, 4, 1, 7 };

        for (int x : input) {
            q.add(x);
        }

        evenOddSort(q);
        System.out.println(q); // [2, 8, 6, 4, 7, 1, 9, 3, 5]
    }
}