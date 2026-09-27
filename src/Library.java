import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Library.java
 * Core controller class managing books, members, and issuing transactions.
 * Demonstrates:
 * - OOP: Encapsulation (ArrayList collections kept private)
 * - OOP: Method Overloading (multiple search methods with different parameter types)
 * - Data Structure: ArrayList for dynamic list manipulation
 * - Robust business validation and status management
 */
public class Library {
    // Private collections (Encapsulation)
    private ArrayList<Book> books;
    private ArrayList<Member> members;
    private ArrayList<IssueRecord> issueRecords;
    private int recordCounter;

    // Constructor initializes lists and pre-loads sample data
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.issueRecords = new ArrayList<>();
        this.recordCounter = 1000;
        loadSampleData();
    }

    /**
     * Pre-populates the library system with initial sample data.
     */
    private void loadSampleData() {
        // Sample Books
        books.add(new Book("B001", "Java Programming", "James Gosling", "Programming"));
        books.add(new Book("B002", "Python Basics", "Mark Lutz", "Programming"));
        books.add(new Book("B003", "Database Management", "Abraham Silberschatz", "Database"));

        // Sample Members
        members.add(new Member("M001", "Anitha", "anitha@gmail.com", "9876543210"));
        members.add(new Member("M002", "Rahul", "rahul@gmail.com", "9876543211"));
    }

    // ==========================================
    //           BOOK MANAGEMENT METHODS
    // ==========================================

    /**
     * Adds a new book to the library after checking for duplicate ID.
     * @param book Book to add
     * @return true if added successfully, false if duplicate ID
     */
    public boolean addBook(Book book) {
        if (findBookById(book.getId()) != null) {
            return false; // Duplicate ID found
        }
        books.add(book);
        return true;
    }

    /**
     * Returns the complete list of books.
     */
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    /**
     * Finds a book by its exact unique ID (case-insensitive).
     * @param id Book ID
     * @return Book object if found, null otherwise
     */
    public Book findBookById(String id) {
        if (id == null) return null;
        for (Book book : books) {
            if (book.getId().equalsIgnoreCase(id.trim())) {
                return book;
            }
        }
        return null;
    }

    // --- Method Overloading Demonstrations for Search ---

    /**
     * Overloaded search: Searches books matching title or author keyword.
     * @param query Search query string
     * @return List of matching books
     */
    public ArrayList<Book> searchBooks(String query) {
        ArrayList<Book> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) return results;
        String q = query.trim().toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(q) || 
                book.getAuthor().toLowerCase().contains(q) ||
                book.getCategory().toLowerCase().contains(q)) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * Overloaded search: Searches books specifically by Title.
     * @param title Title substring
     * @return List of matching books
     */
    public ArrayList<Book> searchBooksByTitle(String title) {
        ArrayList<Book> results = new ArrayList<>();
        if (title == null || title.trim().isEmpty()) return results;
        String q = title.trim().toLowerCase();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(q)) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * Overloaded search: Searches books specifically by Author.
     * @param author Author substring
     * @return List of matching books
     */
    public ArrayList<Book> searchBooksByAuthor(String author) {
        ArrayList<Book> results = new ArrayList<>();
        if (author == null || author.trim().isEmpty()) return results;
        String q = author.trim().toLowerCase();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(q)) {
                results.add(book);
            }
        }
        return results;
    }

    /**
     * Updates an existing book's title, author, and category.
     * @param id Book ID
     * @param newTitle New title (skipped if empty)
     * @param newAuthor New author (skipped if empty)
     * @param newCategory New category (skipped if empty)
     * @return true if updated, false if book not found
     */
    public boolean updateBook(String id, String newTitle, String newAuthor, String newCategory) {
        Book book = findBookById(id);
        if (book == null) return false;

        if (newTitle != null && !newTitle.trim().isEmpty()) {
            book.setTitle(newTitle.trim());
        }
        if (newAuthor != null && !newAuthor.trim().isEmpty()) {
            book.setAuthor(newAuthor.trim());
        }
        if (newCategory != null && !newCategory.trim().isEmpty()) {
            book.setCategory(newCategory.trim());
        }
        return true;
    }

    /**
     * Deletes a book from the library.
     * Cannot delete a book that is currently issued.
     * @param id Book ID
     * @return 1: deleted, 0: not found, -1: currently issued (cannot delete)
     */
    public int deleteBook(String id) {
        Book book = findBookById(id);
        if (book == null) {
            return 0; // Not found
        }
        if (book.isIssued()) {
            return -1; // Currently issued, cannot delete
        }
        books.remove(book);
        return 1; // Successfully deleted
    }

    // ==========================================
    //          MEMBER MANAGEMENT METHODS
    // ==========================================

    /**
     * Adds a new member to the library after checking for duplicate ID.
     * @param member Member to add
     * @return true if added, false if duplicate ID
     */
    public boolean addMember(Member member) {
        if (findMemberById(member.getId()) != null) {
            return false; // Duplicate ID found
        }
        members.add(member);
        return true;
    }

    /**
     * Returns the complete list of registered members.
     */
    public ArrayList<Member> getAllMembers() {
        return members;
    }

    /**
     * Finds a member by their exact unique ID (case-insensitive).
     * @param id Member ID
     * @return Member object if found, null otherwise
     */
    public Member findMemberById(String id) {
        if (id == null) return null;
        for (Member member : members) {
            if (member.getId().equalsIgnoreCase(id.trim())) {
                return member;
            }
        }
        return null;
    }

    /**
     * Searches members by their name (case-insensitive substring search).
     * @param name Name keyword
     * @return List of matching members
     */
    public ArrayList<Member> searchMembersByName(String name) {
        ArrayList<Member> results = new ArrayList<>();
        if (name == null || name.trim().isEmpty()) return results;
        String q = name.trim().toLowerCase();
        for (Member member : members) {
            if (member.getName().toLowerCase().contains(q)) {
                results.add(member);
            }
        }
        return results;
    }

    /**
     * Updates an existing member's name, email, and phone number.
     * @param id Member ID
     * @param newName New name (skipped if empty)
     * @param newEmail New email (skipped if empty)
     * @param newPhone New phone (skipped if empty)
     * @return true if updated, false if member not found
     */
    public boolean updateMember(String id, String newName, String newEmail, String newPhone) {
        Member member = findMemberById(id);
        if (member == null) return false;

        if (newName != null && !newName.trim().isEmpty()) {
            member.setName(newName.trim());
        }
        if (newEmail != null && !newEmail.trim().isEmpty()) {
            member.setEmail(newEmail.trim());
        }
        if (newPhone != null && !newPhone.trim().isEmpty()) {
            member.setPhoneNumber(newPhone.trim());
        }
        return true;
    }

    /**
     * Deletes a member from the library.
     * Cannot delete a member who currently holds issued books.
     * @param id Member ID
     * @return 1: deleted, 0: not found, -1: holds books (cannot delete)
     */
    public int deleteMember(String id) {
        Member member = findMemberById(id);
        if (member == null) {
            return 0; // Not found
        }
        if (member.getIssuedBooksCount() > 0) {
            return -1; // Holds borrowed books
        }
        members.remove(member);
        return 1; // Successfully deleted
    }

    // ==========================================
    //        BOOK ISSUE & RETURN OPERATIONS
    // ==========================================

    /**
     * Result holder for book issuing transactions.
     */
    public static class IssueResult {
        public enum Status { SUCCESS, BOOK_NOT_FOUND, MEMBER_NOT_FOUND, ALREADY_ISSUED }
        private final Status status;
        private final IssueRecord record;

        public IssueResult(Status status, IssueRecord record) {
            this.status = status;
            this.record = record;
        }

        public Status getStatus() { return status; }
        public IssueRecord getRecord() { return record; }
    }

    /**
     * Issues an available book to a valid member.
     * Performs strict validations on existence and availability.
     * @param bookId ID of book to issue
     * @param memberId ID of member borrowing the book
     * @return IssueResult with status code and created record
     */
    public IssueResult issueBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        if (book == null) {
            return new IssueResult(IssueResult.Status.BOOK_NOT_FOUND, null);
        }

        Member member = findMemberById(memberId);
        if (member == null) {
            return new IssueResult(IssueResult.Status.MEMBER_NOT_FOUND, null);
        }

        if (book.isIssued()) {
            return new IssueResult(IssueResult.Status.ALREADY_ISSUED, null);
        }

        // Perform issue transaction
        book.setIssued(true);
        member.incrementIssuedBooks();

        String recId = "REC" + (++recordCounter);
        String todayDate = LocalDate.now().toString();
        IssueRecord record = new IssueRecord(recId, book.getId(), member.getId(), todayDate);
        issueRecords.add(record);

        return new IssueResult(IssueResult.Status.SUCCESS, record);
    }

    /**
     * Result holder for book returning transactions.
     */
    public static class ReturnResult {
        public enum Status { SUCCESS, BOOK_NOT_FOUND, MEMBER_NOT_FOUND, NOT_ISSUED, MEMBER_MISMATCH }
        private final Status status;
        private final IssueRecord record;

        public ReturnResult(Status status, IssueRecord record) {
            this.status = status;
            this.record = record;
        }

        public Status getStatus() { return status; }
        public IssueRecord getRecord() { return record; }
    }

    /**
     * Returns an issued book from a member.
     * Verifies that the book was issued and that it was borrowed by the specified member.
     * @param bookId ID of book to return
     * @param memberId ID of member returning the book
     * @return ReturnResult indicating status and updated record
     */
    public ReturnResult returnBook(String bookId, String memberId) {
        Book book = findBookById(bookId);
        if (book == null) {
            return new ReturnResult(ReturnResult.Status.BOOK_NOT_FOUND, null);
        }

        if (!book.isIssued()) {
            return new ReturnResult(ReturnResult.Status.NOT_ISSUED, null);
        }

        Member member = findMemberById(memberId);
        if (member == null) {
            return new ReturnResult(ReturnResult.Status.MEMBER_NOT_FOUND, null);
        }

        // Find the active issue record for this book
        IssueRecord activeRecord = findActiveIssueRecordByBookId(book.getId());
        if (activeRecord == null || !activeRecord.getMemberId().equalsIgnoreCase(member.getId())) {
            return new ReturnResult(ReturnResult.Status.MEMBER_MISMATCH, activeRecord);
        }

        // Perform return transaction
        book.setIssued(false);
        member.decrementIssuedBooks();

        String returnDate = LocalDate.now().toString();
        activeRecord.markAsReturned(returnDate);

        return new ReturnResult(ReturnResult.Status.SUCCESS, activeRecord);
    }

    /**
     * Finds active (unreturned) issue record for a specific book ID.
     */
    public IssueRecord findActiveIssueRecordByBookId(String bookId) {
        if (bookId == null) return null;
        for (IssueRecord record : issueRecords) {
            if (record.getBookId().equalsIgnoreCase(bookId.trim()) && "ISSUED".equalsIgnoreCase(record.getReturnStatus())) {
                return record;
            }
        }
        return null;
    }

    /**
     * Returns all issue records (both currently active and returned history).
     */
    public ArrayList<IssueRecord> getAllIssueRecords() {
        return issueRecords;
    }

    /**
     * Returns currently active (unreturned) issue records.
     */
    public ArrayList<IssueRecord> getActiveIssueRecords() {
        ArrayList<IssueRecord> active = new ArrayList<>();
        for (IssueRecord record : issueRecords) {
            if ("ISSUED".equalsIgnoreCase(record.getReturnStatus())) {
                active.add(record);
            }
        }
        return active;
    }
}
