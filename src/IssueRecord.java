/**
 * IssueRecord.java
 * Represents an issue transaction record for a book borrowed by a member.
 * Demonstrates:
 * - OOP: Encapsulation (private fields, getters and setters)
 * - Constructor overloading
 * - Domain tracking of book borrowing history and return lifecycle
 */
public class IssueRecord {
    private String recordId;
    private String bookId;
    private String memberId;
    private String issueDate;
    private String returnDate;
    private String returnStatus; // "ISSUED" or "RETURNED"

    // Default constructor
    public IssueRecord() {
        this.returnDate = "-";
        this.returnStatus = "ISSUED";
    }

    // Parameterized constructor when a book is newly issued
    public IssueRecord(String recordId, String bookId, String memberId, String issueDate) {
        this.recordId = recordId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = "-";
        this.returnStatus = "ISSUED";
    }

    // Overloaded constructor with full historical details
    public IssueRecord(String recordId, String bookId, String memberId, String issueDate, String returnDate, String returnStatus) {
        this.recordId = recordId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
        this.returnStatus = returnStatus;
    }

    // Getters and Setters
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    /**
     * Marks the record as returned with a timestamp/date.
     * @param date The date on which the book was returned
     */
    public void markAsReturned(String date) {
        this.returnDate = date;
        this.returnStatus = "RETURNED";
    }

    public void displayDetails() {
        System.out.println("Record ID     : " + recordId);
        System.out.println("Book ID       : " + bookId);
        System.out.println("Member ID     : " + memberId);
        System.out.println("Issue Date    : " + issueDate);
        System.out.println("Return Date   : " + returnDate);
        System.out.println("Return Status : " + returnStatus);
    }

    @Override
    public String toString() {
        return "Record [" + recordId + "] - Book: " + bookId + ", Member: " + memberId +
               ", Issued: " + issueDate + ", Returned: " + returnDate + ", Status: " + returnStatus;
    }
}
