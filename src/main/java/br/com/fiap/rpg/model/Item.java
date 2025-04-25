package br.com.fiap.rpg.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Item {
    private String nome;
    private Tipo tipo;
    private Raridade raridade;
    private int valor;

}
