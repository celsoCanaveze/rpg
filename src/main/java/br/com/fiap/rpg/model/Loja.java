package br.com.fiap.rpg.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;
    
    @Min(value = 0, message = "Bonus de compra não pode ser negativo")
    private int bonusCompra;
    
    @Min(value = 0, message = "Bonus de venda não pode ser negativo")
    private int bonusVenda;
    
    // Relacionamento com os itens da loja
    @OneToMany(mappedBy = "loja")
    private List<Item> itens;
}