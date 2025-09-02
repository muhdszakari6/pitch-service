package com.example.userservice5.service;

import com.example.userservice5.dto.PitchDto;
import com.example.userservice5.dto.SessionConfigurationDto;
import com.example.userservice5.dto.SessionDto;
import com.example.userservice5.dto.UserDto;
import com.example.userservice5.entity.PitchEntity;
import com.example.userservice5.entity.UserEntity;
import com.example.userservice5.enums.CreationMode;
import com.example.userservice5.exception.ApiException;
import com.example.userservice5.repository.PitchRepository;
import com.example.userservice5.repository.SessionConfigurationRepository;
import com.example.userservice5.repository.SessionRepository;
import com.example.userservice5.repository.UserRepository;
import com.example.userservice5.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PitchService {
    private final PitchRepository pitchRepository;
    private final SessionRepository sessionRepository;
    private final SessionConfigurationRepository sessionConfigurationRepository;
    private final UserRepository userRepository;

    @Transactional()
    public PitchDto createPitch(PitchDto requestBody) {
        PitchEntity existingPitch = pitchRepository.findByName(requestBody.getName());
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity existingUser = userPrincipal.getUserEntity();
        UserEntity managedUser = userRepository.findByEmail(existingUser.getEmail());

        if(managedUser == null){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }

        if(existingPitch != null){
            throw new ApiException(HttpStatus.BAD_REQUEST, "A pitch with this name exists already");
        }

        SessionConfigurationDto sessionConfigurationDto = this.createSessionConfiguration(requestBody);
        requestBody.setSessionConfiguration(sessionConfigurationDto);
        ModelMapper mapper = new ModelMapper();

        PitchEntity pitch = mapper.map(requestBody, PitchEntity.class);
        pitch.setUserDetail(managedUser);
        
        PitchEntity storedPitch = pitchRepository.save(pitch);

        return mapper.map(storedPitch, PitchDto.class);
    }

    @Transactional()
    public PitchDto updatePitch(PitchDto requestBody){
        Optional<PitchEntity> existingPitch = pitchRepository.findById(requestBody.getId());
        if(!existingPitch.isPresent()){
//            throw new ApiException()
        }
        return null;
    }

    private SessionConfigurationDto createSessionConfiguration(PitchDto pitch) {
        SessionConfigurationDto sessionConfigurationDto = new SessionConfigurationDto();
        sessionConfigurationDto.setCreationMode(CreationMode.DAILY);
        sessionConfigurationDto.setNumberOfSessions(10);
        sessionConfigurationDto.setPitch(pitch);
        return sessionConfigurationDto;
    }

}
