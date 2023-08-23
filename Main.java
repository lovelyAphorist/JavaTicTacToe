import java.util.Scanner;

public class Main {

    //Create two seperate players that will be implemented later in the program
    static Player playerOne;
    static Player playerTwo;
   //create a new board class
    static Board game = new Board();

//start the game using the startGame function  
    public static void main(String[] args) {
        startGame();
    }
//begins the game
    static void startGame() {
        Scanner scanner = new Scanner(System.in);
      //collects the users name
        System.out.println("Let's play Tic-Tac-Toe!");
        System.out.println("What is your name?");
        
        String playerOneName = scanner.nextLine();
      //creates player one by creating a new player and passing the name the gamePiece the player will be using the board and the strategy they will use
        playerOne = new Player(playerOneName, "X", game, new HumanStrat());
// lets the user decide if they want to play with a friend/against their self or against one of the computers
        System.out.println("Would you like to play with another human or against the computer?");
        System.out.println("Enter 1 for human or 2 for computer:");
        String choice = scanner.nextLine();
//depending on the users choice this section creates a new player and adds the required info for that type of player
        if (choice.equals("1")) {
            System.out.println("What is Player 2's name?");
            String playerTwoName = scanner.nextLine();
            playerTwo = new Player(playerTwoName, "O", game, new HumanStrat());
        } else if (choice.equals("2")) {
            System.out.println("Would you like to play with a dumb computer or a smart(ish) computer?");
            System.out.println("Enter 1 for dumb or 2 for smart(ish):");
            String choiceTwo = scanner.nextLine();
            if (choiceTwo.equals("1")) {
                String playerTwoName = "Charmander (Computer)";
                playerTwo = new Player(playerTwoName, "O", game, new DumbComputer());
            } else if (choiceTwo.equals("2")) {
                String playerTwoName = "Charizard (Computer)";
                playerTwo = new Player(playerTwoName, "O", game, new SmartComputer(game));
            }
        }
//sets the initial game state to continue and starts the actual game play
        game.setState(GameState.Continue);
        gamePlay();

        scanner.close();
    }
// checks if the game state is either PlayerOneWinner, PlayerTwoWinner, or draw and announces that they one and increases the score then calls the play agin function to see if the user wants to continue playing
    static void announceWinner() {
        if (game.getState() == GameState.PlayerOneWinner) {
            System.out.println(playerOne.getName() + " is the Winner!");
            playerOne.increaseScore();
            System.out.println("The current score is " + playerOne.getName() + ": " + playerOne.getScore() +
                    " and " + playerTwo.getName() + ": " + playerTwo.getScore());
        }
        if (game.getState() == GameState.PlayerTwoWinner) {
            System.out.println(playerTwo.getName() + " is the Winner!");
            playerTwo.increaseScore();
            System.out.println("The current score is " + playerOne.getName() + ": " + playerOne.getScore() +
                    " and " + playerTwo.getName() + ": " + playerTwo.getScore());
        }
        if (game.getState() == GameState.Draw) {
            System.out.println("The game is a Draw.");
        }
    }
//determins if the user wants to play again and either restarts the game exits the program or allows the player to pick a different mode
    static void playAgainQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to play again? Or start over from the beginning?");
        System.out.println("If you start from the beginning you will lose your score");
        System.out.println("Yes = New Game");
        System.out.println("No = Exit");
        System.out.println("Reset = Start from the beginning");
        String input = scanner.nextLine();

        if (input.toLowerCase().equals("reset")) {
            game = new Board();
            System.out.println("New game started!");
            startGame();
        }
        if (input.toLowerCase().equals("no")) {
            System.exit(0);
        }
        if (input.toLowerCase().equals("yes")) {
            game.clearBoard();
            game.setState(GameState.Continue);
            gamePlay();
        } else {
            System.out.println("Invalid answer");
            System.out.println("Yes = New Game");
            System.out.println("No = Exit");
            System.out.println("Reset = Start from the beginning");
            input = scanner.nextLine();
        }

        scanner.close();
    }
// determines who's turn it is and displays the board and recieves the users input as to where on the board they want to go after a player makes a move it checks if there is a winner/draw and then prints the updated board if there is a winner the loop is exited and the winner is anounced and the user is asked if they want to play again
    static void gamePlay() {
        Scanner scanner = new Scanner(System.in);
        game.printBoard();
        System.out.println("To start, please enter a number from 0-8 of where you would like to make your move!");
        int currentPlayer = game.getCurrentPlayer();

        while (game.getState() == GameState.Continue) {
            currentPlayer = game.setCurrentPlayer(currentPlayer);

            if (currentPlayer == 1) {
                game.getGrid()[playerOne.makeMove()] = 1;
            } else {
                game.getGrid()[playerTwo.makeMove()] = 2;
            }

            game.checkForDraw();
            game.printBoard();
        }

        announceWinner();
        playAgainQuestion();

        scanner.close();
    }
}
