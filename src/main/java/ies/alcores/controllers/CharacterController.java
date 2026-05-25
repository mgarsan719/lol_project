package ies.alcores.controllers;

import ies.alcores.persistence.model.Character;
import ies.alcores.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<List<Character>> getAll() {
        return ResponseEntity.ok(this.characterService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> findById(@PathVariable final String id) {

        return this.characterService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Character> create(@RequestBody Character c) {

        Character characterCreated = this.characterService.create(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> update(@PathVariable String id, @RequestBody Character c) {
        return this.characterService.update(id, c)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable final String id) {
        return this.characterService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
