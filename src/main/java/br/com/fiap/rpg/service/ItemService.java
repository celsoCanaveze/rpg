package br.com.fiap.rpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.rpg.model.Item;
import br.com.fiap.rpg.model.Personagem;
import br.com.fiap.rpg.model.Raridade;
import br.com.fiap.rpg.model.Tipo;
import br.com.fiap.rpg.repository.ItemRepository;
import br.com.fiap.rpg.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private PersonagemRepository personagemRepository;
    
    public Page<Item> findAll(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }
    
    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com id: " + id));
    }
    
    public Page<Item> findByNome(String nome, Pageable pageable) {
        return itemRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }
    
    public Page<Item> findByTipo(Tipo tipo, Pageable pageable) {
        return itemRepository.findByTipo(tipo, pageable);
    }
    
    public Page<Item> findByRaridade(Raridade raridade, Pageable pageable) {
        return itemRepository.findByRaridade(raridade, pageable);
    }
    
    public Page<Item> findByPrecoBetween(int minPreco, int maxPreco, Pageable pageable) {
        return itemRepository.findByPrecoBetween(minPreco, maxPreco, pageable);
    }
    
    @Transactional
    public Item save(Item item, Long donoId) {
        if (donoId != null) {
            Personagem dono = personagemRepository.findById(donoId)
                    .orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado com id: " + donoId));
            item.setDono(dono);
        }
        return itemRepository.save(item);
    }
    
    @Transactional
    public Item update(Long id, Item item, Long donoId) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item não encontrado com id: " + id);
        }
        
        if (donoId != null) {
            Personagem dono = personagemRepository.findById(donoId)
                    .orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado com id: " + donoId));
            item.setDono(dono);
        } else {
            item.setDono(null);
        }
        
        item.setId(id);
        return itemRepository.save(item);
    }
    
    @Transactional
    public void delete(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new EntityNotFoundException("Item não encontrado com id: " + id);
        }
        itemRepository.deleteById(id);
    }
}