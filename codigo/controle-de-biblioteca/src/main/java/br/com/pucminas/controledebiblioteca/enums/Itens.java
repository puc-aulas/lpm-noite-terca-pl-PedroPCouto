package br.com.pucminas.controledebiblioteca.enums;

public enum Itens {
    CD("CD", true),
    DVD("DVD", true),
    LIVRO("LIVRO", true),
    REVISTA("REVISTA", false),
    ARTIGO("ARTIGO", false);

    private String tipo;
    private boolean emprestavel;

    Itens(String tipo, boolean emprestavel){
        this.tipo = tipo;
        this.emprestavel = emprestavel;
    }

    public String getTipo(){
        return tipo;
    }

    public boolean emprestavel(){
        return emprestavel;
    }

}
