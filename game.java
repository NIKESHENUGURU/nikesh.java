import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_TRIES = 5;
    private static final int LOWER_BOUND = 1;
    private static final int UPPER_BOUND = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        printWelcomeMessage();

        while (playAgain) {
            int targetNumber = generateRandomNumber(LOWER_BOUND, UPPER_BOUND);
            boolean isWinner = playGame(scanner, targetNumber);
            
            if (isWinner) {
                System.out.println("🎉 Congratulations! You guessed the correct number!");
            } else {
                System.out.println("😢 Better luck next time! The correct number was: " + targetNumber);
            }

            playAgain = askToPlayAgain(scanner);
        }

        printGoodbyeMessage();
        scanner.close();
    }

    // Prints a welcome message at the start of the game
    private static void printWelcomeMessage() {
        System.out.println("************************************************");
        System.out.println("*       Welcome to the Number Guessing Game!   *");
        System.out.println("************************************************");
        System.out.println("You need to guess a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
        System.out.println("You have " + MAX_TRIES + " attempts to find the right number.");
        System.out.println("Good luck! 🍀");
        System.out.println();
    }

    // Generates a random number within the specified bounds
    private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    // Handles the main game logic
    private static boolean playGame(Scanner scanner, int targetNumber) {
        for (int attempt = 1; attempt <= MAX_TRIES; attempt++) {
            System.out.println("Attempt " + attempt + " of " + MAX_TRIES + ":");
            int guess = getPlayerGuess(scanner);

            if (guess == targetNumber) {
                return true;
            }

            if (guess < targetNumber) {
                System.out.println("Too low! Try a higher number.");
            } else {
                System.out.println("Too high! Try a lower number.");
            }
        }
        return false;
    }

    // Gets the player's guess with input validation
    private static int getPlayerGuess(Scanner scanner) {
        while (true) {
            System.out.print("Enter your guess: ");
            if (scanner.hasNextInt()) {
                int guess = scanner.nextInt();
                if (guess >= LOWER_BOUND && guess <= UPPER_BOUND) {
                    return guess;
                } else {
                    System.out.println("Invalid guess! Please enter a number between " + LOWER_BOUND + " and " + UPPER_BOUND + ".");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    // Asks the player if they want to play again
    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Would you like to play again? (yes/no): ");
        String response = scanner.next().trim().toLowerCase();
        return response.startsWith("y");
    }

    // Prints a goodbye message at the end of the game
    private static void printGoodbyeMessage() {
        System.out.println();
        System.out.println("************************************************");
        System.out.println("*      Thanks for playing! See you again!      *");
        System.out.println("************************************************");
    }
}
