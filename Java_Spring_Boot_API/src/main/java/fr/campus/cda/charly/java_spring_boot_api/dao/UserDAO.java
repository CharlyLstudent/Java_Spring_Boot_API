package fr.campus.cda.charly.java_spring_boot_api.dao;

import fr.campus.cda.charly.java_spring_boot_api.dto.UserCreationParams;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(UUID id);
    public User addUser(UserCreationParams params);
    public User updateUser(UUID id, UserCreationParams params);
    public User deleteUser(UUID id);
}
