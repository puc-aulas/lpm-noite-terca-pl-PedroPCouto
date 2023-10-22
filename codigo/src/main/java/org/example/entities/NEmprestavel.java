package org.example.entities;

import org.example.entities.interfaces.Revistas;
import org.example.entities.interfaces.Teses;

public class NEmprestavel extends Item implements Revistas, Teses, Comparable<Item> {
    public NEmprestavel(String titulo, int quantidade, String tipo, String autor, int ano) {
        super(titulo, quantidade, tipo, autor, ano);
    }
}
