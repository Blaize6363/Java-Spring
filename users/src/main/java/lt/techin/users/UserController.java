package lt.techin.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/users")

public class UserController {

    private List<User> users = new ArrayList<>();

//    @PostMapping
//    public void addUser(@RequestBody User user) {
//        users.add(user);
//    }
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return users;
//    }

    private Long count = -1L;
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserCreationDTO userCreationDTO) {
        System.out.println(userCreationDTO);
        count++;
        User user = new User(count, userCreationDTO.getUsername(), userCreationDTO.getFirstName(), userCreationDTO.getLastName(), userCreationDTO.getEmail());
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User result = users.stream().filter(u -> Objects.equals(u.getId(), id)).findAny().orElse(null);
        return ResponseEntity.status(HttpStatus.FOUND).body(result);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("Deleting user with id " + id);
        users.removeIf(u -> u.getId() == id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User result = new User();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId() == id) {
                result = user;
                users.set(i, user);
                return ResponseEntity.status(HttpStatus.FOUND).body(result);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }
}
