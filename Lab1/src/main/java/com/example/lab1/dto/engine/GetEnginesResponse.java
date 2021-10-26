package com.example.lab1.dto.engine;

import com.example.lab1.car.entity.Car;
import com.example.lab1.dto.car.GetCarsResponse;
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
public class GetEnginesResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Engine {
        private String name;
    }

    @Singular
    private List<Engine> engines;

    public static Function<Collection<com.example.lab1.car.entity.Engine>, GetEnginesResponse> entityToDto() {
        return engines -> {
            GetEnginesResponse.GetEnginesResponseBuilder response = GetEnginesResponse.builder();
            engines.stream()
                    .map(engine -> GetEnginesResponse.Engine.builder()
                            .name(engine.getName())
                            .build())
                    .forEach(response::engine);
            return response.build();
        };
    }
}
