package com.masprog.appointment_api.api.controller;


import com.masprog.appointment_api.api.mapper.AppointmentMapper;
import com.masprog.appointment_api.api.request.AppointmentRequest;
import com.masprog.appointment_api.api.response.AppointmentResponse;
import com.masprog.appointment_api.domain.entity.Appointment;
import com.masprog.appointment_api.domain.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointment() {
        List<Appointment> appointments = appointmentService.getAllAppointment();
        List<AppointmentResponse> appointmentResponses = appointmentMapper.toAppointmentResponseList(appointments);

        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> optAppointment = appointmentService.findById(id);

        if(optAppointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AppointmentResponse appointmentResponse = appointmentMapper.toAppointmentResponse(optAppointment.get());

        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponse);
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> save(@Valid @RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentMapper.toAppointment(request);
        Appointment appointmentSave = appointmentService.save(appointment);
        AppointmentResponse appointmentResponse = appointmentMapper.toAppointmentResponse(appointmentSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponse);
    }

}
