package com.example.lab3.car.dto;

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

    public static Function<Collection<com.example.lab3.car.entity.Engine>, GetEnginesResponse> entityToDto() {
        return engines -> {
            GetEnginesResponseBuilder response = GetEnginesResponse.builder();
            engines.stream()
                    .map(engine -> Engine.builder()
                            .name(engine.getName())
                            .build())
                    .forEach(response::engine);
            return response.build();
        };
    }
}
