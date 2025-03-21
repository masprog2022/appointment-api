package com.masprog.appointment_api.domain.service;

import com.masprog.appointment_api.domain.entity.Patient;
import com.masprog.appointment_api.domain.repository.PatientRepository;
import com.masprog.appointment_api.exception.BusinessException;
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

        boolean existCpf = false;

      Optional<Patient> optPatient =  patientRepository.findByCpf(patient.getCpf());
      if(optPatient.isPresent()){
          if(!optPatient.get().getId().equals(patient.getId())){
             existCpf = true;
          }
      }

      if(existCpf){
          throw  new BusinessException("Cpf já cadastrado.");
      }

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

    public Patient update(Long id, Patient patient) {
        Optional<Patient> optPatient = this.getById(id);

        if (optPatient.isEmpty()) {
            throw new BusinessException("Paciente não cadastrado!");
        }

        patient.setId(id);

        return save(patient);
    }

}
