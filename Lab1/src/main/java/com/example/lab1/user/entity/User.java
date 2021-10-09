package com.example.lab1.user.entity;

import com.example.lab1.car.entity.Car;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User implements Serializable {

    private Long id;

    private String login;

    private String name;

    private String surname;

    private LocalDate birthDate;

    @ToString.Exclude
    private String password;

    private String email;

    @ToString.Exclude
    private List<Car> cars;
}
