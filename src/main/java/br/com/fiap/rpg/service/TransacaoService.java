package br.com.fiap.rpg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.rpg.exception.TransacaoException;
import br.com.fiap.rpg.model.Item;
import br.com.fiap.rpg.model.Personagem;
import br.com.fiap.rpg.repository.ItemRepository;
import br.com.fiap.rpg.repository.PersonagemRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TransacaoService {

    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private PersonagemRepository personagemRepository;
    
    @Transactional
    public void comprarItem(Long personagemId, Long itemId) {
        Personagem comprador = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new EntityNotFoundException("Personagem comprador não encontrado com id: " + personagemId));
        
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com id: " + itemId));
        
        if (item.getDono() == null) {
            // Compra do mercado
            if (comprador.getMoedas() < item.getPreco()) {
                throw new TransacaoException("Personagem não possui moedas suficientes para comprar este item");
            }
            
            comprador.setMoedas(comprador.getMoedas() - item.getPreco());
            item.setDono(comprador);
            
            personagemRepository.save(comprador);
            itemRepository.save(item);
        } else {
            throw new TransacaoException("Este item já possui um dono e não está à venda no mercado");
        }
    }
    
    @Transactional
    public void venderItem(Long personagemId, Long itemId) {
        Personagem vendedor = personagemRepository.findById(personagemId)
                .orElseThrow(() -> new EntityNotFoundException("Personagem vendedor não encontrado com id: " + personagemId));
        
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item não encontrado com id: " + itemId));
        
        if (item.getDono() != null && item.getDono().getId().equals(personagemId)) {
            // Venda para o mercado
            vendedor.setMoedas(vendedor.getMoedas() + item.getPreco());
            item.setDono(null);
            
            personagemRepository.save(vendedor);
            itemRepository.save(item);
        } else {
            throw new TransacaoException("Este item não pertence ao personagem indicado");
        }
    }
    
    @Transactional
    public void trocarItens(Long personagem1Id, Long item1Id, Long personagem2Id, Long item2Id) {
        Personagem personagem1 = personagemRepository.findById(personagem1Id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem 1 não encontrado com id: " + personagem1Id));
        
        Personagem personagem2 = personagemRepository.findById(personagem2Id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem 2 não encontrado com id: " + personagem2Id));
        
        Item item1 = itemRepository.findById(item1Id)
                .orElseThrow(() -> new EntityNotFoundException("Item 1 não encontrado com id: " + item1Id));
        
        Item item2 = itemRepository.findById(item2Id)
                .orElseThrow(() -> new EntityNotFoundException("Item 2 não encontrado com id: " + item2Id));
        
        if (item1.getDono() == null || !item1.getDono().getId().equals(personagem1Id)) {
            throw new TransacaoException("Item 1 não pertence ao personagem 1");
        }
        
        if (item2.getDono() == null || !item2.getDono().getId().equals(personagem2Id)) {
            throw new TransacaoException("Item 2 não pertence ao personagem 2");
        }
        
        // Realizando a troca
        item1.setDono(personagem2);
        item2.setDono(personagem1);
        
        itemRepository.save(item1);
        itemRepository.save(item2);
    }
}