package com.example.userservice5.security;

import com.example.userservice5.entity.RoleEntity;
import com.example.userservice5.entity.UserEntity;
import com.example.userservice5.exception.ApiException;
import com.example.userservice5.repository.UserRepository;
import com.example.userservice5.utils.SecurityConstants;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    UserRepository userRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)){
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        String token = header.replace(SecurityConstants.TOKEN_PREFIX, "");
        byte[] secretKeyBytes = Base64.encode(SecurityConstants.getTokenSecret().getBytes());
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS512.getJcaName());

        JwtParser jwtParser = Jwts.parser().setSigningKey(secretKey);
        Jwt<Header, Claims> jwt =  jwtParser.parse(token);
        String subject = jwt.getBody().getSubject();

        if(subject == null){
            return null;
        }

        UserEntity user = userRepository.findByEmail(subject);
        if(user == null) return null;

        Collection<RoleEntity> roles = user.getRoles();
        if(roles.isEmpty()){
            throw new ApiException(HttpStatus.BAD_REQUEST, "User does not have roles");
        }

        UserPrincipal userPrincipal = new UserPrincipal(user);
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }
}

