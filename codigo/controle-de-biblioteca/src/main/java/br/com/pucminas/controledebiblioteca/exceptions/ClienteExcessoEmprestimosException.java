package br.com.pucminas.controledebiblioteca.exceptions;

public class ClienteExcessoEmprestimosException extends Exception{
    public ClienteExcessoEmprestimosException() {
        super("O cliente atingiu o limite de 3 emprestimos e, por isso, não pode requerir mais um");
    }

    public ClienteExcessoEmprestimosException(String message) {
        super(message);
    }
}
