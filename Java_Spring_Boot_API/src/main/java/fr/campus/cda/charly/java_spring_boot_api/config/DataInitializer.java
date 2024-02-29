//package fr.campus.cda.charly.java_spring_boot_api.config;
//
//import fr.campus.cda.charly.java_spring_boot_api.entity.UserEntity;
//import fr.campus.cda.charly.java_spring_boot_api.repository.UserSecurityRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class DataInitializer {
//    @Bean
//    public CommandLineRunner initDatabase(UserSecurityRepository userRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            for (int i = 1; i <= 15; i++) {
//                String username = "User" + i;
//                String password = "password" + i;
//                userRepository.save(new UserEntity(username, passwordEncoder.encode(password), true, true, true, true));
//            }
//        };
//    }
//}
