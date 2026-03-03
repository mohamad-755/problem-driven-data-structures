import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ListUtils {
    public static <E> void reverse(List<E> list) {
        for (int i = 0; i < list.size() / 2; i++) {
            int iReverse = list.size() - i - 1;

            E temp = list.get(iReverse);
            list.set(iReverse, list.get(i));
            list.set(i, temp);
        }
    }

    public static <E> void rotateRight(List<E> list, int k) {
        int size = list.size();
        if (size == 0)
            return;

        k = k % size; // handle k larger than size
        if (k < 0)
            k += size; // handle negative k

        for (int i = 0; i < k; i++) {
            E last = list.remove(size - 1);
            list.add(0, last);
        }
    }

    public static <E> void removeDuplicates(List<E> list) {
        ArrayList<E> seenElements = new ArrayList<>();

        Iterator<E> iterator = list.iterator();
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (seenElements.contains(element)) {
                iterator.remove();
            } else {
                seenElements.add(element);
            }
        }
    }

    public static <E extends Comparable<E>> void mergeSortedLists(
            List<E> list1, List<E> list2, List<E> result) {
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i).compareTo(list2.get(j)) <= 0) {
                result.add(list1.get(i));
                i++;
            } else {
                result.add(list2.get(j));
                j++;
            }
        }

        while (i < list1.size()) {
            result.add(list1.get(i));
            i++;
        }

        while (j < list2.size()) {
            result.add(list2.get(j));
            j++;
        }
    }

}
