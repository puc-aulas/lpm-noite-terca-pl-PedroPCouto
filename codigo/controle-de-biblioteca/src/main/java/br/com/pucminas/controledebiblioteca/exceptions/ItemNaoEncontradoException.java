package br.com.pucminas.controledebiblioteca.exceptions;

public class ItemNaoEncontradoException extends Exception {
    public ItemNaoEncontradoException() {
        super("O item que está procurando não foi encontrado");
    }
}
