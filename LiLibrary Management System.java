import java.util.ArrayList;
import java.util.Scanner;

// Main class
public class LibraryManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        boolean running = true;

        printWelcomeMessage();

        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1 -> library.addBook();
                case 2 -> library.displayBooks();
                case 3 -> library.borrowBook();
                case 4 -> library.returnBook();
                case 5 -> library.searchBook();
                case 6 -> library.registerUser();
                case 7 -> library.displayUsers();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        printGoodbyeMessage();
        scanner.close();
    }

    // Prints a welcome message
    private static void printWelcomeMessage() {
        System.out.println("***********************************************");
        System.out.println("*       Welcome to the Library System!        *");
        System.out.println("***********************************************");
    }

    // Displays the menu
    private static void displayMenu() {
        System.out.println("\nLibrary Management System:");
        System.out.println("1. Add a Book");
        System.out.println("2. Display All Books");
        System.out.println("3. Borrow a Book");
        System.out.println("4. Return a Book");
        System.out.println("5. Search for a Book");
        System.out.println("6. Register a User");
        System.out.println("7. Display All Users");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // Gets the user's choice from the menu
    private static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Prints a goodbye message
    private static void printGoodbyeMessage() {
        System.out.println("\n***********************************************");
        System.out.println("*       Thank you for using the system!       *");
        System.out.println("*               Goodbye! 😊                  *");
        System.out.println("***********************************************");
    }
}

// Library class
class Library {
    private final ArrayList<Book> books = new ArrayList<>();
    private final ArrayList<User> users = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    // Adds a new book to the library
    public void addBook() {
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter the total number of copies: ");
        int copies = getPositiveInteger();

        Book book = new Book(title, author, copies);
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Displays all books in the library
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            System.out.println("\nAvailable Books:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    // Allows a user to borrow a book
    public void borrowBook() {
        System.out.print("Enter the book title to borrow: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.borrow()) {
                    System.out.println("You successfully borrowed the book: " + title);
                } else {
                    System.out.println("Sorry, no copies are available.");
                }
                return;
            }
        }

        System.out.println("Book not found!");
    }

    // Allows a user to return a book
    public void returnBook() {
        System.out.print("Enter the book title to return: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                System.out.println("You successfully returned the book: " + title);
                return;
            }
        }

        System.out.println("Book not found!");
    }

    // Searches for a book by title
    public void searchBook() {
        System.out.print("Enter the book title to search for: ");
        String title = scanner.nextLine();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found: " + book);
                return;
            }
        }

        System.out.println("Book not found!");
    }

    // Registers a new user
    public void registerUser() {
        System.out.print("Enter the user's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the user's ID: ");
        int id = getPositiveInteger();

        User user = new User(name, id);
        users.add(user);
        System.out.println("User registered successfully!");
    }

    // Displays all registered users
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered in the system.");
        } else {
            System.out.println("\nRegistered Users:");
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    // Ensures positive integer input
    private int getPositiveInteger() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a positive integer: ");
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear the newline character
        return Math.max(value, 0);
    }
}

// Book class
class Book {
    private final String title;
    private final String author;
    private final int totalCopies;
    private int availableCopies;

    public Book(String title, String author, int totalCopies) {
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public String getTitle() {
        return title;
    }

    public boolean borrow() {
        if (availableCopies > 0) {
            availableCopies--;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Available Copies: " + availableCopies + "/" + totalCopies;
    }
}

// User class
class User {
    private final String name;
    private final int id;

    public User(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User Name: " + name + ", User ID: " + id;
    }
}
