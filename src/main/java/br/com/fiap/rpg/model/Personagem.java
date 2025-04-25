package br.com.fiap.rpg.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    @Min(value = 1, message = "Nível mínimo é 1")
    @Max(value = 99, message = "Nível máximo é 99")
    private int nivel;

    @Min(value = 0, message = "Quantidade de moedas não pode ser negativa")
    private int moedas;

    @OneToMany(mappedBy = "dono")
    private List<Item> itens = new ArrayList<>();
}