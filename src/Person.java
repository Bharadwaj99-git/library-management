/**
 * Person.java
 * Base class representing common attributes and behavior of an individual in the system.
 * Demonstrates:
 * - OOP: Abstraction & Encapsulation (private fields, public getters/setters)
 * - OOP: Inheritance (Base class for Member)
 * - OOP: Polymorphism (displayDetails method meant to be overridden)
 */
public class Person {
    // Encapsulated private attributes
    private String id;
    private String name;
    private String email;
    private String phoneNumber;

    // Default constructor
    public Person() {
    }

    // Parameterized constructor
    public Person(String id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters (Encapsulation)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Displays general person details.
     * Subclasses (like Member) can override this method to show specific details.
     */
    public void displayDetails() {
        System.out.println("ID           : " + id);
        System.out.println("Name         : " + name);
        System.out.println("Email        : " + email);
        System.out.println("Phone Number : " + phoneNumber);
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber;
    }
}
