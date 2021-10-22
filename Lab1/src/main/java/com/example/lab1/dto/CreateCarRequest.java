package com.example.lab1.dto;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.user.entity.User;
import lombok.*;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCarRequest {

    private Long id;

    private String name;

    private int wheels;

    private int seats;

    private int maxSpeed;

    private int doors;

    private String engine;

    public static Function<CreateCarRequest, Car> dtoToEntity(
            Function<String, Engine> engineFunction,
            Supplier<User> userSupplier) {
        return request -> Car.builder()
                .id(request.getId())
                .name(request.getName())
                .wheels(request.getWheels())
                .seats(request.getSeats())
                .maxSpeed(request.getMaxSpeed())
                .doors(request.getDoors())
                .user(userSupplier.get())
                .engine(engineFunction.apply(request.getEngine()))
                .build();
    }

}
