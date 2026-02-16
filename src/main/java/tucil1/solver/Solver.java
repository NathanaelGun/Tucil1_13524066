package tucil1.solver;

import tucil1.model.Board;

public class Solver {
    private Board board;
    private long iterationCount = 0;
    private long startTime;
    private int[] positions;

    public Solver(Board board) {
        this.board = board;
    }

    public boolean solve() {
        startTime = System.currentTimeMillis();
        positions = new int[board.getSize()];
        return solveRecursive(0);
    }

    private boolean solveRecursive(int row) {
        if (row == board.getSize()) {
            iterationCount++;
            if (iterationCount % 1000 == 0) {
                printCurrentState();
            }
            if (isValid(positions)) {
                for (int i = 0; i < board.getSize(); i++) {
                    board.placeQueen(i, positions[i]);

                }
                return true;
            }
            return false;
        }
        for (int col = 0; col < board.getSize(); col++) {
            positions[row] = col;
            if (solveRecursive(row + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValid(int[] positions) {
        for (int i = 0; i < positions.length; i++) {
            for (int j = i + 1; j < positions.length; j++) {
                if (board.conflictsBetween(i, positions[i], j, positions[j])) {
                    return false;
                }
            }
        }
        return true;

    }
    public long getIterationCount() {
        return iterationCount;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
    private void printCurrentState() {
        System.out.print("\rIterasi: " + iterationCount);
    }

}
