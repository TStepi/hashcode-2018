from solution import Solution


class Evolution:
    def __init__(self, initialPopulation):
        self.population = initialPopulation

    def nextGeneration(self, elitism, size, mutationRate):

        # for i in range(len(self.population)-1):
        #     for j in range(i, len(self.population)):
        #         parentA = self.population[i]
        #         parentB = self.population[j]
        #         aux = parentA.mateWith(parentB)
        #         aux = aux[0]
        #         aux = aux.mutate(mutationRate)
        #         newSolutions.append(aux)

        newSolutions = [((self.population[i].mateWith(self.population[j]))[0]).mutate(mutationRate) 
           for i in range(len(self.population)-1)
           for j in range(i, len(self.population))]

        if elitism:
            newSolutions += self.population

        newSolutions.sort(key=lambda x: -x.fitness())
        self.population = newSolutions[:size]
