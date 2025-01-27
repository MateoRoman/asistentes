package com.espe.eventos.repositories;

import com.espe.eventos.model.entities.EventoUsuario;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventoUsuarioRepository extends CrudRepository<EventoUsuario, Long> {

    @Query("SELECT eu FROM EventoUsuario eu WHERE eu.eventoId = :eventoId AND eu.usuarioId = :usuarioId")
    Optional<EventoUsuario> findByEventoIdAndUsuarioId(@Param("eventoId") Long eventoId, @Param("usuarioId") Long usuarioId);
}
