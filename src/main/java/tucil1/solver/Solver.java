package tucil1.solver;

import tucil1.model.Board;

public class Solver {
    private Board board;

    public Solver(Board board) {
        this.board = board;
    }

    public boolean solve(int row) {
        if (row == board.getSize()) {
            return true;
        }
        for (int col = 0; col < board.getSize(); col++) {
            if (board.isSafe(row, col)) {
                board.placeQueen(row, col);
                if (solve(row + 1)) {
                    return true;
                }
                else {
                    board.removeQueen(row);
                }
            }
        }
        return false;
    }
}
