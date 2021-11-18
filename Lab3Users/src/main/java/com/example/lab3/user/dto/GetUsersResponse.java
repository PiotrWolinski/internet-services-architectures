package com.example.lab3.user.dto;

import com.example.lab3.user.entity.User;
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


    @Singular
    private List<String> users;

    public static Function<Collection<User>, GetUsersResponse> entityToDto() {
        return characters -> {
            GetUsersResponseBuilder response = GetUsersResponse.builder();
            characters.stream()
                    .map(User::getLogin)
                    .forEach(response::user);
            return response.build();
        };
    }
}
