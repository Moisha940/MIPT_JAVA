package main;

import main.Color;
import main.Coordinates;
import main.File;
import main.board.Board;
import main.piece.Piece;

import java.util.Collections;
import java.util.Set;


public class BoardConsoleRender {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_PIECE_COLOR = "\u001B[97m";
    public static final String ANSI_BLACK_PIECE_COLOR = "\u001B[30m";
    public static final String ANSI_WHITE_SQUARE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_SQUARE_BACKGROUND =  "\u001B[0;100m";
    public static final String ANSI_HIGHLIGHTED_SQUARE_BACKGROUND =  "\u001B[45m";


    public void render(Board board, Piece pieceToMove) {
        Set<Coordinates> availableMoveSquares = Collections.emptySet();;

        if (pieceToMove != null) {
            availableMoveSquares = pieceToMove.getAvailableMoveSquares(board);
        }

        for (int rank = 8; rank >= 1; --rank) {
            String line = "";
            for (File file : File.values()) {
                Coordinates coordinates = new Coordinates(file, rank);
                boolean isHighlight = availableMoveSquares.contains(coordinates);

                if (board.isSquareEmpty(coordinates)) {
                    line += getSpriteEmptySquare(coordinates, isHighlight);
                } else {
                    line += getPieceSprite(board.getPiece(coordinates),  isHighlight);
                }
            }
            System.out.println(line + ANSI_RESET);
        }
    }

    public void render(Board board) {
        render(board, null);
    }

    private String colorizeSprite(String sprite, Color pieceColor, boolean isSquareDark, boolean isHighlighted) {
        //формат: бэк + цвет шрифта + текст

        //текст
        String result = sprite;

        //цвет шрифта + текст
        if (pieceColor == Color.WHITE) {
            result = ANSI_WHITE_PIECE_COLOR + result;
        } else {
            result = ANSI_BLACK_PIECE_COLOR + result;
        }

        // бэк + цвет шрифта + текст
        if (isHighlighted) {
            result = ANSI_HIGHLIGHTED_SQUARE_BACKGROUND + result;
        } else if (isSquareDark) {
            result = ANSI_BLACK_SQUARE_BACKGROUND + result;
        } else {
            result = ANSI_WHITE_SQUARE_BACKGROUND + result;
        }
        return  result;
    }

    private String getSpriteEmptySquare(Coordinates coordinates, boolean isHighlight) {
        return colorizeSprite("   ", Color.WHITE, Board.isSquareDark(coordinates),isHighlight);
    }

    private String selectUnicodeSpriteForPiece(Piece piece) {
        return switch (piece.getClass().getSimpleName()) {
            case "Pawn" -> " ♟ ";
            case "Knight" -> " ♞ ";
            case "Bishop" -> " ♝ ";
            case "Rook" -> " ♜ ";
            case "Queen" -> " ♛ ";
            case "King" -> " ♚ ";
            default -> "  ";
        };
    }

    private String getPieceSprite(Piece piece, boolean isHighlight) {
        return colorizeSprite((selectUnicodeSpriteForPiece(piece)), piece.color, Board.isSquareDark(piece.coordinates), isHighlight);
    }
}
