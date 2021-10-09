package com.example.lab1.car.repository;

import com.example.lab1.datastore.DataStore;
import com.example.lab1.repository.Repository;
import com.example.lab1.serialization.CloningUtility;
import com.example.lab1.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.lab1.car.entity.Car;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class CarRepository implements Repository<Car, Long> {

    private DataStore store;

    @Autowired
    public CarRepository(DataStore store) { this.store = store; }

    @Override
    public Optional<Car> find(Long id) { return store.findCar(id); }

    @Override
    public List<Car> findAll() { return store.findAllCars(); }

    @Override
    public void create(Car car) { store.createCar(car); }

    @Override
    public void delete(Car car) { store.deleteCar(car.getId()); }

    @Override
    public void update(Car car) { store.updateCar(car); }

    public Optional<Car> findByIdAndUser(Long id, User user) {
        return store.getCarStream()
                .filter(car -> car.getUser().equals(user))
                .filter(car -> car.getId().equals(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public List<Car> findAllByUser(User user) {
        return store.getCarStream()
                .filter(car -> car.getUser().equals(user))
                .map(CloningUtility::clone)
                .collect(Collectors.toList());
    }
}
