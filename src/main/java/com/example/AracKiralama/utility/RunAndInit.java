package com.example.AracKiralama.utility;

import com.example.AracKiralama.entity.Admin;
import com.example.AracKiralama.entity.Person;
import com.example.AracKiralama.entity.enums.Role;
import com.example.AracKiralama.repository.IAdminRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RunAndInit {
   private IAdminRepository repository;
    public RunAndInit(IAdminRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
        public void run(){
            new Thread(()->{
                saveAdmin();
            }).start();

    }

    public void saveAdmin(){
            if (repository.findAll().isEmpty()){
                Admin admin=Admin.builder()
                                .rol(Role.ROLE_ADMIN)
                                .name("admin")
                                .surname("admin")
                                .phone("11111111111")
                                .adres("1234567")
                                .email("string")
                                .password("string")
                        .b(1)
                        .build();
                repository.save(admin);
                System.err.println(
                        "Email: "+ admin.getEmail()+
                                "\nŞifre: "+admin.getPassword()
                );
            }


    }
}
