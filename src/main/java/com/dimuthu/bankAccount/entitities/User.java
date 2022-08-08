package com.dimuthu.bankAccount.entitities;

import com.dimuthu.bankAccount.exceptions.AgeException;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;
    private int age;
    //... Rest of the details

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Account> accountList = new ArrayList<>();

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        }

        throw new AgeException("Age is below the required age");
    }

    public User(String name, int age) {
        this.name = name;

        if (age >= 18) {
            this.age = age;
        } else {
            throw new AgeException("Age is below the required age");
        }
    }

    public User() {
    }
}