package com.example.lab1.dto.engine;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateEngineRequest {

    private double displacement;

    public static BiFunction<Engine, com.example.lab1.dto.engine.UpdateEngineRequest, Engine> dtoToEntity() {
        return (engine, request) -> {
            engine.setDisplacement(request.getDisplacement());
            return engine;
        };
    }
}
