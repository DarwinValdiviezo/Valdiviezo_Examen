package com.espe.micro_estudiantes.services;

import com.espe.micro_estudiantes.model.entity.Paciente;
import com.espe.micro_estudiantes.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Override
    public List<Paciente> findAll() {
        return (List<Paciente>) repository.findAll();
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return repository.save(paciente);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
