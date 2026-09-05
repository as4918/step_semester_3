import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors")) ||
            (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock")) ||
            (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        String[] moves = {"Rock", "Paper", "Scissors"};

        int n = 5;
        String[] playerMoves = new String[n];
        String[] computerMoves = new String[n];
        String[] results = new String[n];
        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("Enter your move (Rock/Paper/Scissors) for Round " + (i + 1) + ": ");
            playerMoves[i] = sc.nextLine();
            computerMoves[i] = moves[random.nextInt(3)];
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if (results[i].equals("Player Wins")) wins++;
            else if (results[i].equals("Computer Wins")) losses++;
            else draws++;
        }

        System.out.println("\nRound | Player Move | Computer Move | Result");
        for (int i = 0; i < n; i++) {
            System.out.println((i + 1) + "     | " + playerMoves[i] + "       | " + computerMoves[i] + "      | " + results[i]);
        }

        double winPercentage = (wins * 100.0) / n;
        System.out.printf("\nWins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n", wins, losses, draws, winPercentage);
        sc.close();
    }
}
