package com.shopme.admin.entity;

import com.shopme.common.entity.Role;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 127,nullable = false,unique = true)
    private String email;

    @Column(length = 64,nullable = false)
    private String password;

    @Column(name = "first_name",length = 45,nullable = false)
    private String firstname;

    @Column(name = "last_name",length = 45,nullable = false)
    private String lastname;

    @Column(length = 64)
    private String photos;
    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles= new HashSet<>();
    public User(String email, String password, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public String rolex(){
        String em="";
        for(Role e:roles){
            String s = em + " | " + e.getName();
            em=s;
        }
        return  em;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User() {
    }
    public  void addRole(Role role){
        this.roles.add(role);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
