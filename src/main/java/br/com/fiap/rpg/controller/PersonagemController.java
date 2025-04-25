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

import br.com.fiap.rpg.model.Classe;
import br.com.fiap.rpg.model.Personagem;
import br.com.fiap.rpg.service.PersonagemService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/personagens")
public class PersonagemController {
    
    @Autowired
    private PersonagemService personagemService;
    
    @GetMapping
    public ResponseEntity<Page<Personagem>> findAll(
            @PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        return ResponseEntity.ok(personagemService.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Personagem> findById(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.findById(id));
    }
    
    @GetMapping("/por-nome")
    public ResponseEntity<Page<Personagem>> findByNome(
            @RequestParam String nome,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(personagemService.findByNome(nome, pageable));
    }
    
    @GetMapping("/por-classe")
    public ResponseEntity<Page<Personagem>> findByClasse(
            @RequestParam Classe classe,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(personagemService.findByClasse(classe, pageable));
    }
    
    @PostMapping
    public ResponseEntity<Personagem> create(@Valid @RequestBody Personagem personagem) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personagemService.save(personagem));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Personagem> update(
            @PathVariable Long id, 
            @Valid @RequestBody Personagem personagem) {
        return ResponseEntity.ok(personagemService.update(id, personagem));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personagemService.delete(id);
        return ResponseEntity.noContent().build();
    }
}