package com.example.userservice5.controller;

import com.example.userservice5.dto.AuthDto;
import com.example.userservice5.model.request.AuthRequest;
import com.example.userservice5.model.response.AuthResponse;
import com.example.userservice5.service.AuthenticationService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("auth")
public class AuthController {
    @Autowired
    AuthenticationService authenticationService;
    @PostMapping
    public AuthResponse login(@RequestBody @Valid AuthRequest authRequest){
     ModelMapper mapper = new ModelMapper();
     AuthDto authDto = mapper.map(authRequest, AuthDto.class);
     String token = authenticationService.login(authDto);
     AuthResponse returnValue = new AuthResponse(token);

     return returnValue;
    }
}
