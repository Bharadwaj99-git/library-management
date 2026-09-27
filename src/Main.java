import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main.java
 * The entry point for the Library Management System console application.
 * Demonstrates:
 * - OOP: Classes, Objects, Methods, Encapsulation, Polymorphism, Inheritance
 * - Robust exception handling and scanner buffer management
 * - Interactive CLI menu with clear formatting and user-friendly prompts
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        printWelcomeBanner();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntChoice("Enter your choice (1-14): ");

            System.out.println();
            switch (choice) {
                case 1:
                    handleAddBook();
                    break;
                case 2:
                    handleViewAllBooks();
                    break;
                case 3:
                    handleSearchBook();
                    break;
                case 4:
                    handleUpdateBook();
                    break;
                case 5:
                    handleDeleteBook();
                    break;
                case 6:
                    handleAddMember();
                    break;
                case 7:
                    handleViewAllMembers();
                    break;
                case 8:
                    handleSearchMember();
                    break;
                case 9:
                    handleUpdateMember();
                    break;
                case 10:
                    handleDeleteMember();
                    break;
                case 11:
                    handleIssueBook();
                    break;
                case 12:
                    handleReturnBook();
                    break;
                case 13:
                    handleViewIssuedBooks();
                    break;
                case 14:
                    running = false;
                    printExitMessage();
                    break;
                default:
                    System.out.println("[ERROR] Invalid choice! Please select a valid number between 1 and 14.");
            }
            if (running) {
                pausePrompt();
            }
        }
        scanner.close();
    }

    // ==========================================
    //           MENU & BANNER DISPLAYS
    // ==========================================

    private static void printWelcomeBanner() {
        System.out.println("==================================================================");
        System.out.println("            WELCOME TO THE LIBRARY MANAGEMENT SYSTEM              ");
        System.out.println("       A Clean Core Java & Object-Oriented Console Project        ");
        System.out.println("==================================================================");
        System.out.println("[INFO] Sample books and members have been pre-loaded successfully.");
        System.out.println();
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("===== LIBRARY MANAGEMENT SYSTEM =====");
        System.out.println(" 1. Add Book");
        System.out.println(" 2. View All Books");
        System.out.println(" 3. Search Book");
        System.out.println(" 4. Update Book");
        System.out.println(" 5. Delete Book");
        System.out.println(" 6. Add Member");
        System.out.println(" 7. View All Members");
        System.out.println(" 8. Search Member");
        System.out.println(" 9. Update Member");
        System.out.println("10. Delete Member");
        System.out.println("11. Issue Book");
        System.out.println("12. Return Book");
        System.out.println("13. View Issued Books");
        System.out.println("14. Exit");
        System.out.println("=====================================");
    }

    private static void printExitMessage() {
        System.out.println("==================================================================");
        System.out.println("  Thank you for using the Library Management System. Goodbye!     ");
        System.out.println("==================================================================");
    }

    private static void pausePrompt() {
        System.out.println();
        System.out.print("Press [Enter] to return to the main menu...");
        scanner.nextLine();
    }

    // ==========================================
    //          BOOK ACTION HANDLERS
    // ==========================================

    private static void handleAddBook() {
        System.out.println("----- ADD NEW BOOK -----");
        String id = readNonEmptyString("Enter Book ID (e.g., B004): ");

        if (library.findBookById(id) != null) {
            System.out.println("[ERROR] A book with ID '" + id + "' already exists! Duplicate IDs are not allowed.");
            return;
        }

        String title = readNonEmptyString("Enter Book Title: ");
        String author = readNonEmptyString("Enter Author Name: ");
        String category = readNonEmptyString("Enter Category (e.g., Programming, Database, Science): ");

        Book newBook = new Book(id, title, author, category);
        boolean success = library.addBook(newBook);

        if (success) {
            System.out.println("[SUCCESS] Book added successfully!");
            System.out.println("Details: " + newBook);
        } else {
            System.out.println("[ERROR] Failed to add book due to duplicate ID.");
        }
    }

    private static void handleViewAllBooks() {
        System.out.println("----- ALL BOOKS IN LIBRARY -----");
        ArrayList<Book> books = library.getAllBooks();

        if (books.isEmpty()) {
            System.out.println("[INFO] No books found in the library.");
            return;
        }

        printBookTableHeader();
        for (Book book : books) {
            printBookTableRow(book);
        }
        printBookTableFooter();
        System.out.println("Total Books: " + books.size());
    }

    private static void handleSearchBook() {
        System.out.println("----- SEARCH BOOK -----");
        System.out.println("1. Search by Book ID");
        System.out.println("2. Search by Title");
        System.out.println("3. Search by Author");
        int subChoice = readIntChoice("Choose search method (1-3): ");

        switch (subChoice) {
            case 1: {
                String id = readNonEmptyString("Enter Book ID to search: ");
                Book book = library.findBookById(id);
                if (book != null) {
                    System.out.println("\n[SUCCESS] Book Found:");
                    printBookTableHeader();
                    printBookTableRow(book);
                    printBookTableFooter();
                } else {
                    System.out.println("[INFO] No book found with ID: " + id);
                }
                break;
            }
            case 2: {
                String title = readNonEmptyString("Enter Title keyword: ");
                ArrayList<Book> matches = library.searchBooksByTitle(title);
                displayBookSearchResults(matches, "Title containing '" + title + "'");
                break;
            }
            case 3: {
                String author = readNonEmptyString("Enter Author keyword: ");
                ArrayList<Book> matches = library.searchBooksByAuthor(author);
                displayBookSearchResults(matches, "Author containing '" + author + "'");
                break;
            }
            default:
                System.out.println("[ERROR] Invalid search option.");
        }
    }

    private static void displayBookSearchResults(ArrayList<Book> matches, String criteria) {
        if (matches.isEmpty()) {
            System.out.println("[INFO] No books found matching " + criteria + ".");
            return;
        }
        System.out.println("\n[SUCCESS] Found " + matches.size() + " book(s) for " + criteria + ":");
        printBookTableHeader();
        for (Book book : matches) {
            printBookTableRow(book);
        }
        printBookTableFooter();
    }

    private static void handleUpdateBook() {
        System.out.println("----- UPDATE BOOK DETAILS -----");
        String id = readNonEmptyString("Enter Book ID to update: ");
        Book book = library.findBookById(id);

        if (book == null) {
            System.out.println("[ERROR] Book with ID '" + id + "' does not exist.");
            return;
        }

        System.out.println("\nCurrent Book Details:");
        book.displayDetails();
        System.out.println("\n(Leave blank and press Enter to keep current value)");

        System.out.print("Enter New Title [" + book.getTitle() + "]: ");
        String newTitle = scanner.nextLine().trim();

        System.out.print("Enter New Author [" + book.getAuthor() + "]: ");
        String newAuthor = scanner.nextLine().trim();

        System.out.print("Enter New Category [" + book.getCategory() + "]: ");
        String newCategory = scanner.nextLine().trim();

        boolean updated = library.updateBook(id, newTitle, newAuthor, newCategory);
        if (updated) {
            System.out.println("[SUCCESS] Book updated successfully!");
            System.out.println("\nUpdated Details:");
            book.displayDetails();
        } else {
            System.out.println("[ERROR] Failed to update book.");
        }
    }

    private static void handleDeleteBook() {
        System.out.println("----- DELETE BOOK -----");
        String id = readNonEmptyString("Enter Book ID to delete: ");

        int result = library.deleteBook(id);
        if (result == 1) {
            System.out.println("[SUCCESS] Book '" + id + "' was successfully removed from the library.");
        } else if (result == -1) {
            System.out.println("[ERROR] Cannot delete Book '" + id + "' because it is currently ISSUED to a member.");
            System.out.println("Please have the member return the book before deleting it.");
        } else {
            System.out.println("[ERROR] Book with ID '" + id + "' does not exist.");
        }
    }

    // ==========================================
    //         MEMBER ACTION HANDLERS
    // ==========================================

    private static void handleAddMember() {
        System.out.println("----- ADD NEW MEMBER -----");
        String id = readNonEmptyString("Enter Member ID (e.g., M003): ");

        if (library.findMemberById(id) != null) {
            System.out.println("[ERROR] A member with ID '" + id + "' already exists! Duplicate IDs are not allowed.");
            return;
        }

        String name = readNonEmptyString("Enter Full Name: ");
        String email = readNonEmptyString("Enter Email: ");
        String phone = readNonEmptyString("Enter Phone Number: ");

        Member newMember = new Member(id, name, email, phone);
        boolean success = library.addMember(newMember);

        if (success) {
            System.out.println("[SUCCESS] Member registered successfully!");
            System.out.println("Details: " + newMember);
        } else {
            System.out.println("[ERROR] Failed to add member due to duplicate ID.");
        }
    }

    private static void handleViewAllMembers() {
        System.out.println("----- ALL REGISTERED MEMBERS -----");
        ArrayList<Member> members = library.getAllMembers();

        if (members.isEmpty()) {
            System.out.println("[INFO] No members registered yet.");
            return;
        }

        printMemberTableHeader();
        for (Member member : members) {
            printMemberTableRow(member);
        }
        printMemberTableFooter();
        System.out.println("Total Members: " + members.size());
    }

    private static void handleSearchMember() {
        System.out.println("----- SEARCH MEMBER -----");
        System.out.println("1. Search by Member ID");
        System.out.println("2. Search by Member Name");
        int subChoice = readIntChoice("Choose search method (1-2): ");

        switch (subChoice) {
            case 1: {
                String id = readNonEmptyString("Enter Member ID: ");
                Member member = library.findMemberById(id);
                if (member != null) {
                    System.out.println("\n[SUCCESS] Member Found:");
                    printMemberTableHeader();
                    printMemberTableRow(member);
                    printMemberTableFooter();
                } else {
                    System.out.println("[INFO] No member found with ID: " + id);
                }
                break;
            }
            case 2: {
                String name = readNonEmptyString("Enter Member Name keyword: ");
                ArrayList<Member> matches = library.searchMembersByName(name);
                if (matches.isEmpty()) {
                    System.out.println("[INFO] No members found matching name '" + name + "'.");
                } else {
                    System.out.println("\n[SUCCESS] Found " + matches.size() + " member(s):");
                    printMemberTableHeader();
                    for (Member m : matches) {
                        printMemberTableRow(m);
                    }
                    printMemberTableFooter();
                }
                break;
            }
            default:
                System.out.println("[ERROR] Invalid search option.");
        }
    }

    private static void handleUpdateMember() {
        System.out.println("----- UPDATE MEMBER DETAILS -----");
        String id = readNonEmptyString("Enter Member ID to update: ");
        Member member = library.findMemberById(id);

        if (member == null) {
            System.out.println("[ERROR] Member with ID '" + id + "' does not exist.");
            return;
        }

        System.out.println("\nCurrent Member Details:");
        member.displayDetails();
        System.out.println("\n(Leave blank and press Enter to keep current value)");

        System.out.print("Enter New Name [" + member.getName() + "]: ");
        String newName = scanner.nextLine().trim();

        System.out.print("Enter New Email [" + member.getEmail() + "]: ");
        String newEmail = scanner.nextLine().trim();

        System.out.print("Enter New Phone [" + member.getPhoneNumber() + "]: ");
        String newPhone = scanner.nextLine().trim();

        boolean updated = library.updateMember(id, newName, newEmail, newPhone);
        if (updated) {
            System.out.println("[SUCCESS] Member updated successfully!");
            System.out.println("\nUpdated Details:");
            member.displayDetails();
        } else {
            System.out.println("[ERROR] Failed to update member.");
        }
    }

    private static void handleDeleteMember() {
        System.out.println("----- DELETE MEMBER -----");
        String id = readNonEmptyString("Enter Member ID to delete: ");

        int result = library.deleteMember(id);
        if (result == 1) {
            System.out.println("[SUCCESS] Member '" + id + "' was successfully removed.");
        } else if (result == -1) {
            System.out.println("[ERROR] Cannot delete Member '" + id + "' because they currently have borrowed books!");
            System.out.println("Please have the member return all books first.");
        } else {
            System.out.println("[ERROR] Member with ID '" + id + "' does not exist.");
        }
    }

    // ==========================================
    //      ISSUE & RETURN ACTION HANDLERS
    // ==========================================

    private static void handleIssueBook() {
        System.out.println("----- ISSUE BOOK -----");
        String bookId = readNonEmptyString("Enter Book ID to issue: ");
        String memberId = readNonEmptyString("Enter Member ID borrowing the book: ");

        Library.IssueResult result = library.issueBook(bookId, memberId);

        switch (result.getStatus()) {
            case SUCCESS:
                System.out.println("\n[SUCCESS] Book issued successfully!");
                System.out.println("----------------------------------------------");
                result.getRecord().displayDetails();
                System.out.println("----------------------------------------------");
                break;
            case BOOK_NOT_FOUND:
                System.out.println("[ERROR] Book with ID '" + bookId + "' was not found in the library.");
                break;
            case MEMBER_NOT_FOUND:
                System.out.println("[ERROR] Member with ID '" + memberId + "' is not registered.");
                break;
            case ALREADY_ISSUED:
                System.out.println("[ERROR] Book '" + bookId + "' is already ISSUED to another member!");
                IssueRecord activeRec = library.findActiveIssueRecordByBookId(bookId);
                if (activeRec != null) {
                    System.out.println("Currently borrowed by Member: " + activeRec.getMemberId() + 
                                       " on Date: " + activeRec.getIssueDate());
                }
                break;
        }
    }

    private static void handleReturnBook() {
        System.out.println("----- RETURN BOOK -----");
        String bookId = readNonEmptyString("Enter Book ID to return: ");
        String memberId = readNonEmptyString("Enter Member ID returning the book: ");

        Library.ReturnResult result = library.returnBook(bookId, memberId);

        switch (result.getStatus()) {
            case SUCCESS:
                System.out.println("\n[SUCCESS] Book returned successfully!");
                System.out.println("----------------------------------------------");
                result.getRecord().displayDetails();
                System.out.println("----------------------------------------------");
                System.out.println("Book status updated back to: Available.");
                break;
            case BOOK_NOT_FOUND:
                System.out.println("[ERROR] Book with ID '" + bookId + "' does not exist.");
                break;
            case MEMBER_NOT_FOUND:
                System.out.println("[ERROR] Member with ID '" + memberId + "' does not exist.");
                break;
            case NOT_ISSUED:
                System.out.println("[ERROR] Book '" + bookId + "' is not currently issued. It is already Available in the library.");
                break;
            case MEMBER_MISMATCH:
                System.out.println("[ERROR] Member mismatch! Book '" + bookId + "' was issued to Member '" +
                                   result.getRecord().getMemberId() + "', not '" + memberId + "'.");
                break;
        }
    }

    private static void handleViewIssuedBooks() {
        System.out.println("----- CURRENTLY ISSUED BOOKS -----");
        ArrayList<IssueRecord> activeRecords = library.getActiveIssueRecords();

        if (activeRecords.isEmpty()) {
            System.out.println("[INFO] There are currently no books issued.");
            return;
        }

        printRecordTableHeader();
        for (IssueRecord record : activeRecords) {
            printRecordTableRow(record);
        }
        printRecordTableFooter();
        System.out.println("Total Issued Books: " + activeRecords.size());
    }

    // ==========================================
    //           INPUT & VALIDATION HELPERS
    // ==========================================

    /**
     * Reads a valid integer from user, safely catching invalid input exceptions.
     */
    private static int readIntChoice(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Invalid numeric input! Please enter a valid number.");
            }
        }
    }

    /**
     * Reads a non-empty string from user, repeatedly prompting until non-blank input is entered.
     */
    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("[ERROR] Input cannot be blank! Please enter a valid value.");
        }
    }

    // ==========================================
    //           TABLE FORMATTING HELPERS
    // ==========================================

    private static void printBookTableHeader() {
        System.out.println("+--------+--------------------------------+---------------------------+---------------------+------------+");
        System.out.printf("| %-6s | %-30s | %-25s | %-19s | %-10s |%n", "ID", "TITLE", "AUTHOR", "CATEGORY", "STATUS");
        System.out.println("+--------+--------------------------------+---------------------------+---------------------+------------+");
    }

    private static void printBookTableRow(Book b) {
        String title = truncate(b.getTitle(), 30);
        String author = truncate(b.getAuthor(), 25);
        String category = truncate(b.getCategory(), 19);
        System.out.printf("| %-6s | %-30s | %-25s | %-19s | %-10s |%n",
                b.getId(), title, author, category, b.getStatus());
    }

    private static void printBookTableFooter() {
        System.out.println("+--------+--------------------------------+---------------------------+---------------------+------------+");
    }

    private static void printMemberTableHeader() {
        System.out.println("+--------+----------------------+---------------------------+---------------+--------------+");
        System.out.printf("| %-6s | %-20s | %-25s | %-13s | %-12s |%n", "ID", "NAME", "EMAIL", "PHONE", "ISSUED BOOKS");
        System.out.println("+--------+----------------------+---------------------------+---------------+--------------+");
    }

    private static void printMemberTableRow(Member m) {
        String name = truncate(m.getName(), 20);
        String email = truncate(m.getEmail(), 25);
        System.out.printf("| %-6s | %-20s | %-25s | %-13s | %-12d |%n",
                m.getId(), name, email, m.getPhoneNumber(), m.getIssuedBooksCount());
    }

    private static void printMemberTableFooter() {
        System.out.println("+--------+----------------------+---------------------------+---------------+--------------+");
    }

    private static void printRecordTableHeader() {
        System.out.println("+------------+----------+-----------+--------------+--------------+------------+");
        System.out.printf("| %-10s | %-8s | %-9s | %-12s | %-12s | %-10s |%n",
                "RECORD ID", "BOOK ID", "MEMBER ID", "ISSUE DATE", "RETURN DATE", "STATUS");
        System.out.println("+------------+----------+-----------+--------------+--------------+------------+");
    }

    private static void printRecordTableRow(IssueRecord r) {
        System.out.printf("| %-10s | %-8s | %-9s | %-12s | %-12s | %-10s |%n",
                r.getRecordId(), r.getBookId(), r.getMemberId(), r.getIssueDate(), r.getReturnDate(), r.getReturnStatus());
    }

    private static void printRecordTableFooter() {
        System.out.println("+------------+----------+-----------+--------------+--------------+------------+");
    }

    private static String truncate(String text, int maxLen) {
        if (text == null) return "";
        if (text.length() <= maxLen) return text;
        return text.substring(0, maxLen - 3) + "...";
    }
}
