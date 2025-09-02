package com.example.userservice5.controller;

import com.example.userservice5.dto.PitchDto;
import com.example.userservice5.dto.UserDto;
import com.example.userservice5.model.request.CreatePitchRequest;
import com.example.userservice5.model.request.UserSignupRequest;
import com.example.userservice5.model.response.CreatePitchResponse;
import com.example.userservice5.service.PitchService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pitch")
@RequiredArgsConstructor
public class PitchController {
    private final PitchService pitchService;

    @PostMapping(path = "", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CreatePitchResponse> createPitch(@RequestBody @Valid CreatePitchRequest requestBody){
        ModelMapper mapper = new ModelMapper();
        PitchDto pitchDto = mapper.map(requestBody, PitchDto.class);
        PitchDto returnValue = this.pitchService.createPitch(pitchDto);
        return ResponseEntity.status(200).body(mapper.map(returnValue, CreatePitchResponse.class));
    }

}
