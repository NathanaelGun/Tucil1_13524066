package tucil1.model;

public class Board {
    private int size;
    private char[][] cells;
    private int[] queenPositions;

    public Board(int size, char[][] cells) {
        this.size = size;
        this.cells = cells;
        this.queenPositions = new int[size];

        for (int i = 0; i < size; i++) {
            queenPositions[i] = -1;
        }
    }

    public boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (queenPositions[i] == col) {
                return false;
            }
            else if (Math.abs(row - i) <= 1 && Math.abs(col - queenPositions[i]) <= 1) {
                return false;
            }
            // else if (cells[row][col] == cells[i][queenPositions[i]]) {
            //     return false;
            // }
        }
        return true;
    }

    public void placeQueen(int row, int col) {
        queenPositions[row] = col;
    }

    public void removeQueen(int row) {
        queenPositions[row] = -1;
    }

    public void reset() {
        for (int i = 0; i < getSize(); i++) {
            removeQueen(i);
        }
    }

    public int getSize() {
        return size;
    }

    public int getQueenPosition(int row) {
        return queenPositions[row];
    }
}
