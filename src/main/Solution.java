package main;

import java.util.ArrayList;

public interface Solution {

    /**
     * Modifies an existing solution.
     * @param rate how big is the modification
     * @return a new, modified solution
     */
    Solution Mutate(double rate);

    /**
     * Combines this solution with another one to produce a new solution.
     */
    ArrayList<Solution> MateWith(Solution partner);

    /**
     * Describes how good the solution is.
     */
    double Fitness();
}
