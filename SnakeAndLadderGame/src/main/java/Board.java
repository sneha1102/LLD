package main.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final int boardSize;
    private final Map<Integer, Integer> snakes;

    private final Map<Integer, Integer> ladders;

    public Board(int boardSize, List<Snake> snakeList, List<Ladder> ladderList) {
        this.boardSize = boardSize;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();

        for(Snake snake: snakeList){
            snakes.put(snake.getHead(), snake.getTail());
        }

        for(Ladder ladder: ladderList){
            ladders.put(ladder.getStart(), ladder.getEnd());
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNextPosition(int currPosition){
        int nextPosition = currPosition;
        if(snakes.containsKey(currPosition)){
            System.out.println("Oops! Bitten by snake 🐍");
            nextPosition = snakes.get(currPosition);
        }else if(ladders.containsKey(currPosition)){
            System.out.println("Yay! Climbed a ladder 🪜");
            nextPosition = ladders.get(currPosition);
        }

        return nextPosition;
    }
}
