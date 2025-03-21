package com.masprog.appointment_api.api.mapper;

import com.masprog.appointment_api.api.request.PatientRequest;
import com.masprog.appointment_api.api.response.PatientResponse;
import com.masprog.appointment_api.domain.entity.Patient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {

    private final ModelMapper mapper;

    public PatientMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public Patient toPatient(PatientRequest patientRequest){
        return mapper.map(patientRequest, Patient.class);

    }

    public PatientResponse toPatientResponse(Patient patient){
        return mapper.map(patient, PatientResponse.class);

    }

    public List<PatientResponse> toPacienteResponseList(List<Patient> patients) {
        return patients.stream()
                .map(this::toPatientResponse)
                .collect(Collectors.toList());
    }
}
