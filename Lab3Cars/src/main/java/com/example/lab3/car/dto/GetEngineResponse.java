package com.example.lab3.car.dto;

import com.example.lab3.car.entity.Engine;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetEngineResponse {
    private String name;

    private Double displacement;

//    private Boolean turbo;

    public static Function<Engine, GetEngineResponse> entityToDto() {
        return engine -> GetEngineResponse.builder()
                .name(engine.getName())
                .displacement(engine.getDisplacement())
                .build();
    }
}
