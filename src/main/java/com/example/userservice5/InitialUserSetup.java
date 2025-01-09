package com.example.userservice5;
import com.example.userservice5.entity.RoleEntity;
import com.example.userservice5.entity.UserEntity;
import com.example.userservice5.repository.RoleRepository;
import com.example.userservice5.repository.UserRepository;
import com.example.userservice5.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class InitialUserSetup {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils utils;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event){
        System.out.println("From application ready event");
//        AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
//        AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");
//        AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");

        RoleEntity roleAdmin = createRole("ROLE_ADMIN");
        RoleEntity roleUser = createRole("ROLE_USER");

        if(roleAdmin==null){
            return;
        }

        UserEntity adminUser = new UserEntity();
        adminUser.setFirstName("Salim");
        adminUser.setLastName("Zakari");
        adminUser.setEmail("salimzakari600@gmail.com");
        adminUser.setUserId(utils.generateUserId(30));
        adminUser.setPassword(bCryptPasswordEncoder.encode("salim"));
        adminUser.setRoles(Arrays.asList(roleAdmin, roleUser));
        UserEntity user = userRepository.findByEmail("salimzakari600@gmail.com");
        if(user==null){
            userRepository.save(adminUser);
        }
    }

    @Transactional
    RoleEntity createRole(
            String name
    ){
        RoleEntity role = roleRepository.findByName(name);

        if(role==null){
            role = new RoleEntity();
            role.setName(name);
            roleRepository.save(role);
        }
        return role;
    }
}
