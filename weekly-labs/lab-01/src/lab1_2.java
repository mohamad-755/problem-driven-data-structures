import java.util.*;

abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public abstract void makeSound();

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    public void makeSound() {
        System.out.println("Dog: Woof!");
    }
}

class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    public void makeSound() {
        System.out.println("Cat: Meow!");
    }
}

class Zoo {
    private ArrayList<Animal> a;

    public Zoo() {
        a = new ArrayList<>();
    }

    public void addAnimal(Animal x) {
        a.add(x);
    }

    public void soundOff() {
        for (Animal x : a) {
            x.makeSound();
        }
    }

    public void displayAll() {
        for (Animal x : a) {
            x.displayInfo();
        }
    }
}

public class lab1_2 {
    public static void main(String[] args) {
        Zoo q = new Zoo();
        q.addAnimal(new Dog("Buddy", 3));
        q.addAnimal(new Cat("Luna", 2));
        q.soundOff();
        q.displayAll();
    }
}
