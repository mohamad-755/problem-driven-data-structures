import java.util.ArrayList;
import java.util.Collections;

class Patient implements Comparable<Patient> {

    private String firstName;
    private String lastName;
    private int age;
    private String caseSeverity; // "severe", "moderate", "minor"

    public Patient(String firstName, String lastName, int age, String caseSeverity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.caseSeverity = caseSeverity;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCaseSeverity() {
        return caseSeverity;
    }

    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCaseSeverity(String caseSeverity) {
        this.caseSeverity = caseSeverity;
    }

    /**
     * Compare by firstName, then lastName (lexicographically).
     */
    @Override
    public int compareTo(Patient other) {
        int firstCmp = this.firstName.compareTo(other.firstName);
        if (firstCmp != 0) {
            return firstCmp;
        }
        return this.lastName.compareTo(other.lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName +
                " | Age: " + age +
                " | Severity: " + caseSeverity;
    }
}

public class lab2_3 {

    public static void main(String[] args) {

        ArrayList<Patient> patients = new ArrayList<>();

        patients.add(new Patient("Ali", "Saad", 20, "moderate"));
        patients.add(new Patient("Ayman", "Zein", 18, "severe"));
        patients.add(new Patient("Farah", "Haddad", 25, "minor"));
        patients.add(new Patient("Ahmad", "Nasser", 30, "severe"));
        patients.add(new Patient("Mira", "Boulos", 22, "minor"));

        System.out.println("Before sorting:");
        for (Patient p : patients) {
            System.out.println(p);
        }

        Collections.sort(patients); // uses compareTo()

        System.out.println("\nAfter sorting:");
        for (Patient p : patients) {
            System.out.println(p);
        }
    }
}