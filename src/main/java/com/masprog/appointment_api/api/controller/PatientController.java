package com.masprog.appointment_api.api.controller;

import com.masprog.appointment_api.api.mapper.PatientMapper;
import com.masprog.appointment_api.api.request.PatientRequest;
import com.masprog.appointment_api.api.response.PatientResponse;
import com.masprog.appointment_api.domain.entity.Patient;
import com.masprog.appointment_api.domain.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
@Tag(name = "Pacientes", description = "Endpoints para gerenciar Pacientes" )
public class PatientController {

    private final PatientService patientService;

    private final PatientMapper mapper;

    public PatientController(PatientService patientService, PatientMapper mapper) {
        this.patientService = patientService;
        this.mapper = mapper;
    }

    @Operation(summary = "Registar paciente", description = "Registar paciente",

            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto adicionado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponse.class)))
            })
    @PostMapping
    public ResponseEntity<PatientResponse> save(@Valid @RequestBody PatientRequest patientRequest){

        Patient patient = mapper.toPatient(patientRequest);

        Patient patientSave = patientService.save(patient);

        PatientResponse patientResponse = mapper.toPatientResponse(patientSave);

       return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }

    @Operation(summary = "Listar todos pacientes", description = "Listar todos pacientes",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Listar com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponse.class)))
            })
    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAll(){

        List<PatientResponse> patientResponses = patientService.getAll()
                .stream()
                .map(mapper::toPatientResponse)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(patientResponses);
    }

    @Operation(summary = "Listar paciente pelo id", description = "Listar paciente pelo id",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Listar com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponse.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getById(@PathVariable Long id){
        return patientService.getById(id)
                .map(mapper::toPatientResponse)
                .map(toPatientResponse -> ResponseEntity.status(HttpStatus.OK).body(toPatientResponse))
                .orElse(ResponseEntity.notFound().build());
    }
    @Operation(summary = "Actualizar paciente", description = "Actualizar paciente",

            responses = {
                    @ApiResponse(responseCode = "200", description = "actualizado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponse.class)))
            })
    @PutMapping
    public ResponseEntity<PatientResponse> update(@PathVariable long id,  @RequestBody PatientRequest patientRequest){
        Patient patient = mapper.toPatient(patientRequest);
        Patient patientSave = patientService.update(id, patient);
        PatientResponse patientResponse = mapper.toPatientResponse(patientSave);
        return ResponseEntity.status(HttpStatus.OK).body(patientResponse);
    }

    @Operation(summary = "Deletar paciente", description = "Deletar paciente",

            responses = {
                    @ApiResponse(responseCode = "200", description = "deletado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class)))
            })
   @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id){
        patientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
