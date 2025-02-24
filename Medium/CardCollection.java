import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Card {
    private String symbol;
    private String rank;

    public Card(String symbol, String rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return rank + " of " + symbol;
    }
}

public class CardCollection {
    private static Map<String, List<Card>> cardCollection = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCards();
        int choice;
        do {
            System.out.println("\nCard Collection System");
            System.out.println("1. Find Cards by Symbol");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    findCardsBySymbol();
                    break;
                case 2:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 2);
    }

    private static void initializeCards() {
        String[] symbols = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String symbol : symbols) {
            List<Card> cards = new ArrayList<>();
            for (String rank : ranks) {
                cards.add(new Card(symbol, rank));
            }
            cardCollection.put(symbol, cards);
        }
    }

    private static void findCardsBySymbol() {
        System.out.print("Enter the symbol (Hearts, Diamonds, Clubs, Spades): ");
        String symbol = scanner.nextLine();

        if (cardCollection.containsKey(symbol)) {
            List<Card> cards = cardCollection.get(symbol);
            System.out.println("Cards of " + symbol + ":");
            for (Card card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for the symbol: " + symbol);
        }
    }
}