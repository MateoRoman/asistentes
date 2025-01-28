package com.espe.asistentes.services;

import com.espe.asistentes.model.entities.Asistente;
import com.espe.asistentes.repositories.AsistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenteServiceImpl implements AsistenteService {

    @Autowired
    private AsistenteRepository repository;

    @Override
    @Transactional(readOnly = true) // Lectura optimizada para operaciones de solo lectura
    public List<Asistente> findAll() {
        return (List<Asistente>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true) // Lectura optimizada para operaciones de solo lectura
    public Optional<Asistente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional // Transacción de escritura
    public Asistente save(Asistente asistente) {
        return repository.save(asistente);
    }

    @Override
    @Transactional // Transacción de escritura
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
