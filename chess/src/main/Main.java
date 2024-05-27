package main;

import main.board.Board;
import main.board.BoardFactory;

public class Main {
    public static void main(String[] args) {
        Board board = (new BoardFactory()).fromFEN("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR - w");
        Game game  = new Game(board);
        game.gameLoop();
    }
}