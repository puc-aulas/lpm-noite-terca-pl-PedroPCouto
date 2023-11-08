package org.example.entities;

import javax.sound.midi.SysexMessage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private static final Integer QUANTIDADEMAXIMA = 3;
    private static final Integer DIASMAXIMOS = 10;
    private static final Integer ESTOQUEMINIMO = 1;
    private String nome;
    private List<Item> itens;
    private List<Cliente> clientes;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.itens = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public boolean cadastrarCliente(String nome, String cpf){
        if (!verificaCadastrados(cpf)){
            clientes.add(new Cliente(nome, cpf));
            return true;
        }

        return false;
    }

    public List<Emprestavel> retornaItensDisponiveis(){
        List<Emprestavel> lista = new ArrayList<>();
        for (Item item : itens){
            if (item.getTipo().equals("Livros") || item.getTipo().equals("CDs") || item.getTipo().equals("DVDs")){
                lista.add((Emprestavel) item);
            }
        }

        return lista;
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
                itens.add(new NEmprestavel(nome, quantidade, tipo, autor, ano));
                break;
            case "Revistas" :
                itens.add(new NEmprestavel(nome, quantidade, tipo, autor, ano));
                break;
            case "Livros" :
                itens.add(new Emprestavel(nome, quantidade, tipo, autor, ano));
                break;
            case "CDs" :
                itens.add(new Emprestavel(nome, quantidade, tipo, autor, ano));
                break;
            case "DVDs" :
                itens.add(new Emprestavel(nome, quantidade, tipo, autor, ano));
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

    public boolean verificaCadastrados(String cpf) {
        for (Cliente cl : clientes){
            if (cl.getCpf().equals(cpf)){
                System.out.println(cl);
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
                System.out.println(item);
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
        System.out.println(iten);
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
        System.out.println(iten);
        return iten;
    }

    public List<Item> findByAno(int ano){
        List<Item> iten = new ArrayList();
        for (Item item : itens){
            if (item.getAno() == ano){
                iten.add(item);
            }
        }

        Collections.sort(iten);
        for (Item item : iten){
            System.out.println(item);
        }
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

    public void relatorioEmprestadosPorAno(){
        List<Emprestavel> listaRel = retornaItensDisponiveis();
        List<Emprestavel> sortedList = listaRel.stream().sorted((item1, item2) -> item1.getAno() > item2.getAno() ? 1 : 0).toList();
        for(Emprestavel emp : sortedList){
            System.out.println(emp.getTitulo() + " - " + emp.getEmprestado() + " - " + emp.getAno());
        }
    }

    public void relatorioEmprestadosPorUsuario(){
        List<Cliente> clientesRel = this.clientes;
        List<Cliente> sortedList = clientesRel.stream().sorted((item1, item2) -> item1.getNome().compareTo(item2.getNome())).toList();
        for(Cliente cli : sortedList){
            System.out.println(cli.getNome());
            for(Emprestavel emp : cli.getEmprestados()){
                System.out.println(emp.getTitulo() + " - " + emp.getEmprestado() + " - " + emp.getAno());
            }
        }
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

        System.out.println(emprestaveis);
        if (cliente.getEmprestados() != null && emprestimoVencido(cliente.getEmprestados())){
            return "Há emprestímos pendentes em sua conta. Devolva primeiro suas pendencias";
        }

        List<String> itens = validaEstoque(emprestaveis);
        if (itens.size() > 0){
            return "A biblioteca tem apenas um exemplar deste(s) livro(s)" + itens;
        }

        if (quantidadeMaxima(emprestaveis)){
            return "Pegue apenas " + QUANTIDADEMAXIMA + " itens por empréstimo";
        }

        for (Emprestavel emp : emprestaveis){
            emp.addQntImprestaveis();

            itens.remove((emp).getTitulo());
            emp.setDataSaida(LocalDateTime.now());
            cliente.getEmprestados().add(emp);
        }

        return "Iten emprestado com sucesso";
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
