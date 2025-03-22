package com.masprog.appointment_api.api.controller;


import com.masprog.appointment_api.api.mapper.AppointmentMapper;
import com.masprog.appointment_api.api.request.AppointmentRequest;
import com.masprog.appointment_api.api.response.AppointmentResponse;
import com.masprog.appointment_api.api.response.PatientResponse;
import com.masprog.appointment_api.domain.entity.Appointment;
import com.masprog.appointment_api.domain.service.AppointmentService;
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
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@Tag(name = "Agendamento", description = "Endpoints para gerenciar agendamento" )
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final AppointmentMapper appointmentMapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper appointmentMapper) {
        this.appointmentService = appointmentService;
        this.appointmentMapper = appointmentMapper;
    }

    @Operation(summary = "Listar todos agendamentos", description = "Listar todos agendamentos",

            responses = {
                    @ApiResponse(responseCode = "201", description = "agendamentos listados com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class)))
            })
    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointment() {
        List<Appointment> appointments = appointmentService.getAllAppointment();
        List<AppointmentResponse> appointmentResponses = appointmentMapper.toAppointmentResponseList(appointments);

        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponses);
    }

    @Operation(summary = "Listar agendamento pelo id", description = "Listar agendamento pelo id",

            responses = {
                    @ApiResponse(responseCode = "201", description = "agendamento listado pelo id com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class)))
            })

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> optAppointment = appointmentService.findById(id);

        if(optAppointment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AppointmentResponse appointmentResponse = appointmentMapper.toAppointmentResponse(optAppointment.get());

        return ResponseEntity.status(HttpStatus.OK).body(appointmentResponse);
    }

    @Operation(summary = "Registar agendamento", description = "Registar agendamento",

            responses = {
                    @ApiResponse(responseCode = "201", description = "Agenda registada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class)))
            })
    @PostMapping
    public ResponseEntity<AppointmentResponse> save(@Valid @RequestBody AppointmentRequest request) {
        Appointment appointment = appointmentMapper.toAppointment(request);
        Appointment appointmentSave = appointmentService.save(appointment);
        AppointmentResponse appointmentResponse = appointmentMapper.toAppointmentResponse(appointmentSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponse);
    }

}
