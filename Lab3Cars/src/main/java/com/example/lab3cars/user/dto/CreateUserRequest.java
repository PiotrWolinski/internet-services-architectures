package com.example.lab3cars.user.dto;

import com.example.lab3cars.digest.Sha256Utility;
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
public class CreateUserRequest {

    private String login;

    public static Function<CreateUserRequest, User> dtoToEntity() {
        return request -> User.builder()
                .login(request.getLogin())
                .build();
    }
}
