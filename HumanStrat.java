import java.util.Scanner;

public class HumanStrat implements IStrat {

    private Scanner scanner = new Scanner(System.in);

  // the human strat allows the human user to enter a move
   // @Override
    public int makeMove() {
        System.out.print("Enter your move: ");
        int move = scanner.nextInt();
        scanner.nextLine(); 

        return move;
    }
}
