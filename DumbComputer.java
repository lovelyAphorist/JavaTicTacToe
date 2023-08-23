import java.util.Random;

public class DumbComputer implements IStrat {
//the dumb computer only selects a move randomly from all of the choices. I should of checked which moves were available and then randomly picked from that but I got lazy
    //@Override
    public int makeMove() {
        Random rand = new Random();
        return rand.nextInt(9);  // Generates a random number from 0 to 8
    }
}
