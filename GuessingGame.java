import java.util.*;

public class GuessingGame {
    public static final int MAX_VALUE = 100;

    public static void main(String[] args) {
        Random rand = new Random();
        Scanner console = new Scanner(System.in);
        haiku();
        int numGuesses = game(console, rand);
        int bestGame = 1000000; // initializes the best game before being looped
        bestGame = bestGame(numGuesses, bestGame);
        int totalGuesses = numGuesses; // initializes the total guesses before being looped
        int numGames = 1; // initilizes the number of games before being looped
        boolean again = true;
        while(again == true) {
            System.out.print("Do you want to play again? ");
            String playAgain = console.next();
            char firstLetter = playAgain.charAt(0);
            String first = firstLetter + "";
            if(first.equalsIgnoreCase("y")) {
                System.out.println();
                numGames += 1;
                numGuesses = game(console, rand);
                totalGuesses += numGuesses;
                bestGame = bestGame(numGuesses, bestGame);
                again = true;
            } else {
                System.out.println();
                again = false;
            }
        }
        results(numGames, totalGuesses, bestGame);
    }

    // prints haiku
    public static void haiku() {
        System.out.println("You guess a number,");
        System.out.println("Don't be over or under,");
        System.out.println("Yipee! or bummer.");
        System.out.println();
    }

    // runs the game
    // returns: the number of guesses made
    // parameteres:
    //      Scanner console - the scanner for user input
    //      Random rand - the random number generator
    public static int game(Scanner console, Random rand) {
        int goalNum = rand.nextInt(MAX_VALUE) + 1;
        System.out.println("I'm thinking of a number between 1 and " + MAX_VALUE + "...");
        int numGuesses = 1;
        boolean correct = false;
        while(correct == false) {
            System.out.print("Your guess? ");
            int guess = console.nextInt();
            if(guess == goalNum) {
                if(numGuesses == 1) {
                    System.out.println("You got it right in 1 guess!");
                } else {
                    System.out.println("You got it right in " + numGuesses + " guesses!");
                }
                correct = true;
            } else {
                if(guess > goalNum) {
                    System.out.println("It's lower.");
                } else {
                    System.out.println("It's higher.");
                }
                numGuesses += 1;
            }
        }
        return numGuesses;
    }

    // best game
    // returns: the least number of guesses made in a game
    // parameters:
    //      int numGuesses - the number of guesses from the last game played
    //      int bestGame - the current least number of guesses
    public static int bestGame(int numGuesses, int bestGame) {
        if(numGuesses < bestGame) {
            bestGame = numGuesses;
        }
        return bestGame;
    }

    // prints results stats
    // parameters:
    //      int numGames - the total number of games played
    //      int totalGuesses - the total number of guesses made
    //      int bestGame - the least guesses made in a game
    public static void results(int numGames, int totalGuesses, int bestGame) {
        System.out.println("Overall results:");
        System.out.println("Total games   = " + numGames);
        System.out.println("Total guesses = " + totalGuesses);
        double allGuesses = totalGuesses;
        double avgGuess = allGuesses / numGames;
        avgGuess = round1(avgGuess);
        System.out.println("Guesses/game  = " + avgGuess);
        System.out.println("Best game     = " + bestGame);
    }

    // Rounds the given value to one decimal places and returns the result.
    //
    // double num - the number to round
    public static double round1(double num) {
        return Math.round(num * 10.0) / 10.0;
    }
}