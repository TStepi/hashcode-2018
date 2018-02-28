from dummytask import DummyTask
from evolution import Evolution

if __name__ == '__main__':
    initialSolution = DummyTask([0.1, 100, 0.8, 5, 1])
    initialPopulation = []
    for i in range(10):
        initialPopulation.append(initialSolution.mutate(1))

    ## Simulating 200 generations
    optimator = Evolution(initialPopulation)
    for i in range(2000):
        optimator.nextGeneration(True,10,0.02);
        if (i % 1000 == 0):
            print("Step: %d  Fitness %.10f" % (i,optimator.population[0].fitness()))

    print("Final solution: ")
    result = optimator.population[0]
    for i in range(5):
        print(result.coordinates[i])
