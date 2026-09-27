/**
 * Member.java
 * Represents a library member.
 * Demonstrates:
 * - OOP: Inheritance (extends Person base class)
 * - OOP: Polymorphism (overrides displayDetails and toString)
 * - OOP: Encapsulation (private member fields with getters/setters)
 * - OOP: Constructor Overloading (multiple constructors)
 */
public class Member extends Person {
    // Specific attributes for Member
    private String membershipType;
    private int issuedBooksCount;

    // Default constructor
    public Member() {
        super();
        this.membershipType = "Regular";
        this.issuedBooksCount = 0;
    }

    // Parameterized constructor using super() to invoke Person constructor
    public Member(String id, String name, String email, String phoneNumber) {
        super(id, name, email, phoneNumber);
        this.membershipType = "Regular";
        this.issuedBooksCount = 0;
    }

    // Overloaded constructor with custom membership type
    public Member(String id, String name, String email, String phoneNumber, String membershipType) {
        super(id, name, email, phoneNumber);
        this.membershipType = membershipType;
        this.issuedBooksCount = 0;
    }

    // Getters and Setters
    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public int getIssuedBooksCount() {
        return issuedBooksCount;
    }

    public void setIssuedBooksCount(int issuedBooksCount) {
        this.issuedBooksCount = Math.max(0, issuedBooksCount);
    }

    public void incrementIssuedBooks() {
        this.issuedBooksCount++;
    }

    public void decrementIssuedBooks() {
        if (this.issuedBooksCount > 0) {
            this.issuedBooksCount--;
        }
    }

    /**
     * Method Overriding (Runtime Polymorphism).
     * Replaces Person.displayDetails() with Member-specific information formatting.
     */
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Type         : " + membershipType);
        System.out.println("Issued Books : " + issuedBooksCount);
    }

    @Override
    public String toString() {
        return super.toString() + ", Type: " + membershipType + ", Issued Books: " + issuedBooksCount;
    }
}
