package fr.campus.cda.charly.java_spring_boot_api.repository;

import fr.campus.cda.charly.java_spring_boot_api.entity.Role;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.List;

public interface RoleRepository extends JpaRepositoryImplementation<Role, Long> {
    List<Role> findAll();
    Role findRoleByName(String name);
}
