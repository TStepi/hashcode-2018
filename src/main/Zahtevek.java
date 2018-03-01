package main;

import pica.Slice;

public class Zahtevek implements Comparable<Zahtevek>{
    int[] params;// x1 y1 x2 y2 tS tE
    int dolz;
    int id;
    
    
    boolean narjen = false;

    public Zahtevek(int[] params, int id) {
        this.params = params;
        dolz = Math.abs(params[0] - params[2]) + Math.abs(params[1] - params[3]);
        this.id = id;
    }
    
    public Ride predelaj(){
        return new Ride(new int[]{params[0], params[1]}, new int[]{params[2], params[3]},
                params[4], params[5]);
    }

    @Override
    public int compareTo(Zahtevek other) {
        double vrDiff = this.vrednost() - other.vrednost();
        if (vrDiff == 0){
            return 0;
        } else {
            return vrDiff < 0 ? 1 : -1;
        }
    }
    
    public double vrednost(){
        if (Naive.trenutni + dolz <= params[5]){ // + B ??
            return -dolz * (params[4] - Naive.trenutni); 
        } else{
            return Double.NEGATIVE_INFINITY;
        }
        
    }
    
    public void posodobi(){
        narjen = true;
    }
}
