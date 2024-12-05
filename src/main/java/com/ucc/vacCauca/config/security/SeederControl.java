package com.ucc.vacCauca.config.security;

import com.ucc.vacCauca.domain.entity.User;
import com.ucc.vacCauca.enums.RegisterStatusEnum;
import com.ucc.vacCauca.enums.UserRolEnum;
import com.ucc.vacCauca.repository.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class SeederControl {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    public SeederControl(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                new AuthenticatedUser(1L, "a@a.com"),
                "key"
        ));
    }

    @EventListener
    @Transactional
    public void seed(ContextRefreshedEvent contextRefreshedEvent) {
        //this.userSeed();
    }

    protected void userSeed() {
        User user = new User();
        user.setEmail("vaccauca@gmail.com");
        user.setFullName("Vidrios y Aluminios del Cauca");
        user.setIdentification("1000968979-0");
        user.setUserRolEnum(UserRolEnum.ADMIN);
        System.out.println("***************************************************");
        String encode = bCryptPasswordEncoder.encode("pruebas");
        System.out.println(encode);
        user.setPassword(encode);
        user.setPhone("3147450934");
        user.setStatus(RegisterStatusEnum.ACTIVE);

        User newUserCreated = this.userRepository.save(user);
        System.out.println(newUserCreated);
    }
}

