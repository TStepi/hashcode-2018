import java.util.Random;

/**
 * A simple task to test our genetic optimization framework.
 * The goal is to "guess" a vector of 5 values.
 */
public class DummyTask implements Solution {

    double[] coordinates;
    double[] goal = new double[] {1, 2, 3, 4, 5};
    Random rng;

    public DummyTask(double[] elements){
       coordinates = new double[5];
       for (int i=0; i<5; i++){
           coordinates[i] = elements[i];
       }
       rng = new Random();
    }

    // A random coordinate is modified slightly.
    public DummyTask Mutate(double rate) {
        DummyTask result = new DummyTask(coordinates);
        int i = rng.nextInt(5);
        result.coordinates[i] += (rng.nextDouble()-0.5)*rate*coordinates[i];
        return result;
    }

    // Each coordinate is chosen from a random parent.
    public DummyTask MateWith(Solution partner) {
        DummyTask result = new DummyTask(coordinates);
        for (int i=0; i<5; i++){
            if (rng.nextBoolean()){
                result.coordinates[i] = ((DummyTask)partner).coordinates[i];
            }
        }
        return result;
    }

    // sum of squared differences between the coordinates
    public double Fitness() {
        double diff = 0;
        for (int i=0; i<5; i++){
            double d = coordinates[i]-goal[i];
            diff += d*d;
        }
        return -diff;
    }
}
