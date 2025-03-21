package com.masprog.appointment_api.api.response;



import java.time.LocalDateTime;

public class AppointmentResponse {

    private Long id;
    private String description;
    private LocalDateTime dateTime;
    private PatientResponse patient;


    public AppointmentResponse() {
    }

    public AppointmentResponse(Long id, String description, LocalDateTime dateTime, PatientResponse patient) {
        this.id = id;
        this.description = description;
        this.dateTime = dateTime;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public PatientResponse getPatient() {
        return patient;
    }

    public void setPatient(PatientResponse patient) {
        this.patient = patient;
    }
}
