package com.example.project_manager.seeder;

import com.example.project_manager.entity.Users;
import com.example.project_manager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
//@Profile("dev")
public class DevUserSeeder implements CommandLineRunner {

    private final UserRepository users;

    public DevUserSeeder(UserRepository users) {
        this.users = users;
    }

    @Override
    public void run(String... args) {
        if (users.count() > 0) return;

        users.saveAll(List.of(
                new Users("beta", "beta@gmail.com"),
                new Users("alpha", "alpha@gmail.com"),
                new Users("Prototype", "prototype@gmail.com")
        ));
    }
}
