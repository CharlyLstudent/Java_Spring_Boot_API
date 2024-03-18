package fr.campus.cda.charly.java_spring_boot_api.controller;

import fr.campus.cda.charly.java_spring_boot_api.config.SecurityConfig;
import fr.campus.cda.charly.java_spring_boot_api.dto.AuthentificationParams;
import fr.campus.cda.charly.java_spring_boot_api.dto.UserTokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
//@RequestMapping("/api/public")
public class AuthentificationController {
    @Autowired
    private  AuthenticationManager authenticationManager;


//    @PostMapping("/users-security/login")
//    public ResponseEntity<UserTokenDTO> login(@RequestBody AuthentificationParams params) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(params.username(), params.password())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        // Générez le token JWT pour l'utilisateur authentifié
//        String token = generateToken();
//        UserTokenDTO user = new UserTokenDTO(params.username(), token);
//
//        return ResponseEntity.ok(user);
//    }
//
//    private String generateToken(){
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        SecretKey key = Keys.secretKeyFor(signatureAlgorithm);
//        return Jwts.builder()
//                .setSubject(((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 8640000))
//                .signWith(key, signatureAlgorithm)
//                .compact();
//    }
}
