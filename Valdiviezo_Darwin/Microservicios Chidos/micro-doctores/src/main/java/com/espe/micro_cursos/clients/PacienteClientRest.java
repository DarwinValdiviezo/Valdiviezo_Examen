package com.espe.micro_cursos.clients;

import com.espe.micro_cursos.model.Paciente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "micro-pacientes", url = "http://localhost:8001/api/pacientes")
public interface PacienteClientRest {

    @GetMapping("/{id}")
    Paciente obtenerPacientePorId(@PathVariable("id") Long id);
}
