package com.espe.micro_cursos.services;

import com.espe.micro_cursos.model.entity.Doctor;
import com.espe.micro_cursos.model.entity.PacienteDoctor;
import com.espe.micro_cursos.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAll() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> findById(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }

    @Override
    public void deleteById(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public void asignarPaciente(Long doctorId, Long pacienteId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        PacienteDoctor pacienteDoctor = new PacienteDoctor();
        pacienteDoctor.setDoctorId(doctorId);
        pacienteDoctor.setPacienteId(pacienteId);

        // Agregamos el paciente al doctor
        doctor.getPacienteDoctores().add(pacienteDoctor);
        doctorRepository.save(doctor);
    }

    @Override
    public void desmatricularPaciente(Long doctorId, Long pacienteId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));

        // Eliminar el registro de paciente_doctor
        doctor.getPacienteDoctores().removeIf(pd -> pd.getPacienteId().equals(pacienteId));
        doctorRepository.save(doctor);
    }



    @Override
    public List<Long> obtenerPacientesDeDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado"));
        // Retornamos solo los IDs de pacientes
        return doctor.getPacienteDoctores().stream()
                .map(PacienteDoctor::getPacienteId)
                .collect(Collectors.toList());
    }

}
