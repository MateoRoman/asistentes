package com.espe.asistentes.services;

import com.espe.asistentes.model.entities.Asistente;

import java.util.List;
import java.util.Optional;

public interface AsistenteService {

    List<Asistente> findAll();

    Optional<Asistente> findById(Long id);

    Asistente save(Asistente asistente);

    void deleteById(Long id);
}
