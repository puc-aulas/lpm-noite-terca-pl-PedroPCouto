package org.example.entities;

import org.example.entities.interfaces.CDs;
import org.example.entities.interfaces.DVDs;
import org.example.entities.interfaces.Livros;

import java.time.LocalDateTime;

public class Emprestavel extends Item implements Livros, CDs, DVDs, Comparable<Item> {
    private LocalDateTime dataSaida;

    public Emprestavel(String nome, int quantidade, String tipo, String autor, int ano) {
        super(nome, quantidade, tipo, autor, ano);
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }
}