package org.example.utils;

import org.example.entities.Biblioteca;

import java.io.IOException;

public class MockItens {
    private static final String fileName = "C:\\Users\\gustavo.riegert_evol\\IdeaProjects\\lpm-noite-terca-pl-PedroPCouto\\codigo\\src\\mockitens.txt";
    private MyFileReader myFileReader;
    private Biblioteca biblioteca;
    public MockItens(Biblioteca biblioteca){
        this.myFileReader = new MyFileReader(fileName);
        this.biblioteca = biblioteca;
    }

    public void preencheMock() throws IOException {
        String[] arrString = myFileReader.read().split("\n");
        for(String s : arrString){
            String[] arrString2 = s.split(";");
            System.out.println(arrString2[0]);
            this.biblioteca.cadastrarItem(arrString2[3], arrString2[0], 1, arrString2[2], Integer.valueOf(arrString2[1]));
        }
    }

}