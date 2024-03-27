import java.util.*;
import java.util.Random;

public class NUMBERGAME {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        
        while (playAgain) {
            System.out.println("Welcome to the Number Guessing Game!");
            int minRange = 1;
            int maxRange = 100;
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            
            System.out.println("I have selected a number between " + minRange + " and " + maxRange + ". Try to guess it!");
            
            int userGuess;
            boolean guessedCorrectly = false;
            int maxAttempts = 7; // Limiting the number of attempts
            
            while (!guessedCorrectly && attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                userGuess = scanner.nextInt();
                attempts++;
                
                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts!");
                    guessedCorrectly = true;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts! The correct number was: " + randomNumber);
            }
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainResponse = scanner.next();
            playAgain = playAgainResponse.equalsIgnoreCase("yes");
        }
        
        System.out.println("Thank you for playing!");
        scanner.close();
    }
}
