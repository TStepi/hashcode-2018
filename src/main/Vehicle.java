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

    void DoRide(int B, Ride r){
        location = r.finishIntersection;
        if (step < r.earliestStart) step = r.earliestStart;
        score += r.Score(B, step, step + r.length);
        step += r.length;
    }

    public long[] ScoreWithNewRide(int T, int B, Ride r){
        int L = urnik.size();
        int bestLoc = L;
        urnik.add(L, r);
        long bestScore = Evaluate(T, B);
        for (int loc=L-1; loc >-1; loc--){
            urnik.set(loc+1, urnik.get(loc));
            urnik.set(loc, r);
            long newScore = Evaluate(T,B);
            if (newScore > bestScore){
                bestScore = newScore;
                bestLoc = loc;
            }
        }
        urnik.remove(0);
        return new long[] {bestScore, bestLoc};
    }

    public void InsertRide(Ride r, long index){
        urnik.add((int)index, r);
    }
}