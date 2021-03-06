package com.queens;

public class Result {
    private final int numSolutions;
    private final int numPossibilitiesEvaluated;

    public Result(int numSolutions, int numPossibilitiesEvaluated) {
        this.numSolutions = numSolutions;
        this.numPossibilitiesEvaluated = numPossibilitiesEvaluated;
    }

    public int numSolutions() {
        return numSolutions;
    }

    public int numPossibilitiesEvaluated() {
        return numPossibilitiesEvaluated;
    }

    public String report() {
        return String.format("%s possibilities evaluated\n%s solution%s found",
                numPossibilitiesEvaluated,
                numSolutions == 0 ? "No" : numSolutions,
                numSolutions != 1 ? "s" : "");
    }
}
