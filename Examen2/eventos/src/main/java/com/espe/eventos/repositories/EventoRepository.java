package com.espe.eventos.repositories;

import com.espe.eventos.model.entities.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento, Long> {
}
