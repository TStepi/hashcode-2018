from solution import Solution


class Evolution:
    def __init__(self, initialPopultation):
        self.population = initialPopultation

    def nextGeneration(self, elitism, size, mutationRate):
        newSolutions = [((self.popultation[i].MateWith(self.popultation[j]))[0]).mutate(self.mutationRate) 
            for i in range(range(len(self.popultation)-1)) 
            for j in range(i, len(self.popultation))]

        if (elitism):
            newSolutions += self.popultation

        newSolutions.sort(key=lambda x: x.fitness())
        self.popultation = newSolutions[:size]
