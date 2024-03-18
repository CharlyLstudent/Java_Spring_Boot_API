package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.config.JwtTokenUtils;
import fr.campus.cda.charly.java_spring_boot_api.dto.AuthentificationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserSecurityEnterDTO;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserTokenDTO;
import fr.campus.cda.charly.java_spring_boot_api.entity.Role;
import fr.campus.cda.charly.java_spring_boot_api.entity.UserEntity;
import fr.campus.cda.charly.java_spring_boot_api.repository.RoleRepository;
import fr.campus.cda.charly.java_spring_boot_api.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users-security/")
public class UserSecurityController {

    @Autowired
    private UserSecurityRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private RoleRepository roleRepository;
    @PostMapping("add")
    public ResponseEntity<UserEntity> addUser(@RequestBody UserSecurityEnterDTO params) {
        UserEntity addUser = new UserEntity(params.username(), passwordEncoder.encode(params.password()));

        UserEntity savedUser = userRepository.save(addUser);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("get-all")
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("user/{username}")
    public UserEntity getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthentificationParams params) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(params.username(), params.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Générez le token JWT pour l'utilisateur authentifié

        String token = jwtTokenUtils.generateToken();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        UserTokenDTO user = new UserTokenDTO(params.username());

        return ResponseEntity.ok().headers(headers).body(user);
    }

    @GetMapping("get-roles")
    public List<Role> getAllRoles(){
       return roleRepository.findAll();
    }
}
