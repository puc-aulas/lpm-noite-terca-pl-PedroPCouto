package org.example.entities;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Item implements Comparable<Item>{
    UUID id;
    private String titulo;

    private int quantidade;
    private String tipo;
    private String autor;
    private int ano;

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Item(String titulo, int quantidade, String tipo, String autor, int ano) {
        this.id = UUID.randomUUID();
        this.titulo = titulo;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.autor = autor;
        this.ano = ano;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public int compareTo(Item other) {
        return titulo.compareTo(other.getTitulo());
    }

}
