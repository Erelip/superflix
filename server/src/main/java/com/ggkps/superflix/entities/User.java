package com.ggkps.superflix.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "user")
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<VisualContent> visualContents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Favorite> favorite;

    public User() {
    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<VisualContent> getVisualContents() {
        return this.visualContents;
    }

    public void setVisualContents(List<VisualContent> visualContents) {
        this.visualContents = visualContents;
    }

    public List<Favorite> getFavorite() {
        return this.favorite;
    }

    public void setFavorite(List<Favorite> favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "User{id=" + this.id + ", firstname='" + this.username  + "', email='" + this.email + "', password='" + this.password + "'}";
    }
}
