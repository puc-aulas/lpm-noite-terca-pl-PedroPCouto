package br.com.pucminas.controledebiblioteca.entities;

import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.enums.Itens;

import java.time.LocalDate;
import java.util.List;

public class ItemNaoEmprestavel extends ItemBiblioteca {
    public ItemNaoEmprestavel(String nome, String nomeAutor, LocalDate dataPublicacao, int quantidade, Itens tipo, List<Generos> generosList){
        super(nome, nomeAutor, dataPublicacao, quantidade, tipo, generosList);
    }

}
