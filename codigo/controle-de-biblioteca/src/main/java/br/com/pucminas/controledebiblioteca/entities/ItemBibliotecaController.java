package br.com.pucminas.controledebiblioteca.entities;

import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.enums.Itens;
import br.com.pucminas.controledebiblioteca.exceptions.ItemNaoEncontradoException;
import br.com.pucminas.controledebiblioteca.factories.ItemBibliotecaFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ItemBibliotecaController {
    List<ItemEmprestavel> itensEmprestaveis;
    List<ItemNaoEmprestavel> itensNaoEmprestaveis;
    List<ItemBiblioteca> itensTotais;

    public ItemBibliotecaController(){
        this.itensNaoEmprestaveis = new ArrayList<>();
        this.itensEmprestaveis = new ArrayList<>();
        this.itensTotais = new ArrayList<>();
    }

    public void criarItemBiblioteca(String nome, String nomeAutor, LocalDate dataPublicacao, int quantidade, Itens tipo, List<Generos> generosList) {
            ItemBibliotecaFactory factory = new ItemBibliotecaFactory();
            ItemBiblioteca item = ItemBibliotecaFactory.create(nome, nomeAutor, dataPublicacao, quantidade, tipo, generosList, tipo.emprestavel());
            ItemBiblioteca itemResposta = verificaItemJaExistente(item);
            if(itemResposta == null){
                if(tipo.emprestavel()){
                    itensEmprestaveis.add((ItemEmprestavel) item);
                }else {
                    itensNaoEmprestaveis.add((ItemNaoEmprestavel) item);
                }
                this.itensTotais.add(item);
            } else {
                int index = this.itensEmprestaveis.indexOf(itemResposta);
                this.itensEmprestaveis.get(index).aumentarQuantidade(quantidade);
            }

    }

    public ItemBiblioteca verificaItemJaExistente(ItemBiblioteca item){
        Optional<ItemBiblioteca> itemOptional = this.itensTotais.stream().filter(ItemBiblioteca -> ItemBiblioteca.equals(item)).findFirst();
        return itemOptional.orElse(null);
    }

    public Emprestimo emprestar(LocalDate inicio, LocalDate fim, String titulo) throws Exception{
        ItemEmprestavel item = findItemEmprestavelByName(titulo);
        if(item == null){
            throw new ItemNaoEncontradoException();
        }

        item.emprestar();
        return new Emprestimo(inicio, fim, item);
    }

    private ItemEmprestavel findItemEmprestavelByName(String titulo){
        Optional<ItemEmprestavel> clienteOptional = itensEmprestaveis.stream()
                .filter(e -> e.getNome().equals(titulo))
                .findFirst();

        return clienteOptional.orElse(null);
    }

    public ItemBiblioteca findItemByName(String titulo){
        Optional<ItemBiblioteca> clienteOptional = itensTotais.stream()
                .filter(e -> e.getNome().equals(titulo))
                .findFirst();

        return clienteOptional.orElse(null);
    }

    public List<ItemBiblioteca> itemListOrderedByNome(){
        List<ItemBiblioteca> itensTotais = new ArrayList<>(this.itensEmprestaveis);
        itensTotais.addAll(this.itensNaoEmprestaveis);
        return itensTotais.stream().sorted(Comparator.comparing(ItemBiblioteca::getNome)).toList();
    }

    public List<ItemBiblioteca> itemListOrderedByAutor(){
        List<ItemBiblioteca> itensTotais = new ArrayList<>(this.itensEmprestaveis);
        itensTotais.addAll(this.itensNaoEmprestaveis);
        return itensTotais.stream().sorted(Comparator.comparing(ItemBiblioteca::getNomeAutor)).toList();
    }

    public List<ItemBiblioteca> itemListOrderedByData(){
        List<ItemBiblioteca> itensTotais = new ArrayList<>(this.itensEmprestaveis);
        itensTotais.addAll(this.itensNaoEmprestaveis);
        return itensTotais.stream().sorted(Comparator.comparing(ItemBiblioteca::getDataPublicacao)).toList();
    }

    public List<ItemBiblioteca> itemListOrderedByTipo(){
        List<ItemBiblioteca> itensTotais = new ArrayList<>(this.itensEmprestaveis);
        itensTotais.addAll(this.itensNaoEmprestaveis);
        return itensTotais.stream().sorted(Comparator.comparing(i -> i.getTipo().getTipo())).toList();
    }

    public List<ItemBiblioteca> obterItensPorGenero(List<Generos> generosList){
        List<ItemBiblioteca> itensFiltrados = new ArrayList<>();
        for(ItemBiblioteca item : this.itensEmprestaveis){
            List<Generos> generosItem = item.getGeneros();
            for(Generos gen : generosList){
                if(generosItem.contains(gen)){
                    itensFiltrados.add(item);
                }
            }
        }
        return itensFiltrados;
    }
}
