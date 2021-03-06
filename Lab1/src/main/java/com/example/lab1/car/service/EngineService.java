package com.example.lab1.car.service;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.car.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EngineService {
    private EngineRepository repository;

    @Autowired
    public EngineService(EngineRepository repository) { this.repository = repository; }

    public Optional<Engine> find(String name) { return repository.findById(name); }

    public List<Engine> findAll() { return repository.findAll(); }

    @Transactional
    public Engine create(Engine engine) { return repository.save(engine); }

    @Transactional
    public void update(Engine engine) { repository.save(engine); }

    @Transactional
    public void delete(String name) { repository.deleteById(name); }
}
