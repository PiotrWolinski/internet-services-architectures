package com.example.lab1.dto.car;

import com.example.lab1.car.entity.Car;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateCarRequest {

    private int maxSpeed;

    private int seats;

    public static BiFunction<Car, UpdateCarRequest, Car> dtoToEntity() {
        return (car, request) -> {
            car.setMaxSpeed(request.getMaxSpeed());
            car.setSeats(request.getSeats());
            return car;
        };
    }
}
