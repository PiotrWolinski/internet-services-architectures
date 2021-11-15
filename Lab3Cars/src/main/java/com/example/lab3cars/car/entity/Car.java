package com.example.lab3cars.car.entity;

import com.example.lab3cars.user.entity.User;
import com.example.lab3cars.vehicleEntity.Vehicle;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "engine")
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
