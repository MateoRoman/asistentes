package com.espe.eventos.services;

import com.espe.eventos.model.entities.Evento;
import com.espe.eventos.model.entities.Usuario;
import com.espe.eventos.model.repositories.EventoRepository;
import com.espe.eventos.model.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public void deleteById(Long id) {
        eventoRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> addUser(Usuario usuario, Long eventoId) {
        Optional<Evento> eventoOptional = eventoRepository.findById(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            // Asociar el usuario al evento
            evento.addEventoUsuario(new EventoUsuario(usuario, evento));
            eventoRepository.save(evento);
            return Optional.of(usuario);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> removeUser(Long eventoId, Long usuarioId) {
        Optional<Evento> eventoOptional = eventoRepository.findById(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            // Eliminar la asociación del usuario con el evento
            evento.getEventoUsuarios().removeIf(eventoUsuario -> eventoUsuario.getUsuario().getId().equals(usuarioId));
            eventoRepository.save(evento);
            return Optional.ofNullable(usuarioRepository.findById(usuarioId).orElse(null));
        }
        return Optional.empty();
    }

    @Override
    public List<Usuario> listarAsistentes(Long eventoId) {
        Optional<Evento> eventoOptional = eventoRepository.findById(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            // Obtener los usuarios asociados al evento
            return evento.getAsistentes();
        }
        return null;
    }

    @Override
    public List<Evento> listarEventosPorAsistente(Long usuarioId) {
        return eventoRepository.findEventosByUsuarioId(usuarioId);
    }

    @Override
    public boolean cancelarAsistencia(Long eventoId, Long usuarioId) {
        Optional<Evento> eventoOptional = eventoRepository.findById(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            // Eliminar la asociación del usuario con el evento
            boolean removed = evento.getEventoUsuarios().removeIf(eventoUsuario -> eventoUsuario.getUsuario().getId().equals(usuarioId));
            if (removed) {
                eventoRepository.save(evento);
            }
            return removed;
        }
        return false;
    }
}
