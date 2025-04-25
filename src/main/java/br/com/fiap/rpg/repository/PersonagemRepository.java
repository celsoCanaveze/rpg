package br.com.fiap.rpg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.rpg.model.Classe;
import br.com.fiap.rpg.model.Personagem;

public interface PersonagemRepository extends JpaRepository<Personagem, Long> {
    Page<Personagem> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
    Page<Personagem> findByClasse(Classe classe, Pageable pageable);
}