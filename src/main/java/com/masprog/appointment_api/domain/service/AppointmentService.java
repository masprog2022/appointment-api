package com.masprog.appointment_api.domain.service;


import com.masprog.appointment_api.domain.entity.Appointment;
import com.masprog.appointment_api.domain.entity.Patient;
import com.masprog.appointment_api.domain.repository.AppointmentRepository;
import com.masprog.appointment_api.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientService patientService;

    public AppointmentService(AppointmentRepository appointmentRepository, PatientService patientService) {
        this.appointmentRepository = appointmentRepository;
        this.patientService = patientService;
    }

    public List<Appointment> getAllAppointment(){
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> findById(Long id){
        return appointmentRepository.findById(id);
    }

    public Appointment save(Appointment appointment){
        Optional<Patient> optionalPatient = patientService.getById(appointment.getPatient().getId());

        if(optionalPatient.isEmpty()){
            throw new BusinessException("Paciente não encontrado");
        }

        Optional<Appointment> optDateTime = appointmentRepository.findByDateTime(appointment.getDateTime());
        if(optDateTime.isPresent()){
            throw new BusinessException("Já existe agendamento para este horário");
        }

        appointment.setPatient(optionalPatient.get());
        appointment.setDateTime(LocalDateTime.now());

        return appointmentRepository.save(appointment);

    }
}
