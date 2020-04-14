package by.telebook.telebookapplication.user;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<User> login(@RequestParam String login,
                                      @RequestParam String password) {
        Optional<User> userByLogin = userRepository.findUserByLogin(login);
        if (userByLogin.isPresent()) {
            if (userByLogin.get().getPassword().equals(password)) {
                return new ResponseEntity<>(userByLogin.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<String> login(@Valid @RequestBody User user) {
        if (userRepository.existsUserByLogin(user.getLogin())) {
            return new ResponseEntity<>("User with Login " + user.getLogin() + " already exists. Please create a new Login", HttpStatus.IM_USED);
        }
        userRepository.save(user);
        return new ResponseEntity<>("User with Login " + user.getLogin() + " successfully registered.", HttpStatus.OK);
    }

    @GetMapping(path = "/getUserBy/id/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> userById = userRepository.findUserById(id);
        if (userById.isPresent()) {
            return new ResponseEntity<>(userById.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getUserBy/login/{login}")
    public ResponseEntity<User> findUserByLogin(@PathVariable String login) {
        Optional<User> userByLogin = userRepository.findUserByLogin(login);
        if (userByLogin.isPresent()) {
            return new ResponseEntity<>(userByLogin.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getUserBy/firstName/{firstName}")
    public ResponseEntity<List<User>> findUserByFirstName(@PathVariable String firstName) {
        Optional<List<User>> userByFirstName = userRepository.findUsersByFirstName(firstName);
        if (userByFirstName.isPresent()) {
            return new ResponseEntity<>(userByFirstName.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/getUserBy/lastName/{lastName}")
    public ResponseEntity<List<User>> findUserByLastName(@PathVariable String lastName) {
        Optional<List<User>> userByLastName = userRepository.findUsersByLastName(lastName);
        if (userByLastName.isPresent()) {
            return new ResponseEntity<>(userByLastName.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/update/firstName/{login}")
    public ResponseEntity<String> updateUserFirstNameByLogin(@RequestParam String firstName, @PathVariable String login) {
        Optional<User> userByLogin = userRepository.findUserByLogin(login);
        if (userByLogin.isPresent()) {
            userByLogin.get().setFirstName(firstName);
            userRepository.save(userByLogin.get());
            return new ResponseEntity<>(userByLogin.get().getFirstName() + " Updated", HttpStatus.OK);
        }
        return new ResponseEntity<>("TeleBook with login-" + login + " not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/update/lastName/{login}")
    public ResponseEntity<String> updateUserLastNameByLogin(@RequestParam String lastName, @PathVariable String login) {
        Optional<User> userByLogin = userRepository.findUserByLogin(login);
        if (userByLogin.isPresent()) {
            userByLogin.get().setLastName(lastName);
            userRepository.save(userByLogin.get());
            return new ResponseEntity<>(userByLogin.get().getLastName() + " updated.", HttpStatus.OK);
        }
        return new ResponseEntity<>("User with login-" + login + " not found.", HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/update/password/{login}")
    public ResponseEntity<String> updateUserPasswordByLogin(@RequestParam String password, @PathVariable String login) {
        Optional<User> userByLogin = userRepository.findUserByLogin(login);
        if (userByLogin.isPresent()) {
            userByLogin.get().setPassword(password);
            userRepository.save(userByLogin.get());
            return new ResponseEntity<>(userByLogin.get().getLogin() + "'s password updated.", HttpStatus.OK);
        }
        return new ResponseEntity<>("User with login-" + login + " not found.", HttpStatus.NOT_FOUND);
    }
}
