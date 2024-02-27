package fr.campus.cda.charly.java_spring_boot_api.dao;

import fr.campus.cda.charly.java_spring_boot_api.dto.UserCreationParams;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User addUser(UserCreationParams params);
    public User updateUser(int id, UserCreationParams params);
    public User deleteUser(int id);
}
