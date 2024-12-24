import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        printWelcomeMessage();

        while (keepRunning) {
            showMenu();
            int choice = getUserChoice(scanner);

            if (choice == 0) {
                keepRunning = false; // Exit the program
            } else {
                performOperation(choice, scanner);
            }
        }

        printGoodbyeMessage();
        scanner.close();
    }

    // Prints a welcome message at the start of the program
    private static void printWelcomeMessage() {
        System.out.println("*******************************************");
        System.out.println("*           Welcome to Calculator         *");
        System.out.println("*******************************************");
        System.out.println();
    }

    // Displays the main menu
    private static void showMenu() {
        System.out.println("Select an operation:");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Modulus (%)");
        System.out.println("6. Power (^)");
        System.out.println("7. Square Root (√)");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // Gets the user's menu choice
    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number from the menu.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    // Performs the operation based on the user's choice
    private static void performOperation(int choice, Scanner scanner) {
        switch (choice) {
            case 1 -> performAddition(scanner);
            case 2 -> performSubtraction(scanner);
            case 3 -> performMultiplication(scanner);
            case 4 -> performDivision(scanner);
            case 5 -> performModulus(scanner);
            case 6 -> performPower(scanner);
            case 7 -> performSquareRoot(scanner);
            default -> System.out.println("Invalid choice! Please select a valid option.");
        }
    }

    // Performs addition
    private static void performAddition(Scanner scanner) {
        System.out.println("Addition: Enter two numbers.");
        double num1 = getNumber(scanner, "First number: ");
        double num2 = getNumber(scanner, "Second number: ");
        System.out.println("Result: " + (num1 + num2));
    }

    // Performs subtraction
    private static void performSubtraction(Scanner scanner) {
        System.out.println("Subtraction: Enter two numbers.");
        double num1 = getNumber(scanner, "First number: ");
        double num2 = getNumber(scanner, "Second number: ");
        System.out.println("Result: " + (num1 - num2));
    }

    // Performs multiplication
    private static void performMultiplication(Scanner scanner) {
        System.out.println("Multiplication: Enter two numbers.");
        double num1 = getNumber(scanner, "First number: ");
        double num2 = getNumber(scanner, "Second number: ");
        System.out.println("Result: " + (num1 * num2));
    }

    // Performs division
    private static void performDivision(Scanner scanner) {
        System.out.println("Division: Enter two numbers.");
        double num1 = getNumber(scanner, "Dividend: ");
        double num2 = getNumber(scanner, "Divisor: ");
        if (num2 == 0) {
            System.out.println("Error: Division by zero is undefined!");
        } else {
            System.out.println("Result: " + (num1 / num2));
        }
    }

    // Performs modulus
    private static void performModulus(Scanner scanner) {
        System.out.println("Modulus: Enter two numbers.");
        double num1 = getNumber(scanner, "First number: ");
        double num2 = getNumber(scanner, "Second number: ");
        if (num2 == 0) {
            System.out.println("Error: Modulus by zero is undefined!");
        } else {
            System.out.println("Result: " + (num1 % num2));
        }
    }

    // Performs power calculation
    private static void performPower(Scanner scanner) {
        System.out.println("Power: Enter the base and the exponent.");
        double base = getNumber(scanner, "Base: ");
        double exponent = getNumber(scanner, "Exponent: ");
        System.out.println("Result: " + Math.pow(base, exponent));
    }

    // Performs square root calculation
    private static void performSquareRoot(Scanner scanner) {
        System.out.println("Square Root: Enter a number.");
        double number = getNumber(scanner, "Number: ");
        if (number < 0) {
            System.out.println("Error: Square root of negative numbers is not real!");
        } else {
            System.out.println("Result: " + Math.sqrt(number));
        }
    }

    // Gets a valid number input from the user
    private static double getNumber(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid number.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextDouble();
    }

    // Prints a goodbye message at the end of the program
    private static void printGoodbyeMessage() {
        System.out.println();
        System.out.println("*******************************************");
        System.out.println("*        Thanks for using Calculator!     *");
        System.out.println("*             Goodbye! 😊                 *");
        System.out.println("*******************************************");
    }
}
