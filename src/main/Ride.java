package main;

public class Ride {
    public int[] startIntersection;
    public int[] finishIntersection;
    public int earliestStart;
    public int latestFinish;
    public int length;
    public int number;

    public Ride(int[] startIntersection, int[] finishIntersection,
                int earliestStart, int latestFinish, int number){
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;
        this.startIntersection = startIntersection;
        this.finishIntersection = finishIntersection;
        this.length = Math.abs(finishIntersection[0]-startIntersection[0]) +
                Math.abs(finishIntersection[1] - startIntersection[1]);
        this.number = number;
    }

    public long Score(int B, int startStep, int finishStep){
        if (finishStep > latestFinish) return 0;
        else if (startStep == earliestStart) return length + B;
        else return length;
    }
}
