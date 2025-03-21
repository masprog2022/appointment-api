package com.masprog.appointment_api.api.request;

import jakarta.validation.constraints.NotBlank;

public class PatientRequest {
    @NotBlank(message = "Nome do paciente é obrigatório")
    private String name;


    @NotBlank(message = "Sobrenome do paciente é obrigatório")
    private String surname;
    private String email;
    @NotBlank(message = "CPF do paciente é obrigatório")
    private String cpf;


    public PatientRequest() {
    }

    public PatientRequest(String name, String surname, String email, String cpf) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cpf = cpf;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
