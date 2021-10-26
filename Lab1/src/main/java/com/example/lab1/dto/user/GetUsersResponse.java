package com.example.lab1.dto.user;

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
public class GetUsersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class User {

        private String login;

    }

    @Singular
    private List<User> users;

    public static Function<Collection<com.example.lab1.user.entity.User>, GetUsersResponse> entityToDto() {
        return users -> {
            GetUsersResponse.GetUsersResponseBuilder response = GetUsersResponse.builder();
            users.stream()
                    .map(user -> User.builder()
                            .login(user.getLogin())
                            .build())
                    .forEach(response::user);
            return response.build();
        };
    }
}
