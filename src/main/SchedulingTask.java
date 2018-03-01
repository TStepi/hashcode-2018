package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class SchedulingTask {

    Vehicle[] vozila;
    List<Ride> izbrana, neizbrana;
    int B, T;
    Random rng;

    public SchedulingTask(Vehicle[] vozila, List<Ride> neizbrana, int B, int T) {
        rng = new Random();
        this.B = B;
        this.T = T;
        this.neizbrana = neizbrana;
        this.vozila = vozila;
    }

    public Solution Mutate(double rate) {
        int n = vozila.length;

        

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
