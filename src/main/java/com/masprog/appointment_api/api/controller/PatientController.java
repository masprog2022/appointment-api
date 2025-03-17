package com.masprog.appointment_api.api.controller;

import com.masprog.appointment_api.domain.entity.Patient;
import com.masprog.appointment_api.domain.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<Patient> save(@RequestBody Patient patient){
       Patient save = patientService.save(patient);
       return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAll(){
       List<Patient> patients = patientService.getAll();
       return ResponseEntity.status(HttpStatus.OK).body(patients);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id){
        Optional<Patient> optPatient = patientService.getById(id);

        if(optPatient.isEmpty()){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optPatient.get());
    }

    @PutMapping
    public ResponseEntity<Patient> update(@RequestBody Patient patient){
        Patient updatePatient = patientService.save(patient);
        return ResponseEntity.status(HttpStatus.OK).body(updatePatient);
    }

   @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
