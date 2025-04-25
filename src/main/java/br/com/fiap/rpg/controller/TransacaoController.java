package br.com.fiap.rpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.rpg.service.TransacaoService;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {
    
    @Autowired
    private TransacaoService transacaoService;
    
    @PostMapping("/comprar/{itemId}/personagem/{personagemId}")
    public ResponseEntity<Void> comprarItem(
            @PathVariable Long personagemId,
            @PathVariable Long itemId) {
        transacaoService.comprarItem(personagemId, itemId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/vender/{itemId}/personagem/{personagemId}")
    public ResponseEntity<Void> venderItem(
            @PathVariable Long personagemId,
            @PathVariable Long itemId) {
        transacaoService.venderItem(personagemId, itemId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/trocar")
    public ResponseEntity<Void> trocarItens(
            @RequestParam Long personagem1Id,
            @RequestParam Long item1Id,
            @RequestParam Long personagem2Id,
            @RequestParam Long item2Id) {
        transacaoService.trocarItens(personagem1Id, item1Id, personagem2Id, item2Id);
        return ResponseEntity.ok().build();
    }
}