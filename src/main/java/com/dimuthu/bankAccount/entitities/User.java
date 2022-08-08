package com.dimuthu.bankAccount.entitities;

import com.dimuthu.bankAccount.exceptions.AgeException;
import lombok.Data;
import lombok.NonNull;

@Data
public class User {
    @NonNull
    private String name;
    private int age;
    //... Rest of the details

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
}