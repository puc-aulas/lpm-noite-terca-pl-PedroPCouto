package br.com.pucminas.controledebiblioteca.entities;

import br.com.pucminas.controledebiblioteca.adapters.CursoAdapter;
import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.exceptions.ClienteExcessoEmprestimosException;
import br.com.pucminas.controledebiblioteca.exceptions.ClienteJaPossuiItemException;
import br.com.pucminas.controledebiblioteca.exceptions.ClientePossuiAtrasoException;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Cliente {
    private List<Emprestimo> emprestimosEmVigor;
    private static final int LIMITE_EMPRESTIMOS = 3;
    private String cpf;
    private String nome;
    private String curso;
    private List<Emprestimo> emprestimosPassados;
    private List<Generos> generosInteresse;
    public List<Emprestimo> getEmprestimosEmVigorList() {
        return emprestimosEmVigor;
    }

    public List<Emprestimo> getemprestimosPassadosList() {
        return emprestimosPassados;
    }

    public void setEmprestimosEmVigorList(List<Emprestimo> emprestimoList) {
        this.emprestimosEmVigor = emprestimoList;
    }

    public String getCurso(){
        return this.curso;
    }

    public String getCpf() {
        return cpf;
    }

    public void devolver(Emprestimo item){
        emprestimosEmVigor.remove(item);
        emprestimosPassados.add(item);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Cliente(String nome, String cpf, String curso, Generos interesses){
        this.cpf = cpf;
        this.nome = nome;
        this.curso = curso;
        List<Generos> generosPorCurso = CursoAdapter.adptCursoToInteresses(curso);
        generosPorCurso.add(interesses);
        this.generosInteresse = generosPorCurso;
        this.emprestimosEmVigor = new ArrayList<>();
        this.emprestimosPassados = new ArrayList<>();
    }

    public void elegivelEmprestimo(String titulo) throws Exception {
        boolean algumAtraso = false;
        boolean jaPossuiItem = false;
        for(Emprestimo e : this.emprestimosEmVigor){
            algumAtraso = algumAtraso || e.atrasado();
            if(e.getItem().getNome().equals(titulo)){
                System.out.println("Você já possui um título igual em sua posse no momento");
                jaPossuiItem = true;
            }
        }
        boolean quantidadeEmprestimosMaximo = emprestimosEmVigor.size() == LIMITE_EMPRESTIMOS;
        if(algumAtraso){
            throw new ClientePossuiAtrasoException();
        }
        if(quantidadeEmprestimosMaximo){
            throw new ClienteExcessoEmprestimosException();
        }
        if(jaPossuiItem){
            throw new ClienteJaPossuiItemException();
        }

    }

    public Set<Generos> getInteressesCliente() {
        Set<Generos> generosDoCliente = new HashSet<>(this.generosInteresse);
        for(Emprestimo emp : this.emprestimosPassados){
            generosDoCliente.addAll(emp.getItem().getGeneros());
        }
        for(Emprestimo emp : this.emprestimosEmVigor){
            generosDoCliente.addAll(emp.getItem().getGeneros());
        }
        return generosDoCliente;
    }
}
