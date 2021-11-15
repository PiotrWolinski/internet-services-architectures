package com.example.lab3cars.user.controller;

import com.example.lab3cars.car.service.CarService;
import com.example.lab3cars.car.service.EngineService;
import com.example.lab3cars.user.dto.CreateUserRequest;
import com.example.lab3cars.user.entity.User;
import com.example.lab3cars.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest request, UriComponentsBuilder builder) {
        User user = CreateUserRequest.dtoToEntity().apply(request);

        user = userService.create(user);

        return ResponseEntity.created(builder.pathSegment("api", "users", "{login}")
                .buildAndExpand(user.getLogin()).toUri()).build();

    }

    @DeleteMapping("{login}")
    public ResponseEntity<Void> deleteUser(@PathVariable("login") String login) {
        Optional<User> user = userService.find(login);

        if (user.isPresent()) {
            userService.delete(user.get().getLogin());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
