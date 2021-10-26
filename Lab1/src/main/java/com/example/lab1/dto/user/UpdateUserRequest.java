package com.example.lab1.dto.user;

import com.example.lab1.car.entity.Car;
import com.example.lab1.dto.car.UpdateCarRequest;
import com.example.lab1.user.entity.User;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateUserRequest {

    private String name;

    private String surname;

    private LocalDate birthDate;

    private String email;

    public static BiFunction<User, UpdateUserRequest, User> dtoToEntity() {
        return (user, request) -> {
            user.setName(request.getName());
            user.setSurname(request.getSurname());
            user.setBirthDate(request.getBirthDate());
            user.setEmail(request.getEmail());
            return user;
        };
    }
}
