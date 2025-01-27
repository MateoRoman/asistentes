package com.espe.asistentes.controllers;

import com.espe.asistentes.model.entities.Asistente;
import com.espe.asistentes.services.AsistenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/asistentes")
public class AsistenteController {

    @Autowired
    private AsistenteService service;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Asistente asistente, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(
                    err -> errores.put(
                            err.getField(), err.getDefaultMessage()
                    )
            );
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(asistente));
    }

    @GetMapping
    public List<Asistente> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistente> getById(@PathVariable Long id) {
        Optional<Asistente> asistenteOptional = service.findById(id);
        if (asistenteOptional.isPresent()) {
            return ResponseEntity.ok(asistenteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Asistente asistente, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(
                    err -> errores.put(
                            err.getField(), err.getDefaultMessage()
                    )
            );
            return ResponseEntity.badRequest().body(errores);
        }
        Optional<Asistente> asistenteOptional = service.findById(id);
        if (asistenteOptional.isPresent()) {
            Asistente asistenteDB = asistenteOptional.get();
            asistenteDB.setNombre(asistente.getNombre());
            asistenteDB.setApellido(asistente.getApellido());
            asistenteDB.setEmail(asistente.getEmail());
            asistenteDB.setTelefono(asistente.getTelefono());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(asistenteDB));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
