package lt.techin.user2.service;


import lt.techin.user2.database.UserDatabase;
import lt.techin.user2.dto.UserCreateDto;
import lt.techin.user2.dto.UserDto;
import lt.techin.user2.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDatabase userDatabase;

    public UserService(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public List<UserDto> getUsers() {
        return userDatabase.findAll().stream().map(this::toDto).toList();
    }

    public UserDto getUserById(long id) {
        User user = userDatabase.findById(id).orElse(null);
        return toDto(user);
    }

    public UserDto saveUser(UserCreateDto user) {
        User save = userDatabase.save(toEntity(user));
        return toDto(save);
    }

    public UserDto updateUser(long id, UserCreateDto user) {
        User entity = toEntity(user);
        entity.setId(id);
        User save = userDatabase.save(entity);
        return toDto(save);
    }

    public void removeUser(long id) {
        userDatabase.deleteById(id);
    }

    private UserDto toDto(User user) {
        if (user == null) {
            return null;
        } else {
            return new UserDto(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthYear());
        }
    }

    private User toEntity(UserCreateDto user) {
        if (user == null) {
            return null;
        } else {
            return new User(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getBirthYear());
        }
    }
}
