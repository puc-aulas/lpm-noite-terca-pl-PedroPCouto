package br.com.pucminas.controledebiblioteca.interfaces;

public interface IEmprestavel {
    boolean emprestar() throws Exception;

    void devolver();
}
