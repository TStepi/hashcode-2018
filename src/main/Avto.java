package main;

import java.util.ArrayList;

public class Avto {
    int doKdaj;
    int x, y;
    ArrayList<Integer> voznje = new ArrayList<Integer>();

    public Avto(int x, int y, int t) {
        doKdaj = t;
        this.x = x;
        this.y = y;
    }
    
    public void posodobi(Zahtevek zah){
        doKdaj = Naive.trenutni + dist(x, y, zah.params[0], zah.params[1])
    }

}
