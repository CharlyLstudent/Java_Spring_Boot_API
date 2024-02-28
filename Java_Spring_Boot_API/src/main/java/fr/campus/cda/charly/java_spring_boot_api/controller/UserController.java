package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.entity.User;
import fr.campus.cda.charly.java_spring_boot_api.dao.UserDAO;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserDTO;
import fr.campus.cda.charly.java_spring_boot_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

//  @Qualifier("userCollectionImpl")
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRepository repository;

    private UserDTO userToDto(User user) {
        return new UserDTO(user.getName(), user.getId());
    }

    private User dtoToUser(UserDTO userDTO){
        return new User(userDTO.name(), userDTO.id());
    }

    private List<UserDTO> dtoToList(List<User> users) {
        return users.stream()
                .map(this::userToDto)
                .toList();
    }

    @PostMapping("/users")
    public UserDTO addUser(@RequestBody UserDTO params) {
        User addUser = repository.save(dtoToUser(params));
        return userToDto(addUser);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return dtoToList((List<User>) repository.findAll());
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return repository.findById(id)
                .map(this::userToDto)
                .orElse(null);
    }

    @DeleteMapping("/users/{id}")
    public UserDTO deleteUser(@PathVariable int id) {
        UserDTO user = repository.findById(id)
                .map(this::userToDto)
                .orElse(null);
        repository.deleteById(id);
        return user;

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO params, @PathVariable int id) {
       return repository.findById(id).map(user ->{
            user.setName(params.name());
            User updatedUser = repository.save(user);
            return new ResponseEntity<>(userToDto(updatedUser), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}