package main;

import main.board.Board;

public abstract class GameStateChecker {
    public abstract GameState check(Board board, Color color);
}
