import java.util.Random;

public class SmartComputer implements IStrat {

    private Board board;

    public SmartComputer(Board board) {
        this.board = board;
    }
//checks if there is any win conditions and finds which cell is empty in that condition and makes a move there to either block the other player from winning or to win
    //@Override
    public int makeMove() {
        Random rand = new Random();

        int[][] winPatterns = board.getWinningCombinations();

        for (int[] pattern : winPatterns) {
            if (canWin(pattern, 1)) {
                int emptyCell = findEmptyCell(pattern);
                if (emptyCell != -1) {
                    return emptyCell;
                }
            } else if (canWin(pattern, 2)) {
                int emptyCell = findEmptyCell(pattern);
                if (emptyCell != -1) {
                    return emptyCell;
                }
            }
        }

        // If no winning move found, play randomly
        int randomCell;
        do {
            randomCell = rand.nextInt(9);
        } while (!board.isMoveValid(randomCell));

        return randomCell;
    }
//checks to see if there are any win conditions on the board
    private boolean canWin(int[] pattern, int player) {
        int count = 0;
        for (int cell : pattern) {
            if (board.getGrid()[cell] == player) {
                count++;
            }
        }
        return count == 2;
    }
//checks for all empty cells
    private int findEmptyCell(int[] pattern) {
        for (int cell : pattern) {
            if (board.getGrid()[cell] == 0) {
                return cell;
            }
        }
        return -1;
    }
}
