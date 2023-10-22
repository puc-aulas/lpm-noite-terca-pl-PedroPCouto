package org.example.entities;

import java.util.List;

public class Cliente {

    private String nome;
    private String cpf;
    private List<Emprestavel> emprestados;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public List<Emprestavel> getEmprestados() {
        return emprestados;
    }

    public String getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
