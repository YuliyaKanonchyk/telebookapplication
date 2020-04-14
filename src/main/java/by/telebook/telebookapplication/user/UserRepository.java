package by.telebook.telebookapplication.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsUserByLogin(String login);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByLogin(String login);

    Optional<List<User>> findUsersByFirstName(String firstName);

    Optional<List<User>> findUsersByLastName(String lastName);
}
