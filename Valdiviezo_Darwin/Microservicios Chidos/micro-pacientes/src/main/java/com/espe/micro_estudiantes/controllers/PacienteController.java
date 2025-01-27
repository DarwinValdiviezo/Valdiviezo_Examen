package com.espe.micro_estudiantes.controllers;

import com.espe.micro_estudiantes.model.entity.Paciente;
import com.espe.micro_estudiantes.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Paciente paciente, BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }

        Paciente nuevoPaciente = pacienteService.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPaciente);
    }

    @GetMapping
    public List<Paciente> listar() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Paciente> pacienteOptional = pacienteService.findById(id);
        if (pacienteOptional.isPresent()) {
            return ResponseEntity.ok().body(pacienteOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @Valid @RequestBody Paciente paciente, BindingResult result) {
        Map<String, String> errores = new HashMap<>();

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(err -> {
                errores.put(err.getField(), err.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errores);
        }

        Optional<Paciente> pacienteOptional = pacienteService.findById(id);
        if (pacienteOptional.isPresent()) {
            Paciente pacienteDB = pacienteOptional.get();
            pacienteDB.setNombre(paciente.getNombre());
            pacienteDB.setApellido(paciente.getApellido());
            pacienteDB.setEmail(paciente.getEmail());
            pacienteDB.setTelefono(paciente.getTelefono());
            pacienteDB.setFechaNacimiento(paciente.getFechaNacimiento());
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.save(pacienteDB));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Paciente> pacienteOptional = pacienteService.findById(id);
        if (pacienteOptional.isPresent()) {
            pacienteService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado.");
    }
}
