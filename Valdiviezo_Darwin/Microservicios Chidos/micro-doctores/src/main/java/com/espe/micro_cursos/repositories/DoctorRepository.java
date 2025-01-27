package com.espe.micro_cursos.repositories;

import com.espe.micro_cursos.model.entity.Doctor;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
}
