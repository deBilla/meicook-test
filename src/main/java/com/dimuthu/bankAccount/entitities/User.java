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
    @NonNull
    private String nic;

    @NonNull
    private String name;
    private int age;

    //... Rest of the details

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = false)
    private List<Account> accountList = new ArrayList<>();

    public void setAge(int age) {
        if (age >= 18) {
            this.age = age;
        } else {
            throw new AgeException("Age is below the required age");
        }
    }

    public User(String name, int age, String nic) {
        this.name = name;
        this.nic = nic;

        if (age >= 18) {
            this.age = age;
        } else {
            throw new AgeException("Age is below the required age");
        }
    }

    public User() {
    }
}