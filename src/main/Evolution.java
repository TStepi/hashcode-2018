package main;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Evolution {

    List<Solution> population;

    public Evolution(List<Solution> initialPopulation){
        population = initialPopulation;
    }

    /**
     * Produces the next generation of solutions by mating every pair of existing solutions
     * and mutating the children. The specified number of children with the highest fitness
     * is selected for the new solution population.
     * If elitism is enabled, original solutions (parents) can also be included in the
     * next generation if their fitness is high enough.
     * @param elitism determines if the parents are included among the candidates for the new generation
     * @param size the size of the new generation
     * @param mutationRate strength of the mutation applied to the children
     */
    public void nextGeneration(boolean elitism, int size, double mutationRate){

        List<Solution> newSolutions = new ArrayList<Solution>();
        Solution parentA, parentB;
        for (int i=0; i < population.size()-1; i++){
            for (int j=i+1; j<population.size(); j++){
                parentA = population.get(i);
                parentB = population.get(j);
                newSolutions.add(parentA.MateWith(parentB).Mutate(mutationRate));
            }
        }

        if (elitism){
            newSolutions.addAll(population);
        }

        newSolutions.sort(new Comparator<Solution>() {
            @Override
            public int compare(Solution s1, Solution s2) {
                double f1 = s1.Fitness();
                double f2 = s2.Fitness();
                if (f1 > f2) return -1;
                else if (f1 < f2) return 1;
                else return 0;
            }
        });

        population = newSolutions.subList(0, size);
    }

}
