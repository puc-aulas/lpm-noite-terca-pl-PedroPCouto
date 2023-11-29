package br.com.pucminas.controledebiblioteca.entities;

import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.enums.Itens;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class Biblioteca {
    private List<Cliente> clienteList;
    private List<Emprestimo> emprestimoList;
    private ItemBibliotecaController controller;
    private static Biblioteca biblioteca;
    private Biblioteca(){
        this.clienteList = new ArrayList<>();
        this.emprestimoList = new ArrayList<>();
        this.controller = new ItemBibliotecaController();
    }

    public static Biblioteca getInstance(){
        if(biblioteca == null){
            biblioteca = new Biblioteca();
        }
        return biblioteca;
    }


    public void criarCliente(String nome, String cpf, String curso, String interesses){
        Generos generoInteresse = Generos.valueOf(interesses);
        clienteList.add(new Cliente(nome, cpf, curso, generoInteresse));
    }

    public void devolver(String cpf, String nomeTitulo){
        Cliente cliente = findClienteByCpf(cpf);
        if(cliente != null){
            Emprestimo emprestimo = findEmprestimoByNomeTituloENaoDevolvido(nomeTitulo, cliente.getEmprestimosEmVigorList());
            if(emprestimo != null){
                emprestimo.devolver();
                System.out.println("Item devolvido com sucesso");
                cliente.devolver(emprestimo);
            } else {
                System.out.println("Item não encontrado");
                System.out.println("Pode já ter sido devolvido ou nunca ter sido emprestado");
            }
        } else {
            System.out.println("Cliente não encontrado no sistema");
        }
    }

    public ItemBiblioteca getItemByNome(String nome){
        return controller.findItemByName(nome);
    }

    public void criarItemBiblioteca(String nome, String nomeAutor, LocalDate dataPublicacao, int quantidade, Itens tipo, List<Generos> generosList){
        this.controller.criarItemBiblioteca(nome, nomeAutor, dataPublicacao, quantidade, tipo, generosList);
    }
    
    public String emprestar(LocalDate inicio, LocalDate fim, String cpf, String titulo){
        Cliente cliente = findClienteByCpf(cpf);
        if(cliente == null){
            return "Cliente não encontrado";
        }
        try{
            cliente.elegivelEmprestimo(titulo);
            Emprestimo emp = this.controller.emprestar(inicio, fim, titulo);

            emprestimoList.add(emp);
            cliente.getEmprestimosEmVigorList().add(emp);
            return "Emprestimo feito com sucesso";


        }catch (Exception e){
            return e.getMessage();
        }
    }
    
    public Cliente findClienteByCpf(String cpf){
        Optional<Cliente> clienteOptional = clienteList.stream()
                .filter(e -> e.getCpf().equals(cpf))
                .findFirst();

        return clienteOptional.orElse(null);
    }

    private Cliente findClienteByNome(String nome){
        Optional<Cliente> clienteOptional = clienteList.stream()
                .filter(e -> e.getNome().equals(nome))
                .findFirst();

        return clienteOptional.orElse(null);
    }

    private Emprestimo findEmprestimoByNomeTituloENaoDevolvido(String nome, List<Emprestimo> emprestimos){
        Optional<Emprestimo> emprestimoOptional = emprestimos.stream()
                .filter(e -> e.getItem().getNome().equals(nome) && !e.isDevolvido())
                .findFirst();

        return emprestimoOptional.orElse(null);
    }

    public void itemListOrderedByNome(){
        for(ItemBiblioteca i : this.controller.itemListOrderedByNome()){
            System.out.println(i);
        }
    }

    public void itemListOrderedByAutor(){
        for(ItemBiblioteca i : this.controller.itemListOrderedByAutor()){
            System.out.println(i);
        }
    }

    public void itemListOrderedByData(){
        for(ItemBiblioteca i : this.controller.itemListOrderedByData()){
            System.out.println(i);
        }
    }

    public void itemListOrderedByTipo(){
        for(ItemBiblioteca i : this.controller.itemListOrderedByTipo()){
            System.out.println(i);
        }
    }

    public Set<ItemBiblioteca> sugerirItens(String cpf) {
        Cliente cliente = findClienteByCpf(cpf);
        Set<Generos> interessesCliente = cliente.getInteressesCliente();
        Set<ItemBiblioteca> itensPorGenero = this.controller.obterItensPorGenero(interessesCliente);
        return itensPorGenero;
    }

    public List<Emprestimo> relatorioEmprestadosPorUsuario(){
        List<Cliente> clientesRel = this.clienteList;
        List<Cliente> sortedList = clientesRel.stream().sorted((item1, item2) -> item1.getNome().compareTo(item2.getNome())).toList();
        List<Emprestimo> emprestimos = new ArrayList<>();
        for(Cliente cli : sortedList){
            emprestimos.addAll(cli.getEmprestimosEmVigor());
        }
        return emprestimos;
    }
    public List<ItemEmprestavel> relatorioEmprestadosPorAno(){
        List<Emprestimo> listaRel = this.emprestimoList;
        List<Emprestimo> sortedList = listaRel.stream().sorted(Comparator.comparing(item -> item.getItem().getDataPublicacao())).toList();
        List<ItemEmprestavel> itensEmprestados = new ArrayList<>();
        for(Emprestimo emp : sortedList){
            itensEmprestados.add(emp.getItem());
        }
        return itensEmprestados;
    }

}
