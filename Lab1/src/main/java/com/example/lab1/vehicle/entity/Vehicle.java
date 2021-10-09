package com.example.lab1.vehicle.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Vehicle implements Serializable {
    private Long id;

    private String name;

    private int wheels;

    private int seats;

    private int maxSpeed;
}
