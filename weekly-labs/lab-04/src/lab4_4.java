import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Deque;

public class lab4_4 {

    public static void mirrorHalves(Queue<Integer> q) {
        if (q == null)
            return;

        int n = q.size();
        if (n % 2 != 0) {
            throw new IllegalArgumentException("Queue size must be even.");
        }

        int half = n / 2;
        Deque<Integer> d = new ArrayDeque<>();

        // take first half into deque
        for (int i = 0; i < half; i++) {
            d.addLast(q.remove());
        }

        // put first half back
        for (int i = 0; i < half; i++) {
            q.add(d.removeFirst());
        }

        // load first half again to mirror it
        for (int i = 0; i < half; i++) {
            int x = q.remove();
            d.addLast(x);
            q.add(x); // keep order same
        }

        // add reversed first half
        for (int i = 0; i < half; i++) {
            int x = d.removeLast();
            q.add(x);
            d.addFirst(x); // restore deque
        }

        d.clear();

        // rotate so second half is at front
        for (int i = 0; i < 2 * half; i++) {
            q.add(q.remove());
        }

        // repeat same steps for second half
        for (int i = 0; i < half; i++) {
            d.addLast(q.remove());
        }

        for (int i = 0; i < half; i++) {
            q.add(d.removeFirst());
        }

        for (int i = 0; i < half; i++) {
            int x = q.remove();
            d.addLast(x);
            q.add(x);
        }

        for (int i = 0; i < half; i++) {
            int x = d.removeLast();
            q.add(x);
            d.addFirst(x);
        }

        // final rotation to fix order
        for (int i = 0; i < n; i++) {
            q.add(q.remove());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new ArrayDeque<>();
        int[] input = { 10, 50, 19, 54, 30, 67 };

        for (int x : input) {
            q.add(x);
        }

        mirrorHalves(q);
        System.out.println(q);
    }
}