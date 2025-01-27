package com.espe.micro_cursos.controllers;

import com.espe.micro_cursos.model.entity.Doctor;
import com.espe.micro_cursos.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doctores")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // CRUD: Crear un doctor
    @PostMapping
    public ResponseEntity<?> crearDoctor(@RequestBody Doctor doctor) {
        Doctor nuevoDoctor = doctorService.save(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDoctor);
    }

    // CRUD: Listar todos los doctores
    @GetMapping
    public ResponseEntity<List<Doctor>> listarDoctores() {
        List<Doctor> doctores = doctorService.findAll();
        return ResponseEntity.ok(doctores);
    }

    // CRUD: Obtener un doctor por ID
    @GetMapping("/{doctorId}")
    public ResponseEntity<?> obtenerDoctor(@PathVariable Long doctorId) {
        Optional<Doctor> doctor = doctorService.findById(doctorId);
        if (doctor.isPresent()) {
            return ResponseEntity.ok(doctor.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado.");
    }

    // CRUD: Actualizar un doctor
    @PutMapping("/{doctorId}")
    public ResponseEntity<?> actualizarDoctor(@PathVariable Long doctorId, @RequestBody Doctor doctor) {
        Optional<Doctor> doctorOptional = doctorService.findById(doctorId);
        if (doctorOptional.isPresent()) {
            Doctor doctorActualizado = doctorOptional.get();
            doctorActualizado.setNombre(doctor.getNombre());
            doctorActualizado.setApellido(doctor.getApellido());
            doctorActualizado.setEmail(doctor.getEmail());
            doctorActualizado.setLicencia(doctor.getLicencia());
            doctorActualizado.setEspecialidad(doctor.getEspecialidad());
            doctorActualizado.setTelefono(doctor.getTelefono());
            doctorService.save(doctorActualizado);
            return ResponseEntity.ok(doctorActualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado.");
    }

    // CRUD: Eliminar un doctor
    @DeleteMapping("/{doctorId}")
    public ResponseEntity<?> eliminarDoctor(@PathVariable Long doctorId) {
        Optional<Doctor> doctor = doctorService.findById(doctorId);
        if (doctor.isPresent()) {
            doctorService.deleteById(doctorId);
            return ResponseEntity.ok("Doctor eliminado correctamente.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado.");
    }

    // Asignar un paciente a un doctor
    @PostMapping("/{doctorId}/asignar-paciente/{pacienteId}")
    public ResponseEntity<?> asignarPaciente(@PathVariable Long doctorId, @PathVariable Long pacienteId) {
        try {
            doctorService.asignarPaciente(doctorId, pacienteId);
            return ResponseEntity.ok("Paciente asignado al doctor.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Desasignar un paciente de un doctor
    @DeleteMapping("/{doctorId}/desmatricular-paciente/{pacienteId}")
    public ResponseEntity<?> desmatricularPaciente(@PathVariable Long doctorId, @PathVariable Long pacienteId) {
        try {
            doctorService.desmatricularPaciente(doctorId, pacienteId);
            return ResponseEntity.ok("Paciente desasignado del doctor.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    // Listar los pacientes asignados a un doctor
    @GetMapping("/{doctorId}/pacientes")
    public ResponseEntity<?> listarPacientes(@PathVariable Long doctorId) {
        Optional<Doctor> doctorOptional = doctorService.findById(doctorId);

        if (doctorOptional.isPresent()) {
            List<Long> pacientes = doctorService.obtenerPacientesDeDoctor(doctorId);
            return ResponseEntity.ok(pacientes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado.");
        }
    }

}
