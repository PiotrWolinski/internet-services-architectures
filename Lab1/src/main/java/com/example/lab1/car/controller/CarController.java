package com.example.lab1.car.controller;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.service.CarService;
import com.example.lab1.car.service.EngineService;
import com.example.lab1.dto.CreateCarRequest;
import com.example.lab1.dto.GetCarResponse;
import com.example.lab1.dto.GetCarsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@RestController
@RequestMapping("api/cars")
public class CarController {
    private CarService carService;

    private EngineService engineService;

    @Autowired
    public CarController(CarService carService, EngineService engineService) {
        this.carService = carService;
        this.engineService = engineService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars() {
        List<Car> cars = carService.findAll();
        Function<Collection<Car>, GetCarsResponse> mapper = GetCarsResponse.entityToDto();
        GetCarsResponse response = mapper.apply(cars);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("id") long id) {
        return carService.find(id)
                .map(car -> ResponseEntity.ok(GetCarResponse.entityToDto().apply(car)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CreateCarRequest request, UriComponentsBuilder builder) {
        Car car = CreateCarRequest
                .dtoToEntity(name -> engineService.find(name).orElseThrow(), () -> null)
                .apply(request);
        car = carService.create(car); // Service + Repository must be changed to meet JPA standard first for it to work

        return ResponseEntity.created(builder.pathSegment("api", "cars", "{id}")
                .buildAndExpand(car.getId()).toUri()).build();
    }
}
