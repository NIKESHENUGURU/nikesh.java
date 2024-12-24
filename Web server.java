import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Scanner;

public class WebScraper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        System.out.print("Enter the URL of the website to scrape: ");
        String url = scanner.nextLine();

        System.out.println("\nFetching data from: " + url);

        try {
            // Fetch and parse the webpage
            Document document = Jsoup.connect(url).get();

            // Display the title of the webpage
            String title = document.title();
            System.out.println("\nPage Title: " + title);

            // Menu for choosing what to scrape
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1 -> scrapeHeadings(document);
                case 2 -> scrapeLinks(document);
                case 3 -> scrapeParagraphs(document);
                case 4 -> scrapeImages(document);
                default -> System.out.println("Invalid choice. Exiting program.");
            }
        } catch (IOException e) {
            System.err.println("Error fetching data from the website. Please check the URL.");
            e.printStackTrace();
        }

        printGoodbyeMessage();
        scanner.close();
    }

    // Prints a welcome message
    private static void printWelcomeMessage() {
        System.out.println("***********************************************");
        System.out.println("*        Welcome to the Java Web Scraper      *");
        System.out.println("***********************************************\n");
    }

    // Displays the menu for scraping options
    private static void displayMenu() {
        System.out.println("\nWhat would you like to scrape?");
        System.out.println("1. Headings (h1, h2, h3, etc.)");
        System.out.println("2. Links (anchor tags)");
        System.out.println("3. Paragraphs (text content)");
        System.out.println("4. Images (image URLs)");
        System.out.print("Enter your choice: ");
    }

    // Gets the user's menu choice
    private static int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number between 1 and 4.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Scrapes and prints all headings (h1, h2, h3, etc.)
    private static void scrapeHeadings(Document document) {
        System.out.println("\nScraping Headings...");
        Elements headings = document.select("h1, h2, h3, h4, h5, h6");
        if (headings.isEmpty()) {
            System.out.println("No headings found on the page.");
        } else {
            for (Element heading : headings) {
                System.out.println(heading.tagName() + ": " + heading.text());
            }
        }
    }

    // Scrapes and prints all links (anchor tags)
    private static void scrapeLinks(Document document) {
        System.out.println("\nScraping Links...");
        Elements links = document.select("a[href]");
        if (links.isEmpty()) {
            System.out.println("No links found on the page.");
        } else {
            for (Element link : links) {
                System.out.println("Text: " + link.text() + " | URL: " + link.attr("abs:href"));
            }
        }
    }

    // Scrapes and prints all paragraphs
    private static void scrapeParagraphs(Document document) {
        System.out.println("\nScraping Paragraphs...");
        Elements paragraphs = document.select("p");
        if (paragraphs.isEmpty()) {
            System.out.println("No paragraphs found on the page.");
        } else {
            for (Element paragraph : paragraphs) {
                System.out.println(paragraph.text());
            }
        }
    }

    // Scrapes and prints all image URLs
    private static void scrapeImages(Document document) {
        System.out.println("\nScraping Images...");
        Elements images = document.select("img[src]");
        if (images.isEmpty()) {
            System.out.println("No images found on the page.");
        } else {
            for (Element image : images) {
                System.out.println("Image URL: " + image.attr("abs:src"));
            }
        }
    }

    // Prints a goodbye message
    private static void printGoodbyeMessage() {
        System.out.println("\n***********************************************");
        System.out.println("*       Thank you for using the Web Scraper!  *");
        System.out.println("*               Goodbye! 😊                  *");
        System.out.println("***********************************************");
    }
}
