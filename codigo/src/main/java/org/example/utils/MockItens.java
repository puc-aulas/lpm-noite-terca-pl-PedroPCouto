package org.example.utils;

public class MockItens {
    private static final String fileName = "mockitens.txt";
    private MyFileReader myFileReader;
    private  Biblioteca biblioteca;
    public MockItens(Biblioteca biblioteca){
        this.myFileReader = new MyFileReader(fileName);
        this.biblioteca = biblioteca;
    }

    public void preencheMock(){
        String[] arrString = myFileReader.read().split("\n");
        for(String s : arrString){
            String[] arrString2 = s.split(";");
            this.biblioteca.cadastrarItem(arrString2[3], arrString2[0], 1, arrString2[2], Integer.valueOf(arrString2[1]));
        }
    }

}