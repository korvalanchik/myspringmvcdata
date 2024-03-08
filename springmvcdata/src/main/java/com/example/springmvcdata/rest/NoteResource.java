package com.example.springmvcdata.rest;

import com.example.springmvcdata.model.NoteDTO;
import com.example.springmvcdata.service.NoteService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/notes", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoteResource {

    private final NoteService noteService;

    public NoteResource(final NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(noteService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createNote(@RequestBody @Valid final NoteDTO noteDTO) {
        final Long createdId = noteService.create(noteDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateNote(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final NoteDTO noteDTO) {
        noteService.update(id, noteDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable(name = "id") final Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
