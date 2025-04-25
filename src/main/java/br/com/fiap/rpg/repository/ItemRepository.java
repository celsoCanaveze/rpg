package br.com.fiap.rpg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.rpg.model.Item;
import br.com.fiap.rpg.model.Raridade;
import br.com.fiap.rpg.model.Tipo;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Item> findByTipo(Tipo tipo, Pageable pageable);
    Page<Item> findByRaridade(Raridade raridade, Pageable pageable);
    
    @Query("SELECT i FROM Item i WHERE i.preco >= :minPreco AND i.preco <= :maxPreco")
    Page<Item> findByPrecoBetween(@Param("minPreco") int minPreco, @Param("maxPreco") int maxPreco, Pageable pageable);
}