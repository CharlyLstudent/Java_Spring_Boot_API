package fr.campus.cda.charly.java_spring_boot_api.dao;

public class User {
    private int id;
    private String name;

    public User(String name, int id) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
