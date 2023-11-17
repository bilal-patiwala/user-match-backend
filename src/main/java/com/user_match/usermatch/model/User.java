package com.user_match.usermatch.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="_user")
public class User {
    
    @Id
    @GeneratedValue(

        strategy = GenerationType.IDENTITY

    )
    private Long ID;

    public Long getID() {
        return ID;
    }
    public void setID(Long iD) {
        ID = iD;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    private String username;
    private String email;
    private String location;
    private String body_type;

    public String getBody_type() {
        return body_type;
    }
    public void setBody_type(String body_type) {
        this.body_type = body_type;
    }
    public Set<Style> getStyle() {
        return style;
    }
    public void setStyle(Set<Style> style) {
        this.style = style;
    }
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Style> style;
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}
