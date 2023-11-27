package br.com.pucminas.controledebiblioteca.entities;

import java.time.LocalDate;

public class Emprestimo {
    private LocalDate inicio;
    private LocalDate fim;
    private ItemEmprestavel item;
    private boolean devolvido;

    public Emprestimo(LocalDate inicio, LocalDate fim, ItemEmprestavel item){
        this.inicio = inicio;
        this.fim = fim;
        this.item = item;
        this.devolvido = false;
    }

    public void devolver(){
        this.devolvido = true;
        this.item.devolver();
    }

    public boolean atrasado(){
        boolean retorno = (!this.devolvido && LocalDate.now().isAfter(this.fim));
        return retorno;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public ItemEmprestavel getItem() {
        return item;
    }

    public void setItem(ItemEmprestavel item) {
        this.item = item;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
