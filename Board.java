import java.util.Arrays;

public class Board {
    private GameState state;
    private int[] grid = new int[9];
    private int currentPlayer;
    private int[][] winningCombinations = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        }; 
    //when the board class is created it empties or creates the grid that that contains all the player moves
  public Board() {
        Arrays.fill(grid, 0);
        currentPlayer =2;
    }
    
  public GameState getState() {
        return state;
    }
        
  public void setState(GameState state) {
        this.state = state;
    }

  public int getCurrentPlayer(){
    return currentPlayer;
  }
  
  public int setCurrentPlayer(int lastPlayer){
    return currentPlayer = lastPlayer == 1 ? 2 : 1;
  }  

  public int[][] getWinningCombinations(){
    return winningCombinations;
  }

    public int[] getGrid(){
    return grid;
  }
    //Checks if any part of the grid contains a zero if it does then it checks for a winner and exits the loop if the grid is filled and there is no winner it sets the game state to draw. If the grid is filled and there is a winner it exits else its sets the game state to a draw
  public void checkForDraw() {        
    for (int i : grid) {
            if (i == 0) {
                checkForWinner();
                break;
            }
        }
    if(checkForWinner()){
      return;
    }else{
       state = GameState.Draw;
    }       
    }
  //checks through all of the possible win conditions to see if they are met if one is it sets the gamestate to that winner
public boolean checkForWinner() {
    for (int[] combination : winningCombinations) {
        if (grid[combination[0]] == grid[combination[1]] && grid[combination[1]] == grid[combination[2]]) {
            if (grid[combination[0]] == 1) {
                state = GameState.PlayerOneWinner;
                return true;
            } else if (grid[combination[0]] == 2) {
                state = GameState.PlayerTwoWinner;
                return true;
            }
        }
    }
    
    // No winning combinations found
    return false;
}

//displays the board in the console
    public void printBoard() {
        String[] gamePiece = new String[9];
        for (int i = 0; i < gamePiece.length; i++) {
            gamePiece[i] = switch (grid[i]) {
                case 1 -> "X";
                case 2 -> "O";
                default -> String.valueOf(i);
            };
        }

        System.out.println("     |     |     ");
        System.out.printf("  %s  |  %s  |  %s  \n", gamePiece[0], gamePiece[1], gamePiece[2]);
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.printf("  %s  |  %s  |  %s  \n", gamePiece[3], gamePiece[4], gamePiece[5]);
        System.out.println("_____|_____|_____");
        System.out.println("     |     |     ");
        System.out.printf("  %s  |  %s  |  %s  \n", gamePiece[6], gamePiece[7], gamePiece[8]);
        System.out.println("     |     |     ");
    }
//checks if the move is valid by determining if that spot in the grid is empty
    public boolean isMoveValid(int userMove) {
        return grid[userMove] == 0;
    }
  //clears all moves of the board after a game is complete
    public void clearBoard() {
        Arrays.fill(grid, 0);
    }
}
