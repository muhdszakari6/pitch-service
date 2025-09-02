package com.example.userservice5.security;

import com.example.userservice5.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.userservice5.entity.UserEntity;
import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;

public class UserPrincipal implements UserDetails {

    private UserEntity userEntity;
    public UserPrincipal(UserEntity userEntity){
        this.userEntity = userEntity;
    }
    @Serial
    private static final long serialVersionUID = -7530187709860249942L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();

        //Get user roles
        Collection<RoleEntity> roles = userEntity.getRoles();
        if(roles.isEmpty()){
            return authorities;
        }

        roles.forEach((role)->{
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEntity.getEmailVerificationStatus();
    }
}
