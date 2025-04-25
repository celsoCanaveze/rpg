package br.com.fiap.rpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    
    @Enumerated(EnumType.STRING) 
    private Raridade raridade;
    
    @Positive(message = "Valor deve ser maior que zero")
    private int preco;
    
    @ManyToOne
    private Personagem dono;
}