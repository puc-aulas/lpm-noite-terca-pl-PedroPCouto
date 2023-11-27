package br.com.pucminas.controledebiblioteca.utils;

import br.com.pucminas.controledebiblioteca.entities.Biblioteca;
import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.enums.Itens;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MockItens {
    private static final String fileNameItens = "C:\\Users\\Brigh\\Desktop\\controle-de-biblioteca\\mockitens.txt";
    private static final String fileNameClientes = "C:\\Users\\Brigh\\Desktop\\controle-de-biblioteca\\mockClientes.txt";
    private MyFileReader myFileReaderItens;
    private MyFileReader myFileReaderClientes;
    private Biblioteca biblioteca;
    public MockItens(Biblioteca biblioteca){
        this.myFileReaderItens = new MyFileReader(fileNameItens);
        this.biblioteca = biblioteca;
        this.myFileReaderClientes = new MyFileReader(fileNameClientes);
    }

    public void preencheMockItens() throws IOException {
        String[] arrString = myFileReaderItens.read().split("\n");
        for(String s : arrString){
            String[] arrString2 = s.split(";");
            String nome = arrString2[0];
            String dataString = arrString2[1];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate dataConvertida = LocalDate.parse(dataString, formatter);
            String nomeAutor = arrString2[2];
            String tipoString = arrString2[3];
            Itens tipoConvertido = Itens.valueOf(tipoString);
            List<String> generosString = List.of(arrString2[4].split(","));
            List<Generos> generosList = new ArrayList<>();
            for(String stt : generosString){
                generosList.add(Generos.valueOf(stt));
            }
            this.biblioteca.criarItemBiblioteca(nome, nomeAutor, dataConvertida, 2, tipoConvertido, generosList);
        }
    }

    public void preencheMockClientes() throws IOException {
        String[] arrString = myFileReaderClientes.read().split("\n");
        for(String s : arrString){
            String[] arrString2 = s.split(";");
            this.biblioteca.criarCliente(arrString2[0], arrString2[1], arrString2[2], arrString2[3]);
        }
    }

}