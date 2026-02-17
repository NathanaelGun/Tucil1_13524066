package tucil1.solver;

public class SolverResult {
    private boolean solved;
    private int[] queenPositions;
    private long iterationCount;
    private long executionTime;


    public SolverResult(boolean solved, int[] queenPositions, long iterationCount, long executionTime) {
        this.solved = solved;
        this.queenPositions = queenPositions;
        this.iterationCount = iterationCount;
        this.executionTime = executionTime;
    }

    public boolean isSolved() {
        return solved;
    }

    public int[] getQueenPosition() {
        return queenPositions;
    }

    public long getIterationCount() {
        return iterationCount;
    }

    public long getExecutionTime() {
        return executionTime;
    }
}
