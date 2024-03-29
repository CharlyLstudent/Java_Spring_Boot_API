package fr.campus.cda.charly.java_spring_boot_api.service;

import fr.campus.cda.charly.java_spring_boot_api.entity.UserEntity;
import fr.campus.cda.charly.java_spring_boot_api.repository.UserRepository;
import fr.campus.cda.charly.java_spring_boot_api.repository.UserSecurityRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserSecurityRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public void createUser(String username, String rawPassword) {
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encryptedPassword);
        // TODO Setter les autres propriétés de UserEntity
        userRepository.save(user);
    }

}