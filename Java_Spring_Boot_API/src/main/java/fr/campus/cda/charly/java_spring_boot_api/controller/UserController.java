package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.dao.User;
import fr.campus.cda.charly.java_spring_boot_api.dao.UserDAO;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {

//    @Qualifier("userCollectionImpl")
    @Autowired
    private UserDAO userDAO;

    private UserDTO userToDTO(User user) {
        return new UserDTO(user.getName(), user.getId());
    }

    private List<UserDTO> dtoToList(List<User> users) {
        return users.stream()
                .map(this::userToDTO)
                .toList();
    }

    @PostMapping("/users")
    public UserDTO addUser(@RequestBody UserCreationParams params) {
        User addUser = userDAO.addUser(params);
        return userToDTO(addUser);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        return dtoToList(userDAO.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable int id) {
        return userToDTO(userDAO.getUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public UserDTO deleteUser(@PathVariable int id) {
        return userToDTO(userDAO.deleteUser(id));
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@RequestBody UserCreationParams params, @PathVariable int id) {
        User updatedUser = userDAO.updateUser(id, params);
        return userToDTO(updatedUser);
    }


}
