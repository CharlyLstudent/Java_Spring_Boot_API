package fr.campus.cda.charly.java_spring_boot_api.dao;

import java.util.UUID;

public class User {
    private UUID id;
    private String name;

    public User(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
