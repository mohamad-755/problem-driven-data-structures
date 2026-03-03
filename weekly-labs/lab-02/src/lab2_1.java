//import java.util.Arrays;

class MyIntArrayList {

    // Internal array + size
    private int[] data;
    private int size;

    // Default capacity
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 1.1 Constructor
     * Initializes the internal array with a default capacity and sets size to 0.
     */
    public MyIntArrayList() {
        data = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Helper: checks index is within [0, size - 1]
     */
    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Helper: grow internal array if needed.
     */
    private void resize() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 1.2 size
     * Returns current number of elements.
     */
    public int size() {
        return size;
    }

    /**
     * 1.3 isEmpty
     * Checks if list contains zero elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 1.4 get
     * Returns element at index after validating index.
     */
    public int get(int index) {
        if (!isValidIndex(index)) {
            return -1;
        }
        return data[index];
    }

    /**
     * 1.5 contains
     * Returns true if element exists in the list.
     */
    public boolean contains(int element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element)
                return true;
        }
        return false;
    }

    /**
     * 1.6 add
     * Adds element at end; expands array if needed.
     */
    public void add(int element) {
        if (size == data.length) {
            resize();
        }
        data[size] = element;
        size++;
    }

    /**
     * 1.7 remove
     * Deletes element at index, shifts left, decreases size, returns removed value.
     */
    public int remove(int index) {
        if (!isValidIndex(index)) {
            return -1;
        }

        int removed = data[index];

        // Shift elements left from index + 1 to end
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        size--;
        return removed;
    }

    /**
     * 1.8 toString
     * Returns string like [a, b, c]
     */
    @Override
    public String toString() {
        if (size == 0)
            return "[]";

        String result = "[";
        for (int i = 0; i < size; i++) {
            result += data[i];
            if (i != size - 1) {
                result += ", ";
            }
        }
        result += "]";
        return result;
    }
}