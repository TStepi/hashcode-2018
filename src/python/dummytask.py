from solution import Solution
from random

class DummyTask(Solution):
    def __init__(self, elements):
        super(Solution, self).__init__()

        ## custom init after interface init
        self.coordinates = elements
        self.goal = [1, 2, 3, 4, 5]

    def mutate(self, rate):
        result = DummyTask(self.coordinates)
        i = random.randint(1,5)
        resut.coordinates[i] += (random.random() - 0.5) * rate * self.coordinates[i]
        return result

    def mateWith(self, partner):
        reuslt = DummyTask(self.coordinates)
        result = [result[i] if random.random() < 0.5 else partner.coordinates[i] for i in range(5)]
        return [result]

    def fitness(self):
        return -sum([(self.coordinates[i] - self.goal[i]) * (self.coordinates[i] - self.goal[i])] for i in range(5))
