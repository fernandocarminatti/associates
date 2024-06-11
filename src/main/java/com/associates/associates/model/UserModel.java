package com.associates.associates.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "associates")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_cpf", unique = true)
    private String userCpf;

    public UserModel() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserCpf() {
        return userCpf;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userCpf='" + userCpf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel userModel)) return false;
        return Objects.equals(userId, userModel.userId) && Objects.equals(firstName, userModel.firstName) && Objects.equals(lastName, userModel.lastName) && Objects.equals(userCpf, userModel.userCpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, userCpf);
    }

}
