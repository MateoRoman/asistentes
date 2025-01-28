package com.espe.eventos.controllers;

import com.espe.eventos.model.entities.Evento;
import com.espe.eventos.model.entities.Usuario;
import com.espe.eventos.services.EventoService;
import feign.FeignException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.save(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Evento evento, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(
                    err -> errores.put(
                            err.getField(), err.getDefaultMessage()
                    )
            );
            return ResponseEntity.badRequest().body(errores);
        }

        Optional<Evento> eventoOptional = eventoService.findById(id);
        if (eventoOptional.isPresent()) {
            Evento eventoDB = eventoOptional.get();
            eventoDB.setNombre(evento.getNombre());
            eventoDB.setDescripcion(evento.getDescripcion());
            eventoDB.setFecha(evento.getFecha());
            eventoDB.setLugar(evento.getLugar());
            return ResponseEntity.status(HttpStatus.CREATED).body(eventoService.save(eventoDB));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Evento> getAll() {
        return eventoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getById(@PathVariable Long id) {
        Optional<Evento> eventoOptional = eventoService.findById(id);
        if (eventoOptional.isPresent()) {
            return ResponseEntity.ok(eventoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Evento> eventoOptional = eventoService.findById(id);
        if (eventoOptional.isPresent()) {
            eventoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("Error", "Evento no encontrado"));
    }

    @PostMapping("/{eventoId}/asistente/{usuarioId}")
    public ResponseEntity<?> agregarUsuario(@PathVariable Long eventoId, @RequestBody Usuario usuarioId) {
        Optional<Usuario> usuarioOptional;
        try {
            usuarioOptional = eventoService.addUser(usuarioId, eventoId);
        } catch (FeignException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Error", "Asistente o evento no encontrado" + ex.getMessage()));
        }
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{eventoId}/asistente/{usuarioId}")
    public ResponseEntity<?> cancelarParticipacion(@PathVariable Long eventoId, @PathVariable Long usuarioId) {
        Optional<Usuario> usuarioEliminado = eventoService.removeUser(eventoId, usuarioId);
        if (usuarioEliminado.isPresent()) {
            return ResponseEntity.ok(Collections.singletonMap("Participación cancelada", usuarioEliminado.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("Error", "Evento o asistente no encontrado"));
    }

    @GetMapping("/{id}/asistentes")
    public ResponseEntity<List<Usuario>> getAsistentes(@PathVariable Long id) {
        List<Usuario> asistentes = eventoService.listarAsistentes(id);
        if (asistentes != null && !asistentes.isEmpty()) {
            return ResponseEntity.ok(asistentes);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/asistentes/{usuarioId}")
    public ResponseEntity<List<Evento>> getEventosPorAsistente(@PathVariable Long usuarioId) {
        List<Evento> eventos = eventoService.listarEventosPorAsistente(usuarioId);
        if (eventos != null && !eventos.isEmpty()) {
            return ResponseEntity.ok(eventos);
        }
        return ResponseEntity.notFound().build();
    }
}
