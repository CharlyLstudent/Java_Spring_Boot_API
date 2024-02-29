package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.entity.UserEntity;
import fr.campus.cda.charly.java_spring_boot_api.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserSecurityController {

    @Autowired
    private UserSecurityRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/users-security")
    public UserEntity addUser(@RequestBody String name, String password) {
            UserEntity addUser = new UserEntity(name,password);
            addUser.setPassword(passwordEncoder.encode(addUser.getPassword()));
            return userRepository.save(addUser);

    }

    @GetMapping("/users-security")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users-security/{username}")
    public UserEntity getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }
}
