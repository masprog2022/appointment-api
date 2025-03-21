package com.masprog.appointment_api.api.controller;

import com.masprog.appointment_api.api.mapper.PatientMapper;
import com.masprog.appointment_api.api.request.PatientRequest;
import com.masprog.appointment_api.api.response.PatientResponse;
import com.masprog.appointment_api.domain.entity.Patient;
import com.masprog.appointment_api.domain.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    private final PatientMapper mapper;

    public PatientController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<PatientResponse> save(@Valid @RequestBody PatientRequest patientRequest){

        Patient patient = mapper.toPatient(patientRequest);

        Patient patientSave = patientService.save(patient);

        PatientResponse patientResponse = mapper.toPatientResponse(patientSave);

       return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }

    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAll(){

        List<PatientResponse> patientResponses = patientService.getAll()
                .stream()
                .map(mapper::toPatientResponse)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(patientResponses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getById(@PathVariable Long id){
        return patientService.getById(id)
                .map(mapper::toPatientResponse)
                .map(toPatientResponse -> ResponseEntity.status(HttpStatus.OK).body(toPatientResponse))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<PatientResponse> update(@PathVariable long id,  @RequestBody PatientRequest patientRequest){
        Patient patient = mapper.toPatient(patientRequest);
        Patient patientSave = patientService.update(id, patient);
        PatientResponse patientResponse = mapper.toPatientResponse(patientSave);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

   @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
