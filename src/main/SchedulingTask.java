package main;

import java.util.ArrayList;
import java.util.List;

public class SchedulingTask implements Solution{

    Vehicle[] vozila;
    List<Ride> izbrana, neizbrana;
    int B, T;

    public Solution Mutate(double rate) {
        return null;
    }

    public ArrayList<Solution> MateWith(Solution partner) {
        return null;
    }

    public double Fitness() {
        long score = 0;
        for (int i=0; i<vozila.length; i++){
            score += vozila[i].Evaluate(T, B);
        }
        return score;
    }
}
