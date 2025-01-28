package com.espe.asistentes.services;

import com.espe.asistentes.model.entities.Asistente;

import java.util.List;
import java.util.Optional;

public interface AsistenteService {

    List<Asistente> findAll(); // Devuelve todos los asistentes

    Optional<Asistente> findById(Long id); // Encuentra un asistente por su ID

    Asistente save(Asistente asistente); // Guarda o actualiza un asistente

    void deleteById(Long id); // Elimina un asistente por su ID
}
