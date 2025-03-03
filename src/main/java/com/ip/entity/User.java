package com.ip.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "Users", schema = "IP_Access_Project_DB")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID", nullable = false)
    private Integer id;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "zipCode", length = 10)
    private String zipCode;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phoneNumber", length = 20)
    private String phoneNumber;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
