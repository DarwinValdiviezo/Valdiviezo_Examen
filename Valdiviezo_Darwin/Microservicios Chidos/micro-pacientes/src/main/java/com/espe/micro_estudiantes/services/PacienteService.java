package com.espe.micro_estudiantes.services;

import com.espe.micro_estudiantes.model.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    List<Paciente> findAll();
    Optional<Paciente> findById(Long id);
    Paciente save(Paciente paciente);
    void deleteById(Long id);
}
