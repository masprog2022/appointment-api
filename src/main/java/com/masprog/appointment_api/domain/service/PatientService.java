package com.masprog.appointment_api.domain.service;

import com.masprog.appointment_api.domain.entity.Patient;
import com.masprog.appointment_api.domain.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }
    public List<Patient> getAll(){
        return patientRepository.findAll();
    }

    public Optional<Patient> getById(Long id){
       return patientRepository.findById(id);
    }

    public void delete(Long id){
       patientRepository.deleteById(id);
    }

}
