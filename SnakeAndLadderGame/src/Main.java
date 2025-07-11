import main.java.*;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Start game 1
        List<Snake> snakes = List.of(
                new Snake(17, 7), new Snake(54, 34),
                new Snake(62, 19), new Snake(98, 79)
        );

        List<Ladder> ladders = List.of(
                new Ladder(3, 38), new Ladder(24, 33),
                new Ladder(42, 93), new Ladder(72, 84)
        );

        Board board = new Board(100, snakes, ladders);
        Dice dice = new Dice(1, 6);
        List<String> players = Arrays.asList("Player 1", "Player 2", "Player 3");

        Game game1 = new Game.Builder().setBoard(board).setDice(dice).setPlayers(players).build();
        game1.play();
    }
}