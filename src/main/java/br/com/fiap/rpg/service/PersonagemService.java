package br.com.fiap.rpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.rpg.model.Classe;
import br.com.fiap.rpg.model.Personagem;
import br.com.fiap.rpg.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository personagemRepository;
    
    public Page<Personagem> findAll(Pageable pageable) {
        return personagemRepository.findAll(pageable);
    }
    
    public Personagem findById(Long id) {
        return personagemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado com id: " + id));
    }
    
    public Page<Personagem> findByNome(String nome, Pageable pageable) {
        return personagemRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }
    
    public Page<Personagem> findByClasse(Classe classe, Pageable pageable) {
        return personagemRepository.findByClasse(classe, pageable);
    }
    
    @Transactional
    public Personagem save(Personagem personagem) {
        return personagemRepository.save(personagem);
    }
    
    @Transactional
    public Personagem update(Long id, Personagem personagem) {
        if (!personagemRepository.existsById(id)) {
            throw new EntityNotFoundException("Personagem não encontrado com id: " + id);
        }
        personagem.setId(id);
        return personagemRepository.save(personagem);
    }
    
    @Transactional
    public void delete(Long id) {
        if (!personagemRepository.existsById(id)) {
            throw new EntityNotFoundException("Personagem não encontrado com id: " + id);
        }
        personagemRepository.deleteById(id);
    }
}