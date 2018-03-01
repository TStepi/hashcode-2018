package main;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {

    public List<Ride> urnik;
    int step;
    int[] location;
    long score;

    public Vehicle(){
        urnik = new ArrayList<Ride>();
    }

    public long Evaluate(int T, int B){
        step = 0;
        location = new int[] {0, 0};
        score = 0;

        for (int i = 0; i<urnik.size(); i++){
            DoRide(B, urnik.get(i));

            if (step >= T) break;
        }

        return score;
    }

    public void DoRide(int B, Ride r){
        location = r.finishIntersection;
        score += r.Score(B, step, step + r.length);
        step += r.length;
    }
}
