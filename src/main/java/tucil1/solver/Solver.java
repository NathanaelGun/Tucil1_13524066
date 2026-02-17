package tucil1.solver;

import tucil1.model.Board;

public class Solver {
    private Board board;
    private long iterationCount = 0;
    private long startTime;
    private int[] positions;
    private int updateFrequency;

    public Solver(Board board) {
        this.board = board;
        this.updateFrequency = getUpdateFrequency(board.getSize());
    }

    private int getUpdateFrequency(int size) {
        if (size <= 6) return 100;
        if (size == 7) return 10000;
        if (size == 8) return 100000;
        return 1000000;
    }


    public SolverResult solve() {
        startTime = System.currentTimeMillis();
        positions = new int[board.getSize()];
        boolean solved = solveRecursive(0);
        long elapsed = System.currentTimeMillis() - startTime;
        return new SolverResult(solved, positions.clone(), iterationCount, elapsed);
    }

    private boolean solveRecursive(int row) {
        if (row == board.getSize()) {
            iterationCount++;

            if (iterationCount % updateFrequency == 0) {
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

    private void printCurrentState() {
        System.out.println("\n--- Iterasi ke-" + iterationCount + " ---");
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (positions[i] == j) {
                    System.out.print("[#]");
                } else {
                    System.out.print("[" + board.getCells()[i][j] + "]");
                }
            }
            System.out.println();
        }
        System.out.flush();
    }

    public long getIterationCount() {
        return iterationCount;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - startTime;
    }
}