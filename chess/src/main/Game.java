package main;

import main.board.Board;
import main.board.Move;

import java.util.Collections;
import java.util.List;

public class Game {
    private final Board board;
    private final BoardConsoleRender render = new BoardConsoleRender();

    private final List<GameStateChecker> checkers = List.of(
            new StalemateGameStateChecker(),
            new CheckmateGameStateChecker()
    );

    public Game(Board board) {
        this.board = board;
    }

    public void gameLoop() {
        Color colorToMove = Color.WHITE;
        GameState state = determineGameState(board, colorToMove);

        while (state == GameState.ONGOING) {
            // рендеринг-oтрисовка
            render.render(board);

            if (colorToMove == Color.WHITE) {
               System.out.println("White to move");
            } else {
                System.out.println("Black to move");
            }

            Move move = InputCoordinates.inputMove(board, colorToMove, render);

            // делаем ход
            board.makeMove(move);

            // передача хода
            colorToMove = colorToMove.opposite();

            state = determineGameState(board, colorToMove);
        }

        render.render(board);
        System.out.println("Game ended with state = " + state);

    }

    private GameState determineGameState(Board board, Color color) {
        for (GameStateChecker checker : checkers) {
            GameState state = checker.check(board, color);
            if (state != GameState.ONGOING) {
                return state;
            }
        }
        return GameState.ONGOING;
    }
}
