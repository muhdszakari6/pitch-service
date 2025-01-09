package com.example.userservice5.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

@Component
public class Utils {
    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "qwertypouiyutyrterweasdsfdgfhgjkhljmbnvb";

    public static String generateEmailVerificationToken(String publicUserId){
        String token = Jwts.builder()
                .setSubject(publicUserId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        return token;
    }
    public static String generatePasswordRestRequestToken(String publicUserId){
        String token = Jwts.builder()
                .setSubject(publicUserId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        return token;
    }
    public static String generateUserId(int length){
        return generateRandomString(length);
    }

    private static String generateRandomString(int length){
        StringBuilder returnValue = new StringBuilder(length);
        for(int i = 0;i<length; i++){
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    public static boolean hasTokenExpired(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(SecurityConstants.getTokenSecret())
                .parseClaimsJws(token).getBody();
        Date tokenExpirationDate = claims.getExpiration();
        Date todayDate = new Date();
        return tokenExpirationDate.before(todayDate);
    }
}
