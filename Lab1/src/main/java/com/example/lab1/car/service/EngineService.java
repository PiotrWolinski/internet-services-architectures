package com.example.lab1.car.service;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.car.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EngineService {
    private EngineRepository repository;

    @Autowired
    public EngineService(EngineRepository repository) { this.repository = repository; }

    public Optional<Engine> find(String name) { return repository.find(name); }

    public List<Engine> findAll() { return repository.findAll(); }

    public void create(Engine engine) { repository.create(engine); }

    public void addEngine(Scanner scanner) {

        System.out.println("engine name: ");
        String name = scanner.nextLine();
        System.out.println("displacement: ");
        Double displacement = Double.parseDouble(scanner.nextLine());

        create(Engine.builder()
                .name(name)
                .displacement(displacement)
                .build());
    }
}
