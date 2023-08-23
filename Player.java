import java.util.Scanner;

public class Player {
  //creates the varibles that will create a new player
    private String name;
    private String gamePiece;
    private int score;
    private Board board;
    private IStrat strat;
    private Scanner scanner = new Scanner(System.in);
//constructs the new player based on inputs from the user in the main program
    public Player(String name, String gamePiece, Board board, IStrat strat) {
        this.name = name;
        this.gamePiece = gamePiece;
        this.board = board;
        this.strat = strat;
    }

    public String getName() {
        return name;
    }

    public String getGamePiece() {
        return gamePiece;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore() {
        score++;
    }
//makes a move based on what strategy the current player using
    public int makeMove() {
        System.out.println("It's " + gamePiece + " " + name + "'s turn:");
        int move = strat.makeMove();

        while (!board.isMoveValid(move)) {
            System.out.println("Move is not valid. Try again.");
            move = strat.makeMove();
        }
        return move;
    }
}
