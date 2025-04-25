package br.com.fiap.rpg.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Personagem {

    @NotBlank
    private String nome;

    private Classe classe;

    @Size(min = 1, max = 99)
    private int nivel;

    @Size(min = 0, max = 9999)
    private int moedas;

    private Item[] items;
}
