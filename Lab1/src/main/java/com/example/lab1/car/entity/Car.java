package com.example.lab1.car.entity;

import com.example.lab1.user.entity.User;
import com.example.lab1.vehicle.entity.Vehicle;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = true)
public class Car extends Vehicle {

    private User user;

    private Engine engine;

    private int doors;

    @Override
    public String toString() {
        String s = "Car<";

        s += String.format("id=%d, ", getId());
        s += String.format("name=%s, ", getName());
        s += String.format("wheels=%d,", getWheels());
        s += String.format("seats=%s, ", getSeats());
        s += String.format("masSpeed=%s, ", getMaxSpeed());
        s += String.format("user=%s, ", getUser().getLogin());
        s += String.format("engine=%s", getEngine().getName());
        s += ">";
        return s;
    }
}
