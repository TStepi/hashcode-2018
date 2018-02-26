package main;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //Initial pool of solutions
        DummyTask initialSolution = new DummyTask(new double[] {0.1, 100, 0.8, 5, 1});
        List<Solution> initialPopulation = new ArrayList<Solution>();
        for (int i=0; i<10; i++) initialPopulation.add(initialSolution.Mutate(1));

        // Simulating 200 generations
        Evolution optimator = new Evolution(initialPopulation);
        for (int i=0; i<20000; i++){
            optimator.nextGeneration(true,10,0.02);
            if (i%1000 == 0)
                System.out.println(String.format("Step: %d   Fitness: %.10f",i,optimator.population.get(0).Fitness()));
        }

        // Show the final solution
        System.out.println("Final solution:");
        DummyTask result = (DummyTask) optimator.population.get(0);
        for (int i=0; i<5; i++){
            System.out.println(result.coordinates[i]);
        }
    }
}
