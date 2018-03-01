package main;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        int R, C, F, N, B, T;
        String file = "/data/Documents/Repositories/hashcode-2018/inputData/rides/e_high_bonus.in";
        ArrayList<String> lines = (new Read(file)).getLines();
        String[] params = lines.get(0).split(" ");
        R = Integer.parseInt(params[0]);
        C = Integer.parseInt(params[1]);
        F = Integer.parseInt(params[2]);
        N = Integer.parseInt(params[3]);
        B = Integer.parseInt(params[4]);
        T = Integer.parseInt(params[5]);
        List<Ride> zahtevki = new ArrayList<>();
        for (int z = 1; z < lines.size(); z++) {
            String[] ride = lines.get(z).split(" ");
            zahtevki.add(new Ride(
                    new int[] {Integer.parseInt(ride[0]), Integer.parseInt(ride[1])},
                    new int[] {Integer.parseInt(ride[2]), Integer.parseInt(ride[3])},
                    Integer.parseInt(ride[4]), Integer.parseInt(ride[5])
                    ));
        }

        SchedulingTask naloga = new SchedulingTask(B, T, F, zahtevki);
        Annealing optimizacija = new Annealing();

        SchedulingTask dobra = optimizacija.RunOptimization(naloga);
        System.out.println("Končal");
    }
}
