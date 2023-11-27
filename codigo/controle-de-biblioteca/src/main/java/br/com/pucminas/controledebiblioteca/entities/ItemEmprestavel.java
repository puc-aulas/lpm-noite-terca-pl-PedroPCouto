package br.com.pucminas.controledebiblioteca.entities;

import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.enums.Itens;
import br.com.pucminas.controledebiblioteca.exceptions.UltimoItemDisponivelException;
import br.com.pucminas.controledebiblioteca.interfaces.IEmprestavel;

import java.time.LocalDate;
import java.util.List;

public class ItemEmprestavel extends ItemBiblioteca implements IEmprestavel {

    private int quantEmprestado;
    public ItemEmprestavel(String nome, String nomeAutor, LocalDate dataPublicacao, int quantidade, Itens tipo, List<Generos> generosList){
        super(nome, nomeAutor, dataPublicacao, quantidade, tipo, generosList);
        this.quantEmprestado = 0;
    }

    public boolean emprestar() throws Exception {
        if(this.quantidade == 1){
            throw new UltimoItemDisponivelException();
        }

        this.quantEmprestado = this.quantEmprestado + 1;
        this.quantidade = this.quantidade - 1;
        return true;
    }

    public void devolver(){
        this.quantidade = this.quantidade + 1;
    }
}
