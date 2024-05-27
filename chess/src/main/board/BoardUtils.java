package main.board;

import main.Coordinates;
import main.File;

import java.util.ArrayList;
import java.util.List;

public class BoardUtils {
    public static List<Coordinates> getDiagonalCoordinatesBetween(Coordinates sourse, Coordinates target) {
        // допущение, что клетки лежат на одной диагонали

        List<Coordinates> results = new ArrayList<>();

        int fileShift = sourse.file.ordinal() < target.file.ordinal() ? 1 : -1;
        int rankShift = sourse.rank < target.rank ? 1 : -1;

        for (
                int fileIndex = sourse.file.ordinal() + fileShift,
                rank = sourse.rank + rankShift;

                fileIndex != target.file.ordinal() && rank != target.rank;

                fileIndex += fileShift, rank += rankShift
        ) {
            results.add(new Coordinates(File.values()[fileIndex], rank));
        }
        return results;
    }

    public static List<Coordinates> getVerticalCoordinatesBetween(Coordinates sourse, Coordinates target) {
        // допущение, что клетки лежат на одной вертикали

        List<Coordinates> results = new ArrayList<>();

        int rankShift = sourse.rank < target.rank ? 1 : -1;

        for (int rank = sourse.rank + rankShift; rank != target.rank;  rank += rankShift) {
            results.add(new Coordinates(sourse.file, rank));
        }
        return results;
    }

    public static List<Coordinates> getHorizontalCoordinatesBetween(Coordinates sourse, Coordinates target) {
        // допущение, что клетки лежат на одной горизонтали

        List<Coordinates> results = new ArrayList<>();

        int fileShift = sourse.file.ordinal() < target.file.ordinal() ? 1 : -1;

        for (int fileIndex = sourse.file.ordinal() + fileShift; fileIndex != target.file.ordinal(); fileIndex += fileShift) {
            results.add(new Coordinates(File.values()[fileIndex], sourse.rank));
        }
        return results;
    }
}
