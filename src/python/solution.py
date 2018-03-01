
class Solution:
    def mutate(self, rate):
        raise NotImplementedError( "Should have implemented mutate" )

    def mateWith(self, partner):
        raise NotImplementedError( "Should have implemented mateWith" )

    def fitness(self):
        raise NotImplementedError( "Should have implemented fitness" )

