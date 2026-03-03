import java.util.ListIterator;
import java.util.Iterator;
import java.util.List;

class ListIteratorUtils {
    public static int product(List<Integer> list) {
        int result = 1;

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            result *= iterator.next();
        }

        return result;
    }

    public static <E> void findAndReplace(List<E> list, E target, E replacement) {
        ListIterator<E> iterator = list.listIterator();

        while (iterator.hasNext()) {
            E value = iterator.next();
            if (target.equals(value)) {
                iterator.set(replacement);
                return; // replace only first occurrence
            }
        }
    }

    private static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0)
                return false;
        }

        return true;
    }

    public static void processPrimesAndSquares(List<Integer> list) {
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            int value = iterator.next();

            if (isPrime(value)) {
                iterator.remove();
            } else {
                iterator.set(value * value);
            }
        }
    }
}
