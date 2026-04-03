package com.example.project_manager.service;

import com.example.project_manager.entity.Users;
import com.example.project_manager.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UserRepository users;

    public UsersServiceImpl(UserRepository users) {
        this.users = users;
    }

    @Override
    public List<Users> getUsers() {
        return users.findAll().stream().toList();
    }
}
