package com.example.userservice5.repository;

import com.example.userservice5.entity.BookingEntity;
import com.example.userservice5.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
    Optional<BookingEntity> findBySessionAndBookingDateAndDeletedAtIsNull(SessionEntity session, LocalDate bookingDate);
}
