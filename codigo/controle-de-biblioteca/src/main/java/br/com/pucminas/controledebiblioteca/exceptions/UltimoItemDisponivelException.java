package br.com.pucminas.controledebiblioteca.exceptions;

public class UltimoItemDisponivelException extends Exception{
    public UltimoItemDisponivelException(){
        super("Você não pode emprestar esse item pois ele é o último exemplar nessa biblioteca no momento");
    }
}
