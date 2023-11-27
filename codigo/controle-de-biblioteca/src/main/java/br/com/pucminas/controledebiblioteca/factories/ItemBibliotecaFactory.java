package br.com.pucminas.controledebiblioteca.factories;

import br.com.pucminas.controledebiblioteca.entities.ItemBiblioteca;
import br.com.pucminas.controledebiblioteca.entities.ItemEmprestavel;
import br.com.pucminas.controledebiblioteca.entities.ItemNaoEmprestavel;
import br.com.pucminas.controledebiblioteca.enums.Generos;
import br.com.pucminas.controledebiblioteca.enums.Itens;

import java.time.LocalDate;
import java.util.List;

public class ItemBibliotecaFactory {

    public static ItemBiblioteca create(String nome, String nomeAutor, LocalDate dataPublicacao, int quantidade, Itens tipo, List<Generos> generosList, boolean emprestavel) {
        if(emprestavel)
            return new ItemEmprestavel(nome, nomeAutor, dataPublicacao, quantidade, tipo, generosList);

        return new ItemNaoEmprestavel(nome, nomeAutor, dataPublicacao, quantidade, tipo, generosList);
    }
}
