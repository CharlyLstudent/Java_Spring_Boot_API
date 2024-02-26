package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.dao.User;
import fr.campus.cda.charly.java_spring_boot_api.dao.UserDAO;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserDTO;
import fr.campus.cda.charly.java_spring_boot_api.service.UserCollectionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserDAO userDAO;
    private UserDTO userToDTO(User entity){
        return new UserDTO(entity.getName(), entity.getId());
    }

    private List<UserDTO> dtoToList(List<User> users){
        return users.stream()
                .map(this::userToDTO)
                .toList();
    }
    @PostMapping("/users")
    public UserDTO addUser(@RequestBody UserCreationParams params){
      return  userToDTO(userDAO.addUser(params));
    }
    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return dtoToList(userDAO.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable UUID id){
        return userToDTO(userDAO.getUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public UserDTO deleteUser(@PathVariable UUID id){
        return userToDTO(userDAO.deleteUser(id));
    }

    @PutMapping("/users/{id}")
    public UserDTO updateUser(@RequestBody UserCreationParams params, @PathVariable UUID id){
        User updatedUser = userDAO.updateUser(id,params);
        return userToDTO(updatedUser);
    }



}
