package com.example.lab1.car.service;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.car.repository.CarRepository;
import com.example.lab1.car.repository.EngineRepository;
import com.example.lab1.user.entity.User;
import com.example.lab1.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository repository;

    private UserRepository userRepository;

    private EngineRepository engineRepository;

    @Autowired
    public CarService(CarRepository repository, UserRepository userRepository, EngineRepository engineRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.engineRepository = engineRepository;
    }

    public Optional<Car> find(Long id) { return repository.findById(id); }

    public Optional<Car> find(Long id, User user) { return repository.findByIdAndUser(id, user); }

    public Optional<Car> find(Long id, String login) {
        Optional<User> user = userRepository.findById(login);

        if (user.isPresent()) {
            return repository.findByIdAndUser(id, user.get());
        } else {
            return Optional.empty();
        }
    }

    public List<Car> findAll() { return repository.findAll(); }

    public List<Car> findAll(User user) { return repository.findAllByUser(user); }

    @Transactional
    public Car create(Car car) { return repository.save(car); }

    @Transactional
    public void update(Car car) { repository.save(car); }

    @Transactional
    public void delete(Long id) { repository.deleteById(id); }

}
