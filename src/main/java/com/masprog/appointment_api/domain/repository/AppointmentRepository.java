package com.masprog.appointment_api.domain.repository;

import com.masprog.appointment_api.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByDateTime(LocalDateTime dateTime);
}
