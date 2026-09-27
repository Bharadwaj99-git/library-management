/**
 * Book.java
 * Represents a book in the library system.
 * Demonstrates:
 * - OOP: Encapsulation (private fields, public getters and setters)
 * - OOP: Constructor Overloading (default and multiple parameterized constructors)
 * - Clean domain modeling with status tracking
 */
public class Book {
    // Encapsulated private attributes
    private String id;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    // Default constructor
    public Book() {
        this.isIssued = false;
    }

    // Parameterized constructor (default unissued)
    public Book(String id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    // Overloaded parameterized constructor allowing explicit initial issue status
    public Book(String id, String title, String author, String category, boolean isIssued) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = isIssued;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean isIssued) {
        this.isIssued = isIssued;
    }

    /**
     * Returns a human-friendly string for availability status.
     * @return "Issued" if book is currently borrowed, else "Available"
     */
    public String getStatus() {
        return isIssued ? "Issued" : "Available";
    }

    /**
     * Displays complete details of the book in formatted key-value pairs.
     */
    public void displayDetails() {
        System.out.println("Book ID      : " + id);
        System.out.println("Title        : " + title);
        System.out.println("Author       : " + author);
        System.out.println("Category     : " + category);
        System.out.println("Status       : " + getStatus());
    }

    @Override
    public String toString() {
        return "Book ID: " + id + ", Title: \"" + title + "\", Author: " + author + 
               ", Category: " + category + ", Status: " + getStatus();
    }
}
