package lt.techin.user2.database;

import lt.techin.user2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDatabase extends JpaRepository<User, Long> {
//        List<User> findAllWithLastName(String lastName);
//        List<User> getAllByBirthYear(int year);
}