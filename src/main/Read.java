package main;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Read {
    private String m_File;
    private ArrayList<String> m_Lines;
    
    public Read(String file) throws IOException {
        m_File = file;
        m_Lines = new ArrayList<String>();
        
        BufferedReader br = new BufferedReader(new FileReader(m_File));
        try {
            String line = br.readLine();
            while (line != null) {
                m_Lines.add(line);
                line = br.readLine();
            }
        } finally {
            br.close();
        }
    }
    
    public ArrayList<String> getLines(){
        return m_Lines;
    }

}
