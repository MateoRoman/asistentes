package com.espe.eventos.services;

import com.espe.eventos.model.entities.Evento;
import com.espe.eventos.model.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface EventoService {

    List<Evento> findAll();
    Optional<Evento> findById(Long id);
    Evento save(Evento evento);
    void deleteById(Long id);
    Optional<Usuario> addUser(Usuario usuario, Long id);
    Optional<Usuario> removeUser(Long eventoId, Long usuarioId);
    List<Usuario> listarAsistentes(Long eventoId);
    List<Evento> listarEventosPorAsistente(Long usuarioId);
    boolean cancelarAsistencia(Long eventoId, Long usuarioId);
}
