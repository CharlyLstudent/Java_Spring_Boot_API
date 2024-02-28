package fr.campus.cda.charly.java_spring_boot_api.service;

import fr.campus.cda.charly.java_spring_boot_api.entity.User;
import fr.campus.cda.charly.java_spring_boot_api.dao.UserDAO;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserCreationParams;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserCollectionImpl implements UserDAO {

    List<User> users = new ArrayList<>();

    @Override
    public List<User> getAllUsers() {
        return this.users;
    }

    @Override
    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public User addUser(UserCreationParams params) {
       User user = new User(params.name(), params.id());
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(int id, UserCreationParams params) {
       User user =  users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        assert user != null;
        user.setName(params.name());
        return user;
    }

    @Override
    public User deleteUser(int id) {
        User user =  users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        users.remove(user);
        return user;
    }
}
