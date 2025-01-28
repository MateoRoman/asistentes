package com.espe.eventos.services;

import com.espe.eventos.model.entities.Evento;
import com.espe.eventos.model.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface EventoService {

    // Guardar un evento
    Evento save(Evento evento);

    // Buscar un evento por su ID
    Optional<Evento> findById(Long id);

    // Obtener todos los eventos
    List<Evento> findAll();

    // Eliminar un evento por su ID
    void deleteById(Long id);

    // Agregar un usuario (asistente) a un evento
    Optional<Usuario> addUser(Usuario usuario, Long eventoId);

    // Eliminar un usuario (asistente) de un evento
    Optional<Usuario> removeUser(Long eventoId, Long usuarioId);

    // Listar asistentes de un evento
    List<Usuario> listarAsistentes(Long eventoId);

    // Listar eventos en los que participó un usuario
    List<Evento> listarEventosPorAsistente(Long usuarioId);
}
