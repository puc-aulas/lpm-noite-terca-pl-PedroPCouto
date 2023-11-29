package br.com.pucminas.controledebiblioteca.enums;

public enum Generos {
    DRAMA("DRAMA"),
    CIENCIAS("CIENCIAS"),
    HISTORIA("HISTORIA"),
    ROMANCE("ROMANCE"),
    MATEMATICA("MATEMATICA"),
    COMEDIA("COMEDIA"),
    FICCAO_CIENTIFICA("FICCAO CIENTIFICA"),
    FICCAO("FICCAO"),
    ACAO("ACAO"),
    INFORMATICA("INFORMATICA"),
    HARDWARE("HARDWARE"),
    VETERINARIA("VETERINARIA"),
    MEDICINA("MEDICINA"),
    OUTROS("OUTROS");

    private String genero;

    Generos(String genero){
        this.genero = genero;
    }

    public String getGenero(){
        return this.genero;
    }
}
