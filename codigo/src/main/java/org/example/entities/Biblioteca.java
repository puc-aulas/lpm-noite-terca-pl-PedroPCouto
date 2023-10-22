package org.example.entities;

import org.example.entities.interfaces.Revistas;
import org.example.entities.interfaces.Teses;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Biblioteca {

    private static final Integer QUANTIDADEMAXIMA = 3;
    private static final Integer DIASMAXIMOS = 10;
    private static final Integer ESTOQUEMINIMO = 1;
    private String nome;
    private List<Item> itens;
    private List<Cliente> clientes;

    public boolean cadastrarCliente(String nome, String cpf){
        if (!verificaCadastrados(cpf)){
            clientes.add(new Cliente(nome, cpf));
            return true;
        }

        return false;
    }

    public void editaCliente(String cpf, int escolha, String opcao){
        Cliente cliente = findByCpf(cpf);
        switch (escolha){
            case 1 :
                cliente.setCpf(opcao);
                break;
            case 2 :
                cliente.setNome(opcao);
                break;
        }
    }

    public void cadastrarItem(String tipo, String nome, int quantidade, String autor, int ano){
        switch (tipo){
            case "Teses" :
                new NEmprestavel(nome, quantidade, tipo, autor, ano);
            case "Revistas" :
                new NEmprestavel(nome, quantidade, tipo, autor, ano);
            case "Livros" :
            case "CDs" :
            case "DVDs" :
                new Emprestavel(nome, quantidade, tipo, autor, ano);
                break;
        }
    }

    public void editarItem(String nome, int escolha, String opcao){
        Item item = findByNome(nome);
        switch (escolha){
            case 1 :
                item.setTitulo(opcao);
                break;
            case 2:
                item.setAutor(opcao);
            case 3:
                item.setAno(Integer.parseInt(opcao));

        }
    }

    private boolean verificaCadastrados(String cpf) {
        for (Cliente cl : clientes){
            if (cl.getCpf().equals(cpf)){
                return true;
            }
        }

        return false;
    }

    public Cliente findByCpf(String cpf){
        for (Cliente cl : clientes){
            if (cl.getCpf().equals(cpf)){
                return cl;
            }
        }

        return null;
    }

    public Item findByNome(String nome){
        for (Item item : itens){
            if (item.getTitulo().equals(nome)){
                return item;
            }
        }

        return null;
    }

    public List<Item> findByAutor(String nome){
        List iten = new ArrayList();
        for (Item item : itens){
            if (item.getAutor().equals(nome)){
                iten.add(item);
            }
        }

        Collections.sort(iten);
        return iten;
    }

    public List<Item> findByTipo(String nome){
        List iten = new ArrayList();
        for (Item item : itens){
            if (item.getTipo().equals(nome)){
                iten.add(item);
            }
        }

        Collections.sort(iten);
        return iten;
    }

    public List<Item> findByAno(int ano){
        List iten = new ArrayList();
        for (Item item : itens){
            if (item.getAno() == ano){
                iten.add(item);
            }
        }

        Collections.sort(iten);
        return iten;
    }




    private List<Integer> verificaData(List<Emprestavel> item){
        List<Integer> aux = new ArrayList<>();
        for (Emprestavel emp : item){
            int validade = LocalDateTime.now().compareTo(emp.getDataSaida().plusDays(DIASMAXIMOS));
            if (validade <= 0);
            aux.add(validade);
        }

        return aux;
    }

    private boolean emprestimoVencido(List<Emprestavel> item){
        return verificaData(item).size() > 0;
    }

    private List<String> validaEstoque(List<Emprestavel> emprestaveis){
        List<String> aux = new ArrayList<>();
        for (Item item : emprestaveis){

            if (findByNome(item.getTitulo()).getQuantidade() <= ESTOQUEMINIMO){
                aux.add(item.getTitulo());
            }
        }

        return aux;
    }

    public String emprestarLivro(String cpf, List<Emprestavel> emprestaveis){
        Cliente cliente = findByCpf(cpf);

        if (emprestimoVencido(cliente.getEmprestados())){
            return "Há emprestímos pendentes em sua conta. Devolva primeiro suas pendencias";
        }

        List<String> itens = validaEstoque(emprestaveis);
        if (itens.size() > 0){
            return "A biblioteca tem apenas um exemplar deste(s) livro(s)" + itens;
        }

        if (quantidadeMaxima(emprestaveis)){
            return "Pegue apenas " + QUANTIDADEMAXIMA + " itens por empréstimo";
        }

        cliente.getEmprestados().add((Emprestavel) emprestaveis);
        return "Item emprestado com sucesso";
    }

    private boolean quantidadeMaxima(List<Emprestavel> emprestaveis) {
        if (emprestaveis.size() > QUANTIDADEMAXIMA){
            return true;
        }

        return false;
    }

    public List<Item> searchBy(int opcao, String escolha){
        List<Item> item = new ArrayList<>();
        switch (opcao){
            case 1 :
                item = (List<Item>) findByNome(escolha);
                break;
            case 2 :
                item = findByAutor(escolha);
                break;
            case 3:
                item = findByAno(Integer.parseInt(escolha));
            case 4:
                item = findByTipo(escolha);
        }

        return item;
    }


}
