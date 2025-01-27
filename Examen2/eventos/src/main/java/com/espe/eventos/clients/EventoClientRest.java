package com.espe.eventos.clients;

import com.espe.eventos.model.entities.Evento;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "eventos",
        url = "localhost:8004/api/"  // Cambiar URL según el microservicio de eventos
)
public interface EventoClientRest {

    @GetMapping("/eventos")
    List<Evento> listarEventos();

    @GetMapping("/eventos/{id}")
    Evento findById(@PathVariable("id") Long id);

    @PostMapping("/eventos")
    Evento crearEvento(@RequestBody Evento evento);

    @DeleteMapping("/eventos/{id}")
    void eliminarEvento(@PathVariable("id") Long id);
}
