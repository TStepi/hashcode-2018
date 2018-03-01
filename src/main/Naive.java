package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Naive {
    public static int trenutni;
    int R, C, F, N, B, T;
    int[][] zahtevki; // x1 y1 x2 y2 tS tE
    Avto[] m_Avti;
    Zahtevek[] zaht;

    public Naive(String file) throws IOException {
        ArrayList<String> lines = (new Read(file)).getLines();
        String[] params = lines.get(0).split(" ");
        R = Integer.parseInt(params[0]);
        C = Integer.parseInt(params[1]);
        F = Integer.parseInt(params[2]);
        N = Integer.parseInt(params[3]);
        B = Integer.parseInt(params[4]);
        T = Integer.parseInt(params[5]);
        zahtevki = new int[N][6];
        for (int z = 1; z < lines.size(); z++){
            String[] ride = lines.get(z).split(" ");
            for(int j = 0; j < 6; j++){
                zahtevki[z - 1][j] = Integer.parseInt(ride[j]);
            }
        }
        Avto[] avti = create();
        for(int i = 0; i < avti.length; i++){
            System.out.println("i: " + i + " voznje: " + avti[i].voznje);
        }
        m_Avti = avti;
    }
    
    
    public SchedulingTask predelaj(){
       ArrayList<Ride> rides = new ArrayList<Ride>();
       for(Zahtevek zaho : zaht){
           rides.add(zaho.predelaj());
       }
       return new SchedulingTask(B, T, F, rides);
   }
    
    
       
    public Avto[] create(){
        Avto[] avti = new Avto[F];
        for(int f = 0; f < F; f++){
            avti[f] = new Avto(0, 0, 0);
        }
        Zahtevek[] zaht = new Zahtevek[N];
        for(int n = 0; n < N; n++){
            zaht[n] = new Zahtevek(zahtevki[n], n);
        }
        PriorityQueue<Integer> casi = new PriorityQueue<Integer>();
        casi.add(0);
        int frejAvti = 0;
        HashMap<Integer, Integer> kokKdajSprosti = new HashMap<Integer, Integer>();
        kokKdajSprosti.put(0, F);
        while (!casi.isEmpty()){
            Naive.trenutni = casi.remove();
            frejAvti += kokKdajSprosti.get(Naive.trenutni);
            ArrayList<Zahtevek> neobdelano = new ArrayList<Zahtevek>();
            // poberi neobdelane: potratno
            for (Zahtevek zah: zaht){
               if (!zah.narjen){
                   neobdelano.add(zah);
               }
            }
            Collections.sort(neobdelano);
            for (Zahtevek neobd : neobdelano){
                if(frejAvti == 0){
                    break;
                }
                Avto izbrani = najdiAvto(neobd, avti);
                frejAvti--;
                // posodobi
                izbrani.posodobi(neobd);
                neobd.posodobi();
                if (!kokKdajSprosti.containsKey(izbrani.doKdaj)){
                    kokKdajSprosti.put(izbrani.doKdaj, 0);
                }
                kokKdajSprosti.put(izbrani.doKdaj, 1 + kokKdajSprosti.get(izbrani.doKdaj));
                casi.add(izbrani.doKdaj);

            }
        }
        return avti;
    }
    
    public static int dist(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
    
    
    public Avto najdiAvto(Zahtevek zah, Avto[] avti){
        Avto opti = null;
        double kakovostOpti = Double.POSITIVE_INFINITY;
        for(Avto avto: avti){
            double trenKak = primernost(avto, zah);
            if (trenKak < kakovostOpti){
                kakovostOpti = trenKak;
                opti = avto;
            }
        }
        return opti;
    }
    
    public double primernost(Avto a, Zahtevek z){
        double tPrispe = Math.max(a.doKdaj + dist(a.x, a.y, z.params[0], z.params[1]), z.params[4]);  
        return tPrispe;
        
    }
    

}
