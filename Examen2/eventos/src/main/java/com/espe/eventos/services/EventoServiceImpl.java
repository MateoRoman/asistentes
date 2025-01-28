package com.espe.eventos.services;

import com.espe.eventos.model.entities.Evento;
import com.espe.eventos.model.entities.Usuario;
import com.espe.eventos.repositories.EventoRepository;
import com.espe.eventos.repositories.EventoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private EventoUsuarioRepository eventoUsuarioRepository;

    @Override
    public Evento save(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    public List<Evento> findAll() {
        return (List<Evento>) eventoRepository.findAll();
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
            if (!evento.getUsuarios().contains(usuario)) {
                evento.getUsuarios().add(usuario);
                eventoRepository.save(evento);
                return Optional.of(usuario);
            } else {
                throw new RuntimeException("Usuario ya está registrado en el evento");
            }
        }
        throw new RuntimeException("Evento no encontrado");
    }

    @Override
    public Optional<Usuario> removeUser(Long eventoId, Long usuarioId) {
        Optional<Evento> eventoOptional = eventoRepository.findById(eventoId);
        if (eventoOptional.isPresent()) {
            Evento evento = eventoOptional.get();
            Optional<Usuario> usuarioOptional = evento.getUsuarios().stream()
                    .filter(usuario -> usuario.getId().equals(usuarioId))
                    .findFirst();
            if (usuarioOptional.isPresent()) {
                evento.getUsuarios().remove(usuarioOptional.get());
                eventoRepository.save(evento);
                return usuarioOptional;
            } else {
                throw new RuntimeException("Usuario no encontrado en el evento");
            }
        }
        throw new RuntimeException("Evento no encontrado");
    }

    @Override
    public List<Usuario> listarAsistentes(Long eventoId) {
        Optional<Evento> eventoOptional = eventoRepository.findById(eventoId);
        return eventoOptional.map(Evento::getUsuarios).orElse(null);
    }

    @Override
    public List<Evento> listarEventosPorAsistente(Long usuarioId) {
        return List.of();
    }

}
