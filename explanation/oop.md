## Object-Oriented Programming (OOP) – Course Summary


Object-Oriented Programming (OOP) is a way of writing programs by creating
**classes** that define new data types. Each class groups related **fields**
(state) and **methods** (behavior) in one place. From a class, we create
multiple **objects** (instances), where each object has its own data but shares
the same behaviors defined by the class.

### 1. Classes and Objects

- A class defines a new data type.
- An object is an instance of a class.
- Objects have their own state and behavior.

### 2. Fields (State)

- Fields are variables declared inside a class.
- They represent the state of an object.
- Each object has its own copy of each field.
- Fields are usually private to protect object data.

### 3. Methods (Behavior)

- Methods define what an object can do.
- Instance methods operate on object fields.
- Two main types:
  - Mutator methods: modify object state
  - Accessor methods: return information without modification

### 4. Constructors

- Constructors initialize new objects.
- They run when the `new` keyword is used.
- A class can have multiple constructors.
- Constructors have no return type.
- A constructor can call another constructor using `this(...)`.

### 5. Encapsulation

- Encapsulation hides internal object details.
- Fields are declared private.
- Access is provided through public methods.
- Protects object integrity and enforces valid states.

### 6. The `this` Keyword

- Refers to the current object.
- Used to distinguish fields from parameters.
- Used to call another constructor inside a constructor.

### 7. Arrays of Objects and `null`

- Arrays of objects store references, not actual objects.
- Array elements are initially set to `null`.
- Objects must be created explicitly using `new`.
- Accessing a `null` reference causes a `NullPointerException`.

### 8. `toString()` Method

- Defines how an object is converted to a string.
- Improves readability when printing objects.
- Should return a meaningful representation of the object.

### 9. Static Fields and Methods

- Static fields belong to the class, not objects.
- Shared by all instances of the class.
- Static methods do not use instance data or `this`.

### 10. Classes as Modules

- A class can act as a reusable module.
- Module classes usually contain only static methods.
- They do not have a `main` method.

### Summary

OOP promotes modular, reusable, and maintainable code by
organizing programs into classes and objects with well-
defined responsibilities.

---