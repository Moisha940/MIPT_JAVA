package main;

import main.board.Board;
import main.piece.Piece;

import java.util.List;
import java.util.Set;

public class StalemateGameStateChecker extends GameStateChecker{

    @Override
    public GameState check(Board board, Color color) {
        List<Piece> pieces = board.getPieceByColor(color);
        for (Piece piece : pieces) {
            Set<Coordinates> availableMoveSquares = piece.getAvailableMoveSquares(board);
            if (!availableMoveSquares.isEmpty()) {
                return GameState.ONGOING;
            }
        }
        return GameState.STALEMATE;
    }
}
