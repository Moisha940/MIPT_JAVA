package main.piece;

import main.Color;
import main.Coordinates;
import main.CoordinatesShift;

import java.util.HashSet;
import java.util.Set;

public class Queen extends LongRangePiece implements IRook, IBishop {
    public Queen(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
        Set<CoordinatesShift> moves = getBishopMoves();
        moves.addAll(getRookMoves());

        return moves;
    }
}
