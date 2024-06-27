package upc.edu.oneup.controller;

import upc.edu.oneup.exception.ValidationException;
import upc.edu.oneup.model.Patient;
import upc.edu.oneup.model.User;
import upc.edu.oneup.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "Users", description = "the user API")
@RestController
@RequestMapping("/api/oneup/v1")
//@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/authenticate")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userService.getUserByUsernameAndPassword(username, password);

        if (user != null) {
            Map<String, String> response = new HashMap<>();
            response.put("id", String.valueOf(user.getId()));
            response.put("username", user.getUsername());
            response.put("password", user.getPassword());

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
/*
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }*/

    @GetMapping("/users/{username}/patients")
    public List<Patient> getPatientsByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return userService.getPatientsByUserId(user.getId());
    }


    @PostMapping("/users")
    @Transactional
    public User createUser(@RequestBody User user) {
        validateUser(user);
        return userService.createUser(user);
    }

    @PutMapping("/users/username/{username}")
    @Transactional
    public User updateUserByUsername(@PathVariable String username, @RequestBody User updatedUser) {
        validateUser(updatedUser);
        return userService.updateUserByUsername(username, updatedUser);
    }

    @DeleteMapping("users/{id}")
    @Transactional
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    public void validateUser(User user) {
        if (user.getUsername() != null && (user.getUsername().trim().isEmpty() || user.getUsername().length() > 30)) {
            throw new ValidationException("Username must not be empty and must not be more than 30 characters");
        }

        if (user.getPassword() != null && (user.getPassword().trim().isEmpty() || user.getPassword().length() > 30)) {
            throw new ValidationException("Password must not be empty and must not be more than 30 characters");
        }


        if (user.getEmail() != null && (user.getEmail().trim().isEmpty() || user.getEmail().length() > 50)) {
            throw new ValidationException("Email must not be empty and must not be more than 50 characters");
        }



    }
}
