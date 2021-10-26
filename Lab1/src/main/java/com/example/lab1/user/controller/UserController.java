package com.example.lab1.user.controller;

import com.example.lab1.car.service.CarService;
import com.example.lab1.car.service.EngineService;
import com.example.lab1.dto.car.CreateCarRequest;
import com.example.lab1.dto.user.CreateUserRequest;
import com.example.lab1.dto.user.GetUserResponse;
import com.example.lab1.dto.user.GetUsersResponse;
import com.example.lab1.dto.user.UpdateUserRequest;
import com.example.lab1.user.entity.User;
import com.example.lab1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/users")
public class UserController {
    private CarService carService;

    private EngineService engineService;

    private UserService userService;

    @Autowired
    public UserController(CarService carService, EngineService engineService, UserService userService) {
        this.carService = carService;
        this.engineService = engineService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<GetUsersResponse> getUsers() {
        List<User> users = userService.findAll();
        Function<Collection<User>, GetUsersResponse> mapper = GetUsersResponse.entityToDto();
        GetUsersResponse response = mapper.apply(users);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{login}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("login") String login) {
        return userService.find(login)
                .map(user -> ResponseEntity.ok(GetUserResponse.entityToDto().apply(user)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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

    @PutMapping("{login}")
    public ResponseEntity<Void> updateUser(@RequestBody UpdateUserRequest request,
                                           @PathVariable("login") String login) {
        Optional<User> user = userService.find(login);

        if (user.isPresent()) {
            UpdateUserRequest.dtoToEntity().apply(user.get(), request);
            userService.update(user.get());

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
