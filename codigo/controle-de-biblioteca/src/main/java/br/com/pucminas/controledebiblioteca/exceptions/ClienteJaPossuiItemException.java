package br.com.pucminas.controledebiblioteca.exceptions;

public class ClienteJaPossuiItemException extends  Exception{
    public ClienteJaPossuiItemException() {
        super("O cliente já possui o mesmo item em sua posse emprestado por essa biblioteca");
    }

    public ClienteJaPossuiItemException(String message) {
        super(message);
    }

}
