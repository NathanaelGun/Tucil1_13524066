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

    public boolean conflictsBetween(int row1, int col1, int row2, int col2) {
        if (col1 == col2) {
            return true;
        }
        if (Math.abs(row1 - row2) <= 1 && Math.abs(col1 - col2) <= 1) {
            return true;
        }
        if (cells[row1][col1] == cells[row2][col2]) {
            return true;
        }
        return false;
    }

    public boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (conflictsBetween(row, col, i, queenPositions[i])) {
                return false;
            }
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

    public char[][] getCells() {
        return cells;
    }

    public int getSize() {
        return size;
    }

    public int getQueenPosition(int row) {
        return queenPositions[row];
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (queenPositions[i] == j) {
                    System.out.print(cells[i][j] + "# ");
                } else {
                    System.out.print(cells[i][j] + "  ");
                }
            }
            System.out.println();
        }
    }

    public String getBoardAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (queenPositions[i] == j) {
                    sb.append('#');
                } else {
                    sb.append(cells[i][j]);
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
