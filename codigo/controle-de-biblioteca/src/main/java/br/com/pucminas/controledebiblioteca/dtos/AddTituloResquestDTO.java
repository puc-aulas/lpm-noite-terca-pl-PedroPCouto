package br.com.pucminas.controledebiblioteca.dtos;

import br.com.pucminas.controledebiblioteca.enums.Generos;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AddTituloResquestDTO {
    private String nome;
    private String nomeAutor;
    private String dataPublicacao;
    private int quantidade;
    private List<String> genero;
    private String itens;

    public AddTituloResquestDTO(String nome, String nomeAutor, String dataPublicacao, int quantidade, List<Generos> generos, String itens){
        this.nome = nome;
        this.nomeAutor = nomeAutor;
        this.dataPublicacao = dataPublicacao;
        this.quantidade = quantidade;
        this.genero = new ArrayList<>();
        for(Generos gen : generos){
            this.genero.add(gen.getGenero());
        }
        this.itens = itens;
    }

}
