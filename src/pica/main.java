package pica;

import java.io.IOException;

import main.Read;

public class main {

    public main() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) throws IOException {
        String inputData = args[0];
        Pizza pizza = new Pizza(new Read(inputData).getLines());
        
    }

}
