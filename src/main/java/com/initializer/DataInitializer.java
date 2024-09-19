package com.initializer;




import com.dao.RoleRepository;
import com.dao.UserRepository;
import com.model.Role;
import com.model.UserEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	System.out.println("initializer executed *********");
        // Create roles
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        
      //  roleRepository.saveAll(Arrays.asList(adminRole, userRole));

        // Create users
        UserEntity adminUser = new UserEntity();
        UserEntity userUser = new UserEntity();
         adminRole.setUser(adminUser);
         userRole.setUser(userUser);
         adminUser.setUsername("admin");
         adminUser.setPassword(passwordEncoder.encode("password"));
         adminUser.setRoles(Arrays.asList(adminRole,userRole));
         userUser.setUsername("user");
         userUser.setPassword(passwordEncoder.encode("password"));
         userUser.setRoles(Arrays.asList(userRole));
        userRepository.saveAll(Arrays.asList(adminUser, userUser));
    }
}