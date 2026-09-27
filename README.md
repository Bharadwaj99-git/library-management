# Library Management System

A clean, beginner-friendly, and comprehensive **Core Java Console Application** demonstrating essential **Object-Oriented Programming (OOP)** concepts, data structures using Java Collections (`ArrayList`), and robust input validation and exception handling.

---

## 📋 Table of Contents
- [Overview](#overview)
- [Key Features](#key-features)
- [OOP Concepts Demonstrated](#oop-concepts-demonstrated)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [How to Compile and Run](#how-to-compile-and-run)
- [Sample Run & Console Output](#sample-run--console-output)
- [Validation & Error Handling](#validation--error-handling)
- [Future Enhancements](#future-enhancements)

---

## 📖 Overview
The **Library Management System** is designed as a foundational project for computer science students (such as B.Tech / BE undergraduates) to demonstrate practical Core Java concepts during technical interviews and university lab exams.

The system manages books, library members, and borrowing/returning transactions without relying on external libraries or databases. All data is managed in-memory using Java's `ArrayList` collection, backed by rigorous input validation and error handling.

---

## ✨ Key Features

### 1. 📚 Book Management
- **Add New Book**: Enter Book ID, Title, Author, and Category. Duplicate Book IDs are prevented.
- **View All Books**: Displays a clean, tabular overview of all books along with their current status (`Available` or `Issued`).
- **Search Books**:
  - Search by Book ID
  - Search by Title keyword (case-insensitive)
  - Search by Author name (case-insensitive)
- **Update Book**: Modify title, author, or category while preserving unedited fields.
- **Delete Book**: Safely delete a book. The system prevents deleting books that are currently issued to members.

### 2. 👤 Member Management
- **Add New Member**: Register members with Member ID, Full Name, Email, and Phone Number. Duplicate Member IDs are prevented.
- **View All Members**: Formatted table showing Member ID, Name, Email, Phone Number, and number of currently borrowed books.
- **Search Members**: Search by Member ID or Name keyword.
- **Update Member Details**: Update name, email, or phone number interactively.
- **Delete Member**: Prevents deleting a member who still has active unreturned books.

### 3. 🔄 Issue Book
- Issue any available book to a registered member.
- Validates that both the Book and Member exist.
- Prevents issuing books that are already marked as `Issued`.
- Updates book availability status from `Available` to `Issued`.
- Increments the member's issued book count.
- Generates an issue record with transaction ID, book ID, member ID, and issue date.

### 4. 🔁 Return Book
- Return an issued book by providing Book ID and Member ID.
- Verifies that the book is currently issued.
- Verifies that the returning member matches the borrower.
- Resets book status to `Available`.
- Decrements the member's issued book count.
- Stamps the return date and updates the transaction status to `RETURNED`.

### 5. 📑 Issue Records
- View all active borrowings (records currently in `ISSUED` status).
- Tracks unique transaction Record IDs, Book ID, Member ID, Issue Date, Return Date, and Status.

---

## 🏛️ OOP Concepts Demonstrated

| OOP Concept | Implementation in Project |
| :--- | :--- |
| **Classes & Objects** | Real-world entities modeled as classes: `Book`, `Person`, `Member`, `IssueRecord`, and `Library`. |
| **Encapsulation** | All entity fields are declared `private`. Access and mutations are controlled via public getters, setters, and business methods. |
| **Inheritance** | `Member` inherits common personal information (`id`, `name`, `email`, `phoneNumber`) from the base class `Person` using `extends` and invokes `super(...)`. |
| **Polymorphism (Overriding)** | `Member` overrides `Person`'s `displayDetails()` and `toString()` to append member-specific attributes. |
| **Polymorphism (Overloading)** | `Library` implements method overloading for `searchBooks(String query)`, `searchBooksByTitle(String title)`, and `searchBooksByAuthor(String author)`. Constructors in `Book`, `Member`, and `IssueRecord` are also overloaded. |
| **Abstraction** | Separation of business logic (`Library`), entity models (`Book`, `Member`, `IssueRecord`), and presentation UI (`Main`). |

---

## 💻 Technologies Used
- **Language**: Java (JDK 8 or higher; tested on JDK 17/21)
- **Paradigm**: Object-Oriented Programming (OOP)
- **Collections Framework**: `java.util.ArrayList`
- **Date & Time API**: `java.time.LocalDate`
- **I/O & Scanner**: `java.util.Scanner`
- **External Dependencies**: None (Standard Java SE runtime only)

---

## 📁 Project Structure

```text
LibraryManagementSystem/
│
├── src/
│   ├── Person.java        # Base class for individuals (demonstrates inheritance)
│   ├── Member.java        # Derived class extending Person (demonstrates overriding)
│   ├── Book.java          # Book entity with availability status and encapsulation
│   ├── IssueRecord.java   # Transaction record for book borrowing and returning
│   ├── Library.java       # Controller with ArrayList storage and business logic
│   └── Main.java          # Interactive console UI, validation, and menus
│
└── README.md              # Project documentation and guide
```

---

## 🚀 How to Compile and Run

### Prerequisites
- Java Development Kit (JDK 8 or later) installed.
- Verify installation by running:
  ```bash
  javac -version
  java -version
  ```

### Step 1: Open Terminal / Command Prompt
Navigate to the project root directory:
```bash
cd LibraryManagementSystem
```

### Step 2: Compile the Java Source Files
Compile all Java source files located in `src/`:
```bash
javac src/*.java
```
*(On Windows PowerShell or Command Prompt, this command compiles all `.java` files into corresponding `.class` bytecode files.)*

### Step 3: Run the Application
Execute the `Main` class:
```bash
java -cp src Main
```

---

## 🖥️ Sample Run & Console Output

### 1. Main Menu
```text
==================================================================
            WELCOME TO THE LIBRARY MANAGEMENT SYSTEM              
       A Clean Core Java & Object-Oriented Console Project        
==================================================================
[INFO] Sample books and members have been pre-loaded successfully.

===== LIBRARY MANAGEMENT SYSTEM =====
 1. Add Book
 2. View All Books
 3. Search Book
 4. Update Book
 5. Delete Book
 6. Add Member
 7. View All Members
 8. Search Member
 9. Update Member
10. Delete Member
11. Issue Book
12. Return Book
13. View Issued Books
14. Exit
=====================================
Enter your choice (1-14): 2
```

### 2. View All Books (Formatted Table)
```text
----- ALL BOOKS IN LIBRARY -----
+--------+--------------------------------+---------------------------+---------------------+------------+
| ID     | TITLE                          | AUTHOR                    | CATEGORY            | STATUS     |
+--------+--------------------------------+---------------------------+---------------------+------------+
| B001   | Java Programming               | James Gosling             | Programming         | Available  |
| B002   | Python Basics                  | Mark Lutz                 | Programming         | Available  |
| B003   | Database Management            | Abraham Silberschatz      | Database            | Available  |
+--------+--------------------------------+---------------------------+---------------------+------------+
Total Books: 3
```

### 3. Issue Book
```text
----- ISSUE BOOK -----
Enter Book ID to issue: B001
Enter Member ID borrowing the book: M001

[SUCCESS] Book issued successfully!
----------------------------------------------
Record ID     : REC1001
Book ID       : B001
Member ID     : M001
Issue Date    : 2026-09-27
Return Date   : -
Return Status : ISSUED
----------------------------------------------
```

### 4. Preventing Duplicate or Invalid Issue
```text
----- ISSUE BOOK -----
Enter Book ID to issue: B001
Enter Member ID borrowing the book: M002
[ERROR] Book 'B001' is already ISSUED to another member!
Currently borrowed by Member: M001 on Date: 2026-09-27
```

### 5. Return Book
```text
----- RETURN BOOK -----
Enter Book ID to return: B001
Enter Member ID returning the book: M001

[SUCCESS] Book returned successfully!
----------------------------------------------
Record ID     : REC1001
Book ID       : B001
Member ID     : M001
Issue Date    : 2026-09-27
Return Date   : 2026-09-27
Return Status : RETURNED
----------------------------------------------
Book status updated back to: Available.
```

---

## 🛡️ Validation & Error Handling

- **Duplicate ID Prevention**: Ensures Book IDs and Member IDs remain strictly unique.
- **Empty / Blank Input Check**: Prompts the user repeatedly if blank entries are entered for critical fields.
- **Numeric Menu Choice Protection**: `try-catch` blocks catch `NumberFormatException` so typing letters or invalid characters never crashes the program.
- **Business Rule Constraints**:
  - Cannot issue an already issued book.
  - Cannot return a book that is not issued.
  - Cannot return a book under a different member's ID than the borrower.
  - Cannot delete a book while it is currently issued.
  - Cannot delete a member while they still hold borrowed books.

---

## 🔮 Future Enhancements
- File I/O persistence (saving and loading records from CSV or JSON files).
- Fine calculation for overdue returns beyond a loan period (e.g., 14 days).
- Role-based login (Admin vs. Student).
- Graphical User Interface (GUI) using JavaFX or Swing.
- Integration with an SQL relational database (MySQL / PostgreSQL) via JDBC.

---

## 👨‍💻 Author
- Repository: [Bharadwaj99-git/library-management](https://github.com/Bharadwaj99-git/library-management.git)
- Developed as a Clean Core Java OOP Project.
