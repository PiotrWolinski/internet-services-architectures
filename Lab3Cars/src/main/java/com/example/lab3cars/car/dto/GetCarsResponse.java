package com.example.lab3cars.car.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCarsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Car {
        private long id;

        private String name;
    }

    @Singular
    private List<Car> cars;

    public static Function<Collection<com.example.lab3cars.car.entity.Car>, GetCarsResponse> entityToDto() {
        return cars -> {
            GetCarsResponseBuilder response = GetCarsResponse.builder();
            cars.stream()
                    .map(car -> Car.builder()
                            .id(car.getId())
                            .name(car.getName())
                            .build())
                    .forEach(response::car);
            return response.build();
        };
    }
}
