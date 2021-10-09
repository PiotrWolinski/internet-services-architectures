package com.example.lab1.car.service;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.car.repository.CarRepository;
import com.example.lab1.car.repository.EngineRepository;
import com.example.lab1.user.entity.User;
import com.example.lab1.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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

    public Optional<Car> find(Long id) { return repository.find(id); }

    public Optional<Car> find(Long id, User user) { return repository.findByIdAndUser(id, user); }

    public List<Car> findAll() { return repository.findAll(); }

    public List<Car> findAll(User user) { return repository.findAllByUser(user); }

    public void create(Car car) { repository.create(car); }

    public void update(Car car) { repository.update(car); }

    public void delete(Car car) { repository.delete(car); }

    public void addCar(Scanner scanner) {

        System.out.println("name: ");
        String name = scanner.nextLine();
        System.out.println("doors: ");
        int doors = Integer.parseInt(scanner.nextLine());
        System.out.println("wheels: ");
        int wheels = Integer.parseInt(scanner.nextLine());
        System.out.println("seats: ");
        int seats = Integer.parseInt(scanner.nextLine());
        System.out.println("max speed: ");
        int maxSpeed = Integer.parseInt(scanner.nextLine());
        System.out.println("user login: ");
        String login = scanner.nextLine();
        System.out.println("engine name: ");
        String engineName = scanner.nextLine();

        User user = userRepository.find(login).orElse(null);

        if (user == null) {
            System.out.println("No such user found");
            return;
        }

        Engine engine = engineRepository.find(engineName).orElse(null);

        if (engine == null) {
            System.out.println("No such engine found");
            return;
        }

        create(Car.builder()
                .name(name)
                .doors(doors)
                .wheels(wheels)
                .seats(seats)
                .maxSpeed(maxSpeed)
                .user(user)
                .engine(engine)
                .build());
    }

    public void deleteCar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("id: ");
        Long id = Long.parseLong(scanner.next());

        Car found = find(id).orElse(null);

        if (found != null) {
            delete(found);
            System.out.println("Deleted");
        } else {
            System.out.println("Car with this id does not exist");
        }
        scanner.close();
    }
}
