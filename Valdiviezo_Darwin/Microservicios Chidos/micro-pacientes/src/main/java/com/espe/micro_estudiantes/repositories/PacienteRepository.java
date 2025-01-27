package com.espe.micro_estudiantes.repositories;

import com.espe.micro_estudiantes.model.entity.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteRepository extends CrudRepository<Paciente, Long> {
}
