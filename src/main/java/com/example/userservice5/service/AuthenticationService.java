package com.example.userservice5.service;

import com.example.userservice5.dto.AuthDto;
import com.example.userservice5.exception.ApiException;
import com.example.userservice5.utils.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class AuthenticationService {
    @Autowired
    AuthenticationManager authenticationManager;
    public String login(AuthDto authDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authDto.getEmail(),authDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Instant now = Instant.now();
            byte[] secretKeys = Base64.getEncoder().encode(SecurityConstants.getTokenSecret().getBytes());
            SecretKey secretKey = new SecretKeySpec(secretKeys, SignatureAlgorithm.HS512.getJcaName());


            String token = Jwts.builder()
                    .setSubject(authDto.getEmail())
                    .setExpiration(Date.from(now.plusMillis(SecurityConstants.EXPIRATION_TIME)))
                    .setIssuedAt(Date.from(now))
                    .signWith(SignatureAlgorithm.HS512, secretKey)
                    .compact();

            return token;

        }catch (DisabledException exception){
            throw new ApiException(HttpStatus.BAD_REQUEST,"User account is not verified");
        }
        catch (AuthenticationException exception){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Credentials are not valid");
        }
        catch (Exception exception){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
    }
}
