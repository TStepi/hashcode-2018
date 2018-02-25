package pica;

public class Slice implements Comparable<Slice> {
    private int m_Row1, m_Column1, m_Row2, m_Column2;
    private int m_mush, m_tomato;
    
    public int getM_mush() {
        return m_mush;
    }

    public void setM_mush(int m_mush) {
        this.m_mush = m_mush;
    }

    public int getM_tomato() {
        return m_tomato;
    }

    public void setM_tomato(int m_tomato) {
        this.m_tomato = m_tomato;
    }

    public Slice(int r1, int c1, int r2, int c2, int mush, int tom) {
        m_Row1 = Math.min(r1, r2);
        m_Row2 = Math.max(r1, r2);
        m_Column1 = Math.min(c1, c2);
        m_Column2 = Math.max(c1, c2);
        m_mush = mush;
        m_tomato = tom;
    }
    
    public Slice(Slice slice){
        m_Row1 = slice.getM_Row1();
        m_Row2 = slice.getM_Row2();
        m_Column1 = slice.getM_Column1();
        m_Column2 = slice.getM_Column2();
        m_mush = slice.getM_mush();
        m_tomato = slice.getM_tomato();
    }

    public void setM_Row1(int m_Row1) {
        this.m_Row1 = m_Row1;
    }

    public void setM_Column1(int m_Column1) {
        this.m_Column1 = m_Column1;
    }

    public void setM_Row2(int m_Row2) {
        this.m_Row2 = m_Row2;
    }

    public void setM_Column2(int m_Column2) {
        this.m_Column2 = m_Column2;
    }

    public int getM_Row1() {
        return m_Row1;
    }

    public int getM_Column1() {
        return m_Column1;
    }

    public int getM_Row2() {
        return m_Row2;
    }

    public int getM_Column2() {
        return m_Column2;
    }
    
    public int area(){
        return (Math.abs(m_Row2 - m_Row1) + 1) * (Math.abs(m_Column2 - m_Column1) + 1);
    }

    @Override
    public int compareTo(Slice other) {
        int areaDiff =  this.area() - other.area();
        if (areaDiff == 0){
            return 0;
        } else {
            return areaDiff < 0 ? 1 : -1;
        }
    }
    
    public boolean nonEmptyIntersection(Slice other){
        return nonEmptyIntervalIntersection(m_Row1, m_Row2, other.m_Row1, other.m_Row2)
                &&
               nonEmptyIntervalIntersection(m_Column1, m_Column2, other.m_Column1, other.m_Column2);
    }
    
    public boolean nonEmptyIntervalIntersection(int start1, int end1, int start2, int end2){
        return Math.max(start1, start2) < Math.min(end1, end2);
    }
}
