
import java.util.ArrayList;

class ArrayListExercises {

    /*
     * A. range
     * Returns 1 + (max - min). Empty list => 0.
     */
    public static int range(ArrayList<Integer> list) {
        if (list == null || list.isEmpty())
            return 0;

        int min = list.get(0);
        int max = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            int v = list.get(i);
            if (v < min)
                min = v;
            if (v > max)
                max = v;
        }
        return (max - min) + 1;
    }

    /*
     * B. minToFront
     * Moves the minimum value to the front, preserving relative order of others.
     * Assumes list has at least one value.
     */
    public static void minToFront(ArrayList<Integer> list) {
        int minIndex = 0;
        int minValue = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < minValue) {
                minValue = list.get(i);
                minIndex = i;
            }
        }

        // Remove min from its position and add at the front
        list.remove(minIndex);
        list.add(0, minValue);
    }

    /*
     * C. doubleText
     * Replaces every string with two of that string
     */
    public static void doubleText(ArrayList<String> list) {
        if (list == null)
            return;

        // Iterate by index; after inserting duplicate, jump by 2
        for (int i = 0; i < list.size(); i += 2) {
            String s = list.get(i);
            list.add(i, s); // insert a copy before the original
        }
    }

    /*
     * D. markLength4
     * Inserts "****" in front of every string of length 4
     * (keeps original string too).
     */
    public static void markLength4(ArrayList<String> list) {
        if (list == null)
            return;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i++; // skip over the original word we just marked
            }
        }
    }
}