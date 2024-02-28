package fr.campus.cda.charly.java_spring_boot_api.dao;

import fr.campus.cda.charly.java_spring_boot_api.dto.UserCreationParams;
import fr.campus.cda.charly.java_spring_boot_api.entity.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public User getUserById(int id);
    public User addUser(UserCreationParams params);
    public User updateUser(int id, UserCreationParams params);
    public User deleteUser(int id);
}
