package com.masprog.appointment_api.api.mapper;

import com.masprog.appointment_api.api.request.PatientRequest;
import com.masprog.appointment_api.api.response.PatientResponse;
import com.masprog.appointment_api.domain.entity.Patient;

public class PatientMapper {

    public static Patient toPatient(PatientRequest patientRequest){
        Patient patient = new Patient();
        patient.setName(patientRequest.getName());
        patient.setSurname(patientRequest.getSurname());
        patient.setEmail(patientRequest.getEmail());
        patient.setCpf(patientRequest.getCpf());

        return patient;

    }

    public static PatientResponse toPatientResponse(Patient patient){
        PatientResponse response = new PatientResponse();
        response.setId(patient.getId());
        response.setName(patient.getName());
        response.setSurname(patient.getSurname());
        response.setEmail(patient.getEmail());
        response.setCpf(patient.getCpf());

        return response;

    }
}
