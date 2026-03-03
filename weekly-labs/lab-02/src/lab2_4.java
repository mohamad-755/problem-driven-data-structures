import java.util.ArrayList;
import java.util.Collections;

class Student implements Comparable<Student> {

    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student {id=" + id + ", name=" + name + ", gpa=" + gpa + "}";
    }

    // Natural ordering: GPA ascending
    @Override
    public int compareTo(Student other) {
        if (this.gpa < other.gpa)
            return -1;
        if (this.gpa > other.gpa)
            return 1;
        return 0;
    }

    // Secondary comparison: by name (lexicographic), then by id ascending
    public int compareByNameThenID(Student other) {
        int c = this.name.compareTo(other.name);
        if (c != 0)
            return c;

        if (this.id < other.id)
            return -1;
        if (this.id > other.id)
            return 1;
        return 0;
    }
}

class StudentArrayList {

    private Student[] data;
    private int size;

    public StudentArrayList() {
        data = new Student[4];
        size = 0;
    }

    // Basic operations
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < size;
    }

    private void ensureCapacity() {
        if (size < data.length)
            return;

        Student[] newData = new Student[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void add(Student s) {
        ensureCapacity();
        data[size] = s;
        size++;
    }

    public Student get(int index) {
        if (!isValidIndex(index))
            return null;
        return data[index];
    }

    public void set(int index, Student s) {
        if (!isValidIndex(index))
            return;
        data[index] = s;
    }

    public Student remove(int index) {
        if (!isValidIndex(index))
            return null;

        Student removed = data[index];

        // shift left
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }

        data[size - 1] = null;
        size--;

        return removed;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        String s = "[";
        for (int i = 0; i < size; i++) {
            s = s + data[i];
            if (i != size - 1)
                s = s + ", ";
        }
        s = s + "]";
        return s;
    }

    // Searching
    public int indexOf(int studentId) {
        for (int i = 0; i < size; i++) {
            if (data[i].getId() == studentId)
                return i;
        }
        return -1;
    }

    // use indexOf (as requested)
    public boolean contains(Student s) {
        if (s == null)
            return false;
        return indexOf(s.getId()) != -1;
    }

    public Student findById(int studentId) {
        int idx = indexOf(studentId);
        if (idx == -1)
            return null;
        return data[idx];
    }

    // Min / Max by GPA
    public Student findMaxGpa() {
        if (isEmpty())
            return null;

        Student max = data[0];
        for (int i = 1; i < size; i++) {
            if (data[i].getGpa() > max.getGpa()) {
                max = data[i];
            }
        }
        return max;
    }

    public Student findMinGpa() {
        if (isEmpty())
            return null;

        Student min = data[0];
        for (int i = 1; i < size; i++) {
            if (data[i].getGpa() < min.getGpa()) {
                min = data[i];
            }
        }
        return min;
    }

    // Reverse in place
    public void reverse() {
        int left = 0;
        int right = size - 1;

        while (left < right) {
            Student temp = data[left];
            data[left] = data[right];
            data[right] = temp;

            left++;
            right--;
        }
    }

    // Bubble Sort by name then ID
    public void bubbleSortByNameThenId() {
        for (int pass = 0; pass < size - 1; pass++) {
            boolean swapped = false;

            for (int i = 0; i < size - 1 - pass; i++) {
                if (data[i].compareByNameThenID(data[i + 1]) > 0) {
                    Student temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped)
                return;
        }
    }

    public void printAll() {
        if (isEmpty()) {
            System.out.println("[]");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println(data[i]);
        }
    }
}

public class lab2_4 {
    public static void main(String[] args) {

        StudentArrayList myList = new StudentArrayList();

        // Add 6 students (forces resizing)
        myList.add(new Student(105, "Sara", 2.5));
        myList.add(new Student(101, "Rayan", 3.2));
        myList.add(new Student(103, "Lina", 3.8));
        myList.add(new Student(104, "Hadi", 3.5));
        myList.add(new Student(102, "Omar", 2.9));
        myList.add(new Student(106, "Lina", 3.2));

        System.out.println("Initial custom list:");
        myList.printAll();

        System.out.println("\nMax GPA: " + myList.findMaxGpa());
        System.out.println("Min GPA: " + myList.findMinGpa());

        System.out.println("\nFind id=104: " + myList.findById(104));
        System.out.println("Contains id=999? " + myList.contains(new Student(999, "Wisam", 3.3)));

        System.out.println("\nRemove first: " + myList.remove(0));
        System.out.println("Remove middle: " + myList.remove(myList.size() / 2));
        System.out.println("Remove last: " + myList.remove(myList.size() - 1));

        System.out.println("\nAfter removals:");
        System.out.println(myList);

        myList.reverse();
        System.out.println("\nAfter reverse:");
        System.out.println(myList);

        ArrayList<Student> builtIn = new ArrayList<Student>();
        for (int i = 0; i < myList.size(); i++) {
            builtIn.add(myList.get(i));
        }

        Collections.sort(builtIn);

        System.out.println("\nCollections.sort (by GPA ascending):");
        for (int i = 0; i < builtIn.size(); i++) {
            System.out.println(builtIn.get(i));
        }

        myList.bubbleSortByNameThenId();

        System.out.println("\nCustom bubble sort (by Name then ID):");
        myList.printAll();
    }
}