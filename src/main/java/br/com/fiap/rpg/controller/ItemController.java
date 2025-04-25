package br.com.fiap.rpg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.rpg.model.Item;
import br.com.fiap.rpg.model.Raridade;
import br.com.fiap.rpg.model.Tipo;
import br.com.fiap.rpg.service.ItemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itens")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    @GetMapping
    public ResponseEntity<Page<Item>> findAll(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(itemService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Item> findById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }
    
    @GetMapping("/por-nome")
    public ResponseEntity<Page<Item>> findByNome(
            @RequestParam String nome,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findByNome(nome, pageable));
    }
    
    @GetMapping("/por-tipo")
    public ResponseEntity<Page<Item>> findByTipo(
            @RequestParam Tipo tipo,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findByTipo(tipo, pageable));
    }
    
    @GetMapping("/por-raridade")
    public ResponseEntity<Page<Item>> findByRaridade(
            @RequestParam Raridade raridade,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findByRaridade(raridade, pageable));
    }
    
    @GetMapping("/por-preco")
    public ResponseEntity<Page<Item>> findByPrecoBetween(
            @RequestParam int minPreco, 
            @RequestParam int maxPreco,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(itemService.findByPrecoBetween(minPreco, maxPreco, pageable));
    }
    
    @PostMapping
    public ResponseEntity<Item> create(
            @Valid @RequestBody Item item,
            @RequestParam(required = false) Long donoId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(item, donoId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Item> update(
            @PathVariable Long id, 
            @Valid @RequestBody Item item,
            @RequestParam(required = false) Long donoId) {
        return ResponseEntity.ok(itemService.update(id, item, donoId));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}