package com.espe.micro_cursos.services;

import com.espe.micro_cursos.model.entity.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorService {
    List<Doctor> findAll();
    Doctor save(Doctor doctor);
    Optional<Doctor> findById(Long doctorId);
    void deleteById(Long doctorId);
    void asignarPaciente(Long doctorId, Long pacienteId);
    void desmatricularPaciente(Long doctorId, Long pacienteId);
    List<Long> obtenerPacientesDeDoctor(Long doctorId);
}
