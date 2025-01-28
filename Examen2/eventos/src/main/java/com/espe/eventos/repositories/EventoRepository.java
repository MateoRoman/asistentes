package com.espe.eventos.repositories;

import com.espe.eventos.model.entities.Evento;
import com.espe.eventos.model.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface EventoRepository extends CrudRepository<Evento, Long> {
}
