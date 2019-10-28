package com.example.task1.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "person_role",
            joinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> role;

    @Column(name = "age")
    private int age;

    public User() {
    }

    public User(String login, String password, Set<Role> role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String login, String password, Set<Role> role, int age) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + "****" + '\'' +
                ", role=" + role +
                ",age=" + age +
                '}';
    }
}

