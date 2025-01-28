package com.espe.eventos.repositories;

import com.espe.eventos.model.entities.EventoUsuario;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EventoUsuarioRepository extends CrudRepository<EventoUsuario, Long> {

    @Query("SELECT eu FROM EventoUsuario eu WHERE eu.evento_id = :eventoId AND eu.usuario_id = :usuarioId")
    Optional<EventoUsuario> findByEventoIdAndUsuarioId(@Param("evento_id") Long eventoId, @Param("usuario_id") Long usuario_id);

}
