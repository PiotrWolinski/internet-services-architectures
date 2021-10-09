package com.example.lab1.datastore;

import com.example.lab1.car.entity.Car;
import com.example.lab1.car.entity.Engine;
import com.example.lab1.serialization.CloningUtility;
import com.example.lab1.user.entity.User;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log
@Component
public class DataStore {

    private Set<Car> cars= new HashSet<>();

    private Set<Engine> engines = new HashSet<>();

    private Set<User> users = new HashSet<>();

    public Optional<Engine> findEngine(String name) {
        return engines.stream()
                .filter(engine -> engine.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public List<Engine> findAllEngines() {
        return new ArrayList<>(engines);
    }

    public synchronized void createEngine(Engine engine) throws IllegalArgumentException{
        findEngine(engine.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("Engine name \"%s\" is not unique", engine.getName()));
                },
                    () -> engines.add(engine));
    }

    public synchronized List<Car> findAllCars() {
        return cars.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized Optional<Car> findCar(Long id) {
        return cars.stream()
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createCar(Car car) throws IllegalArgumentException {
        car.setId(findAllCars().stream().mapToLong(Car::getId).max().orElse(0) + 1);
        cars.add(car);
    }

    public synchronized void updateCar(Car car) throws IllegalArgumentException {
        findCar(car.getId()).ifPresentOrElse(
                original -> {
                    cars.remove(original);
                    cars.add(car);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The car with id \"%d\" does not exist", car.getId()));
                });
    }

    public synchronized void deleteCar(Long id) throws IllegalArgumentException {
        findCar(id).ifPresentOrElse(
                original -> cars.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The car with id \"%d\" does not exist", id));
                }
        );
    }

    public synchronized Optional<User> findUser(String login) {
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized Optional<User> findUser(String login, String password) throws IllegalArgumentException{
        return users.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<User> findAllUsers() throws IllegalArgumentException{
        return users.stream().map(CloningUtility::clone).collect(Collectors.toList());
    }

    public synchronized void createUser(User user) throws IllegalArgumentException {
        findUser(user.getLogin()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The user login \"%s\" is not unique", user.getLogin()));
                },
                () -> {
                    user.setId(findAllUsers().stream().mapToLong(User::getId).max().orElse(0) + 1);
                    users.add(user);
                });
    }

    public Stream<Car> getCarStream() {
        return cars.stream();
    }
}
