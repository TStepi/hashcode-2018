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
            	ArrayList<Slice> maxCand = dirMaximize(newSlice, slices);
            	ArrayList<Slice> minCand = dirMinimize(newSlice, slices);
            	
                
            }
            slices.add(newSlice);
        }
        
        Solution newPizza = new PizzaTask(m_Pizza, slices);
        return null;
    }
    
    private ArrayList<Slice> dirMaximize(Slice slice, ArrayList<Slice> slices){
    	ArrayList<Slice> candidates = new ArrayList<Slice>();
        boolean isOK = true;
        Slice newSliceL = new Slice(slice);
        Slice newSliceR = new Slice(slice);
        Slice newSliceU = new Slice(slice);
        Slice newSliceD = new Slice(slice);
    	if( slice.getM_Column1() == 0 ||
    		slice.getM_Column2() + 1 == m_Pizza.getC() ||
    		slice.getM_Row1() == 0 ||
    		slice.getM_Row2() + 1 == m_Pizza.getR()) {
    		// TODO do we want such "new" candidates
    	}
        // left
        if (slice.getM_Column1() > 0) {

	        newSliceL.setM_Column1(Math.max(slice.getM_Column1()-1, 0));
	        if (!noIntersection(slices, newSliceL)) {
	        	isOK = false;
	        } else {
	        	int[] new_mush_tomato = m_Pizza.countMushroomTomato(newSliceL.getM_Row1(), newSliceL.getM_Row2(), newSliceL.getM_Column1(), newSliceL.getM_Column1());
	        	newSliceL.setM_mush(newSliceL.getM_mush() + new_mush_tomato[0]);
	        	newSliceL.setM_tomato(newSliceL.getM_tomato() + new_mush_tomato[1]);
	        	if(!newSliceL.isToppingOK(m_Pizza.getL(), m_Pizza.getH())) {
	        		isOK = false;
	        	}
	        }
	        if (isOK) {
	        	candidates.add(newSliceL);
	        }
        }
        isOK = true;
        // right
        if (slice.getM_Column2() + 1 < m_Pizza.getC()) {
	        newSliceR.setM_Column2(Math.min(slice.getM_Column2() + 1, m_Pizza.getC() - 1));
	        if (!noIntersection(slices, newSliceR)) {
	        	isOK = false;
	        } else {
	        	int[] new_mush_tomato = m_Pizza.countMushroomTomato(newSliceR.getM_Row1(), newSliceR.getM_Row2(), newSliceR.getM_Column2(), newSliceR.getM_Column2());
	        	newSliceR.setM_mush(newSliceR.getM_mush() + new_mush_tomato[0]);
	        	newSliceR.setM_tomato(newSliceR.getM_tomato() + new_mush_tomato[1]);
	        	if(!newSliceR.isToppingOK(m_Pizza.getL(), m_Pizza.getH())) {
	        		isOK = false;
	        	}
	        }
	        if (isOK) {
	        	candidates.add(newSliceR);
	        }
        }
        isOK = true;
        // up
        if (slice.getM_Row1() > 0) {
	        newSliceU.setM_Row1(slice.getM_Row1() - 1);
	        if (!noIntersection(slices, newSliceU)) {
	        	isOK = false;
	        } else {
	        	int[] new_mush_tomato = m_Pizza.countMushroomTomato(newSliceU.getM_Row1(), newSliceU.getM_Row1(), newSliceU.getM_Column1(), newSliceU.getM_Column2());
	        	newSliceU.setM_mush(newSliceU.getM_mush() + new_mush_tomato[0]);
	        	newSliceU.setM_tomato(newSliceU.getM_tomato() + new_mush_tomato[1]);
	        	if(!newSliceU.isToppingOK(m_Pizza.getL(), m_Pizza.getH())) {
	        		isOK = false;
	        	}
	        }
	        if (isOK) {
	        	candidates.add(newSliceU);
	        }
        }
        isOK = true;
        // down
        if(slice.getM_Row2() + 1 < m_Pizza.getR()) {
	        newSliceD.setM_Row2(Math.min(slice.getM_Row2() + 1,  m_Pizza.getR() - 1));
	        if (!noIntersection(slices, newSliceD)) {
	        	isOK = false;
	        } else {
	        	int[] new_mush_tomato = m_Pizza.countMushroomTomato(newSliceD.getM_Row2(), newSliceD.getM_Row2(), newSliceD.getM_Column1(), newSliceD.getM_Column2());
	        	newSliceD.setM_mush(newSliceD.getM_mush() + new_mush_tomato[0]);
	        	newSliceD.setM_tomato(newSliceD.getM_tomato() + new_mush_tomato[1]);
	        	if(!newSliceD.isToppingOK(m_Pizza.getL(), m_Pizza.getH())) {
	        		isOK = false;
	        	}
	        }
	        if (isOK) {
	        	candidates.add(newSliceD);
	        }
        }
        return candidates;
    }
    
    private ArrayList<Slice> dirMinimize(Slice slice, ArrayList<Slice> slices){
    	ArrayList<Slice> candidates = new ArrayList<Slice>();

        Slice newSliceL = new Slice(slice);
        Slice newSliceR = new Slice(slice);
        Slice newSliceU = new Slice(slice);
        Slice newSliceD = new Slice(slice);
        int[] new_mush_tomato;
        // left, right
        if (slice.getM_Column1() < slice.getM_Column2()) {
	        newSliceL.setM_Column1(slice.getM_Column1() + 1);
        	new_mush_tomato = m_Pizza.countMushroomTomato(newSliceL.getM_Row1(), newSliceL.getM_Row2(), newSliceL.getM_Column1()-1, newSliceL.getM_Column1()-1);
        	newSliceL.setM_mush(newSliceL.getM_mush() - new_mush_tomato[0]);
        	newSliceL.setM_tomato(newSliceL.getM_tomato() - new_mush_tomato[1]);
        	if(newSliceL.isToppingOK(m_Pizza.getL(), m_Pizza.getH())) {
        		candidates.add(newSliceL);
        	}
        	
        	newSliceR.setM_Column2(slice.getM_Column2() - 1);
        	new_mush_tomato = m_Pizza.countMushroomTomato(newSliceR.getM_Row1(), newSliceR.getM_Row2(), newSliceR.getM_Column2()+1, newSliceR.getM_Column2()+1);
        	newSliceR.setM_mush(newSliceR.getM_mush() - new_mush_tomato[0]);
        	newSliceR.setM_tomato(newSliceR.getM_tomato() - new_mush_tomato[1]);
        	if(newSliceR.isToppingOK(m_Pizza.getL(), m_Pizza.getH())) {
        		candidates.add(newSliceR);
        	}
        }

        // up, down
        if (slice.getM_Row1() < slice.getM_Row2()) {
	        newSliceU.setM_Row1(slice.getM_Row1() + 1);
        	new_mush_tomato = m_Pizza.countMushroomTomato(newSliceU.getM_Row1()-1, newSliceU.getM_Row1()-1, newSliceU.getM_Column1(), newSliceU.getM_Column2());
        	newSliceU.setM_mush(newSliceU.getM_mush() - new_mush_tomato[0]);
        	newSliceU.setM_tomato(newSliceU.getM_tomato() - new_mush_tomato[1]);
        	if(newSliceU.isToppingOK(m_Pizza.getL(), m_Pizza.getH())) {
        		candidates.add(newSliceU);
        	}
        	
        	newSliceD.setM_Row2(slice.getM_Row2() - 1);
        	new_mush_tomato = m_Pizza.countMushroomTomato(newSliceD.getM_Row2()+1, newSliceD.getM_Row2()+1, newSliceD.getM_Column1(), newSliceD.getM_Column2());
        	newSliceD.setM_mush(newSliceD.getM_mush() + new_mush_tomato[0]);
        	newSliceD.setM_tomato(newSliceD.getM_tomato() + new_mush_tomato[1]);
        	if(newSliceD.isToppingOK(m_Pizza.getL(), m_Pizza.getH())) {
        		candidates.add(newSliceD);
        	}
        }
        return candidates;
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
            boolean isOk = noIntersection(child, candidate);
            while (inds[current] < nums[current] && !isOk){
                inds[current]++;
                candidate = slicess.get(current).get(inds[current]);
                isOk = noIntersection(child, candidate);
            }
            if (isOk){
                child.add(candidate);
                inds[current]++;
            }   
            current = 1 - current;            
        }
        return new PizzaTask(m_Pizza, child);
    }
    
    private boolean noIntersection(ArrayList<Slice> child, Slice candidate){
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
