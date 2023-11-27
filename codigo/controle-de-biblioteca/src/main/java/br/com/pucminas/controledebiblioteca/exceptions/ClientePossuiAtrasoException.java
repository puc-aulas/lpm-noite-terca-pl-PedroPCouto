package br.com.pucminas.controledebiblioteca.exceptions;

public class ClientePossuiAtrasoException extends Exception{
    public ClientePossuiAtrasoException() {
        super("O cliente possui algum atraso em seus emprestimos vigentes");
    }

    public ClientePossuiAtrasoException(String message) {
        super(message);
    }
}
