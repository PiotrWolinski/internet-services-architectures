package com.example.lab3cars.car.dto;

import com.example.lab3cars.car.entity.Car;
import com.example.lab3cars.car.entity.Engine;
import com.example.lab3cars.user.entity.User;
import lombok.*;

import java.util.function.Function;

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

    private String user;

    public static Function<CreateCarRequest, Car> dtoToEntity(
            Function<String, Engine> engineFunction,
            Function<String, User> userFunction) {
        return request -> Car.builder()
                .id(request.getId())
                .name(request.getName())
                .wheels(request.getWheels())
                .seats(request.getSeats())
                .maxSpeed(request.getMaxSpeed())
                .doors(request.getDoors())
                .user(userFunction.apply(request.getUser()))
                .engine(engineFunction.apply(request.getEngine()))
                .build();
    }

}
