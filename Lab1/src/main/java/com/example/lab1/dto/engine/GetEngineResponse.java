package com.example.lab1.dto.engine;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.dto.car.GetCarResponse;
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
