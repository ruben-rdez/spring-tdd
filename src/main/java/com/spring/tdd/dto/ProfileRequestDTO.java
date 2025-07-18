package com.spring.tdd.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public class ProfileRequestDTO {
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    
    @Min(value = 0, message = "Age must be greater than or equal to 0")
    private int age;

    public ProfileRequestDTO() {
    }

    public ProfileRequestDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
