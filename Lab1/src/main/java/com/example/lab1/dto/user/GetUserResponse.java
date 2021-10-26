package com.example.lab1.dto.user;

import com.example.lab1.car.entity.Car;
import com.example.lab1.dto.car.GetCarResponse;
import com.example.lab1.user.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUserResponse {

    private String login;

    private String name;

    private String surname;

    private String email;

    private String birthDate;

    public static Function<User, GetUserResponse> entityToDto() {
        return user -> GetUserResponse.builder()
                .login(user.getLogin())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .birthDate(user.getBirthDate().toString())
                .build();
    }
}
