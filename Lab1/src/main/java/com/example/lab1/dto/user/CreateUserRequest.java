package com.example.lab1.dto.user;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.digest.Sha256Utility;
import com.example.lab1.dto.car.CreateCarRequest;
import com.example.lab1.user.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateUserRequest {
    private String login;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String birthDate;

    public static Function<CreateUserRequest, User> dtoToEntity() {
        return request -> User.builder()
                .login(request.getLogin())
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .birthDate(LocalDate.parse(request.getBirthDate()))
                .password(Sha256Utility.hash(request.getPassword()))
                .build();
    }
}
