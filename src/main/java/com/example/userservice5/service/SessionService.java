package com.example.userservice5.service;

import com.example.userservice5.entity.BookingEntity;
import com.example.userservice5.entity.SessionEntity;
import com.example.userservice5.model.response.AvailableSessionResponse;
import com.example.userservice5.model.response.SessionResponse;
import com.example.userservice5.repository.BookingRepository;
import com.example.userservice5.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final BookingRepository bookingRepository;

    public List<AvailableSessionResponse> getAvailableSessions(Long pitchId, LocalDate date) {
        ModelMapper modelMapper = new ModelMapper();
        List<SessionEntity> sessions = sessionRepository.findByPitchIdAndActiveTrue(pitchId);
        List<BookingEntity> bookings = bookingRepository.findBookingEntitiesByPitchAndDate(pitchId, date);
        List<AvailableSessionResponse> result = sessions.stream().map(sessionEntity -> {
            AvailableSessionResponse availableSessionResponse = modelMapper.map(sessionEntity, AvailableSessionResponse.class);
            if (bookings.stream().anyMatch(bookingEntity -> bookingEntity.getSession().getId().equals(sessionEntity.getId()))) {
                availableSessionResponse.setStatus("BOOKED");
            } else {
                availableSessionResponse.setStatus("AVAILABLE");
            }
            return availableSessionResponse;
        }).toList();
        return result;
    }
}
