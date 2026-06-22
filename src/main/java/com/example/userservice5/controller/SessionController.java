package com.example.userservice5.controller;

import com.example.userservice5.model.response.AvailableSessionResponse;
import com.example.userservice5.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("session")
@RequiredArgsConstructor
public class SessionController {
    private final SessionService sessionService;

    @GetMapping()
    public ResponseEntity<List<AvailableSessionResponse>> getAvailableSessions(
            @RequestParam Long pitchId,
            @RequestParam LocalDate date
    ){
      List<AvailableSessionResponse> sessions = sessionService.getAvailableSessions(pitchId, date);
      return  ResponseEntity.status(200).body(sessions);
    }
}
