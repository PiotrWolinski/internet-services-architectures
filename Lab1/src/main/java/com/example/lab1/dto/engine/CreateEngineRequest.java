package com.example.lab1.dto.engine;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.user.entity.User;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateEngineRequest {

    private String name;

    private Double displacement;

//    private Boolean turbo;

    public static Function<com.example.lab1.dto.engine.CreateEngineRequest, Engine> dtoToEntity() {
        return request -> Engine.builder()
                .name(request.getName())
                .displacement(request.getDisplacement())
                .build();
    }

}
