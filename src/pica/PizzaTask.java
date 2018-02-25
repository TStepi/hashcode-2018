package pica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import main.Solution;

public class PizzaTask implements Solution {
    private ArrayList<Slice> m_Slices;
    private Pizza m_Pizza;
    private Random rng = new Random();

    public PizzaTask(Pizza pizza) {
        m_Pizza = pizza;
        // TODO: create slices
    }
    
    public PizzaTask(Pizza pizza, ArrayList<Slice> slices){
        m_Pizza = pizza;
        m_Slices = slices;
    }

    @Override
    public Solution Mutate(double rate) {
        double maximize = 0.7;
        ArrayList<Slice> slices = new ArrayList<Slice>();
        for (Slice slice : m_Slices){
            Slice newSlice = new Slice(slice);
            if (rng.nextDouble() < rate){
                
            }
            slices.add(newSlice);
        }
        
        Solution newPizza = new PizzaTask(m_Pizza, slices);
        return null;
    }
    
    private boolean[] dirMaximize(Slice slice, ArrayList<Slice> slices){
        boolean[] yesNo = new boolean[4];
        for (int i = 0; i<4; i++){
            yesNo[i] = true;
        }
        Slice newSlice = new Slice(slice);
        newSlice.setM_Column1(Math.max(slice.getM_Column1()-1, 0)); //TODO..
        for (Slice sl : slices){
            if (sl.nonEmptyIntersection(newSlice)){
                yesNo[0] = false;
                break;
            }
        }
        return yesNo;
    }

    @Override
    public ArrayList<Solution> MateWith(Solution partner) {
        ArrayList<Slice> slicesA = this.getM_Slices();
        ArrayList<Slice> slicesB = ((PizzaTask) partner).getM_Slices();
        Collections.sort(slicesA);
        Collections.sort(slicesB);
        ArrayList<Solution> children = new ArrayList<Solution>();
        children.add(oneChild(slicesA, slicesB));
        children.add(oneChild(slicesB, slicesA));     
        return children;
    }
    
    private PizzaTask oneChild(ArrayList<Slice> slices1, ArrayList<Slice> slices2){
        ArrayList<Slice> child = new ArrayList<Slice>();
        ArrayList<ArrayList<Slice>> slicess = new ArrayList<ArrayList<Slice>>();
        slicess.add(slices1);
        slicess.add(slices2);
        int[] nums = new int[]{slices1.size(), slices2.size()}; 
        int[] inds = new int[]{0, 0};
        int current = 0;
        while(inds[0] < nums[0] || inds[1] < nums[1]){
            if (inds[current] >= nums[current]){
                current = 1 - current;
            }
            Slice candidate = slicess.get(current).get(inds[current]); 
            boolean isOk = canAddToPizza(child, candidate);
            while (inds[current] < nums[current] && !isOk){
                inds[current]++;
                candidate = slicess.get(current).get(inds[current]);
                isOk = canAddToPizza(child, candidate);
            }
            if (isOk){
                child.add(candidate);
                inds[current]++;
            }   
            current = 1 - current;            
        }
        return new PizzaTask(m_Pizza, child);
    }
    
    private boolean canAddToPizza(ArrayList<Slice> child, Slice candidate){
        for (Slice slice : child){
            if (slice.nonEmptyIntersection(candidate)){
                return false;
            }
        }
        return true;
    }

    @Override
    public double Fitness() {
        double area = 0.0;
        for (Slice slice : m_Slices){
            area += slice.area();
        }
        return area;
    }

    public ArrayList<Slice> getM_Slices() {
        return m_Slices;
    }
    
    

}
