package com.example.lab3.car.controller;

import com.example.lab3.car.entity.Engine;
import com.example.lab3.car.service.CarService;
import com.example.lab3.car.service.EngineService;
import com.example.lab3.car.dto.GetEngineResponse;
import com.example.lab3.car.dto.GetEnginesResponse;
import com.example.lab3.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/engines")
public class EngineController {

    private CarService carService;

    private EngineService engineService;

    private UserService userService;

    @Autowired
    public EngineController(CarService carService, EngineService engineService, UserService userService) {
        this.carService = carService;
        this.engineService = engineService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<GetEnginesResponse> getEngines() {
        List<Engine> engines = engineService.findAll();
        Function<Collection<Engine>, GetEnginesResponse> mapper = GetEnginesResponse.entityToDto();
        GetEnginesResponse response = mapper.apply(engines);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetEngineResponse> getEngine(@PathVariable("name") String name) {
        return engineService.find(name)
                .map(engine -> ResponseEntity.ok(GetEngineResponse.entityToDto().apply(engine)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteEngine(@PathVariable("name") String name) {
        Optional<Engine> engine = engineService.find(name);

        if (engine.isPresent()) {
            engineService.delete(engine.get().getName());

            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
