package pica;

import java.util.ArrayList;

public class Pizza {
    private int m_R, m_C;
    private int m_L, m_H;
    private boolean[][] m_Pizza; // Mushrooms = true

    public Pizza(ArrayList<String> pizza) {
        String[] params = pizza.get(0).split(" ");
        m_R = Integer.parseInt(params[0]);
        m_C = Integer.parseInt(params[1]);
        m_L = Integer.parseInt(params[2]);
        m_H = Integer.parseInt(params[3]);
        m_Pizza = new boolean[m_R][m_C];
        for(int r = 1; r < m_R; r++){
            String line = pizza.get(r);
            for(int c = 0; c < m_C; c++){
                m_Pizza[r - 1][c] = line.charAt(m_C) == 'M'; 
            }
        }
    }
    
    public int getR(){
        return m_R;
    }
    
    public int getC(){
        return m_R;
    }
    
    public int getL(){
        return m_R;
    }
    
    public int getH(){
        return m_R;
    }
    
    public boolean[][] getPizza(){
        return m_Pizza;
    }
    
    public int[] countMushroomTomato(int row1, int row2, int col1, int col2) {
    	int[] mush_tom = new int[] {0, 0};
    	for(int r = row1; r <= row2; r++) {
    		for(int c = col1; c <= col2; c++) {
    			if(m_Pizza[r][c]) {
    				mush_tom[0]++;
    			} else {
    				mush_tom[1]++;
    			}
    		}
    	}
    	return mush_tom;
    }
}
