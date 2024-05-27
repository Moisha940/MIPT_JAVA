package main.piece;

import jdk.jfr.SettingControl;
import main.*;
import main.piece.Piece;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Rook extends LongRangePiece implements IRook{
    public Rook(Color color, Coordinates coordinates) {
        super(color, coordinates);
    }

    @Override
    protected Set<CoordinatesShift> getPieceMoves() {
       return getRookMoves();
    }
}
