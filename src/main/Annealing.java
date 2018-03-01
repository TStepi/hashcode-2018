package main;

import java.util.Random;

public class Annealing {

    int iterations = 1000;
    double temp = 100;
    double endTemp = 0.0001;
    double delta = Math.pow(endTemp / temp, 1/iterations);
    Random rng = new Random();

    public Annealing(){}

    public SchedulingTask RunOptimization(SchedulingTask solution) {
        SchedulingTask best = null;
        long bestScore = -1;
        long currentScore = solution.Fitness();
        rng.nextDouble();

        for (int i=0; i<iterations; i++){
            if (i%50 == 0) System.out.println(i);

            SchedulingTask candidate = solution.Mutate(0.1);
            long newScore = candidate.Fitness();
            System.out.println(newScore);

            if (newScore > bestScore){
                bestScore = newScore;
                best = candidate;
                System.out.println("New best score: "+bestScore);
            }

            if (rng.nextDouble() < Math.exp((newScore - currentScore) / temp)){
                solution = candidate;
                currentScore = newScore;
            }

            temp *= delta;
        }

        return best;
    }
}
