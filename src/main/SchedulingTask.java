package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SchedulingTask {

    Vehicle[] vozila;
    List<Ride> izbrana, neizbrana;
    int B, T;

    public SchedulingTask(int b, int t, int vehicles, List<Ride> rides){
        B = b;
        T = t;
        vozila = new Vehicle[vehicles];
        for (int i=0; i<vehicles; i++) vozila[i] = new Vehicle();
        izbrana = new ArrayList<Ride>();
        neizbrana = rides;
    }

    public SchedulingTask Mutate() {
        return null;
    }

    public long Fitness() {
        long score = 0;
        for (int i=0; i<vozila.length; i++){
            score += vozila[i].Evaluate(T, B);
        }
        return score;
    }
}

