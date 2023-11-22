package com.example.lab6.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {
    @NotNull(message = "id cannot be null")
    @Size(min = 3,message = "enter id more than 2 characters")
    private String id;
    @NotNull(message = "name cannot be null")
    @Size(min = 5,message = "enter name more than 4 characters")
    @Pattern(regexp = "^[\\p{Alpha} ]*$", message = "Name should contain only alphabets")
    private String name;
    @Email(message = "wrong email")
    private String Email;
    @Pattern(regexp = "^05\\d{8}$",message = "enter correct number from 10 digits and start with 05")
    private String phoneNumber;
    @NotNull(message = "age cannot be null")
    @Positive(message = "enter correct age")
    @Min(value = 26,message = "age start with 26")
    private int age;
    @NotNull(message = "position cannot be null!")
    @Pattern(regexp = "^(supervisor|coordinator)$",message = "enter position supervisor or coordinator")
    private String position;
    @AssertFalse(message = "must be false")
    private boolean onLeave;
    @NotNull(message = "date cannot be null!")
    @PastOrPresent(message = "date should be in past or present!")
    @JsonFormat(pattern = "yyyy:MM:DD")
    private LocalDate hireDate;
    @NotNull(message = "annual leave cannot be null!")
    @PositiveOrZero(message = "annual leave must be positive number!")
    private int AnnualLeave;
}
