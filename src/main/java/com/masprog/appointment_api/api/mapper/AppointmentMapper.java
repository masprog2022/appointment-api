package com.masprog.appointment_api.api.mapper;


import com.masprog.appointment_api.api.request.AppointmentRequest;
import com.masprog.appointment_api.api.response.AppointmentResponse;
import com.masprog.appointment_api.domain.entity.Appointment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppointmentMapper {

    private final ModelMapper mapper;

    public AppointmentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Appointment toAppointment(AppointmentRequest request){
        return mapper.map(request, Appointment.class);
    }

    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        return mapper.map(appointment, AppointmentResponse.class);
    }

    public List<AppointmentResponse> toAppointmentResponseList(List<Appointment> appointments) {
        return appointments.stream()
                .map(this::toAppointmentResponse)
                .collect(Collectors.toList());
    }
}
