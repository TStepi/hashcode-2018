package main;

import java.util.*;
import java.util.Comparator;

public class SchedulingTask {

    Vehicle[] vozila;
    HashMap<Ride, Boolean> statusi;
    int B, T;
    Random rng;

    public SchedulingTask(int b, int t, int vehicles, List<Ride> rides){
        B = b;
        T = t;
        rng = new Random();
        vozila = new Vehicle[vehicles];
        for (int i=0; i<vehicles; i++) vozila[i] = new Vehicle();
        statusi = new HashMap<>();
        for (int i=0; i<rides.size(); i++){
            statusi.put(rides.get(i), false);
        }
    }

    public SchedulingTask(SchedulingTask orig){
        B = orig.B;
        T = orig.T;
        rng = new Random();
        vozila = Arrays.copyOf(orig.vozila, orig.vozila.length);
        statusi = (HashMap<Ride,Boolean>)orig.statusi.clone();
    }

    public SchedulingTask Mutate(double rate) {

        SchedulingTask kopija = new SchedulingTask(this);

        int numVozil = kopija.vozila.length;
        for (int i=0; i<numVozil; i++){
            if (rng.nextDouble() < rate){
                for (Ride r: kopija.vozila[i].urnik){
                    kopija.statusi.replace(r, false);
                }
                kopija.vozila[i].urnik = new ArrayList<Ride>();
            }
        }

        kopija.GreedyInsertRides();
        return kopija;
    }


    public void GreedyInsertRides(){

        List<Ride> neizpolnjene = new ArrayList<>();
        for (Ride r: statusi.keySet()){
            if (!statusi.get(r)) {
                neizpolnjene.add(r);
            }
        }

        Collections.sort(neizpolnjene, new Comparator<Ride> () {
            @Override
            public int compare(Ride r1, Ride r2){
                return r2.length - r1.length;
            }
        });


        for (Ride r: neizpolnjene)GreedyInsertRide(r);
    }

    public void GreedyInsertRide(Ride r){

        long bestDelta = 0;
        Vehicle izbrano = null;
        long index = 0;
        for (Vehicle v : vozila){
            long startScore = v.Evaluate(T, B);
            long[] newScoreAndIndex = v.ScoreWithNewRide(T,B, r);
            long delta = newScoreAndIndex[0] - startScore;
            if (delta > bestDelta){
                bestDelta = delta;
                index = newScoreAndIndex[1];
                izbrano = v;
            }
        }
        izbrano.InsertRide(r, index);
    }


    public long Fitness() {
        long score = 0;
        for (int i=0; i<vozila.length; i++){
            score += vozila[i].Evaluate(T, B);
        }
        return score;
    }
}

