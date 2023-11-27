package br.com.pucminas.controledebiblioteca.entities;

import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.enums.Itens;

import java.time.LocalDate;
import java.util.List;

public class ItemBiblioteca {
    private static int idCounter = 0;
    private int id;
    private String nome;
    private String nomeAutor;
    private LocalDate dataPublicacao;
    protected int quantidade;
    private Itens tipo;
    private List<Generos> generosList;

    public ItemBiblioteca(String nome, String nomeAutor, LocalDate dataPublicacao, int quantidade, Itens tipo, List<Generos> generos){
        this.nome = nome;
        this.nomeAutor = nomeAutor;
        this.dataPublicacao = dataPublicacao;
        this.quantidade = quantidade;
        idCounter = idCounter + 1;
        this.id = idCounter;
        this.tipo = tipo;
        this.generosList = generos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void aumentarQuantidade(int quantidade) {
        this.quantidade = this.quantidade + quantidade;
    }
    public Itens getTipo() {
        return tipo;
    }

    public void setTipo(Itens tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "\n" +"nome: " + nome + " \n " +
                "nomeAutor: " + nomeAutor + " \n " +
                "dataPublicacao: " + dataPublicacao + " \n " +
                "quantidade: " + quantidade + " \n " +
                "tipo: " + tipo.getTipo() + " \n";
    }

    public List<Generos> getGeneros() {
        return this.generosList;
    }
}

