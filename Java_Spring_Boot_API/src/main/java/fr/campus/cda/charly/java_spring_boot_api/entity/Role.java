package fr.campus.cda.charly.java_spring_boot_api.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<UserEntity> users;


    @Override
    public String getAuthority() {
        return name;
    }


}